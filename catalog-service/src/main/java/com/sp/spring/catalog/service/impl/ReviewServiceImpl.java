package com.sp.spring.catalog.service.impl;

import com.sp.spring.catalog.repository.ReviewRepository;
import com.sp.spring.catalog.repository.dao.ProductReview;
import com.sp.spring.catalog.service.ProductService;
import com.sp.spring.catalog.service.ReviewService;
import com.sp.spring.catalog.web.CreateOrUpdateReviewRequest;
import com.sp.spring.catalog.web.ProductResponse;
import com.sp.spring.feignclient.feign.AuthServerFeignClient;
import com.sp.spring.feignclient.web.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.sp.spring.common.util.CommonUtilityMethods.getUserIdFromToken;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    AuthServerFeignClient authServerFeignClient;

    @Autowired
    ProductService productService;

    @Override
    public void createOrUpdateReview(CreateOrUpdateReviewRequest createOrUpdateReviewRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userIdFromToken = getUserIdFromToken(authentication);

        GetUserResponse userResponse = authServerFeignClient.getUserByUserName(userIdFromToken);
        String userName = userResponse.getFirstName()+" "+userResponse.getLastName();

        //check whether product exists.
        ProductResponse product = productService.getProduct(createOrUpdateReviewRequest.getProductId());
        if (product == null) {
            throw new RuntimeException("Product doesn't exist!");
        }

        Optional<ProductReview> review = reviewRepository.findByUserIdAndProductId(userIdFromToken, createOrUpdateReviewRequest.getProductId());

        if (review.isPresent()) {
            ProductReview updatedReview = review.get();
            updatedReview.setRatingValue(createOrUpdateReviewRequest.getRatingValue());
            updatedReview.setReviewMessage(createOrUpdateReviewRequest.getReviewMessage());
            reviewRepository.save(updatedReview);
        } else {
            ProductReview newReview = ProductReview.builder()
                    .reviewMessage(createOrUpdateReviewRequest.getReviewMessage())
                    .ratingValue(createOrUpdateReviewRequest.getRatingValue())
                    .userId(userIdFromToken)
                    .userName(userName)
                    .productId(createOrUpdateReviewRequest.getProductId())
                    .build();
            reviewRepository.save(newReview);
        }
    }

    @Override
    public List<ProductReview> getReviewsForProduct(String productId) {

        Optional<List<ProductReview>> reviewsForProduct = reviewRepository.findAllByProductId(productId);
        return reviewsForProduct.orElseGet(ArrayList::new);

    }
}
