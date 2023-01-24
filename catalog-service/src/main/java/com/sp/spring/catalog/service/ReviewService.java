package com.sp.spring.catalog.service;

import com.sp.spring.catalog.repository.dao.ProductReview;
import com.sp.spring.catalog.web.CreateOrUpdateReviewRequest;

import java.util.List;

public interface ReviewService {

    void createOrUpdateReview(CreateOrUpdateReviewRequest createOrUpdateReviewRequest);

    List<ProductReview> getReviewsForProduct(String productId);

}
