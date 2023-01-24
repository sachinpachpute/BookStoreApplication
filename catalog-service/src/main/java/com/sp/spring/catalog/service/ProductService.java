package com.sp.spring.catalog.service;

import com.sp.spring.catalog.web.CreateProductRequest;
import com.sp.spring.catalog.web.ProductResponse;
import org.springframework.data.domain.Page;

import jakarta.validation.Valid;

public interface ProductService {

    //String createProduct(@Valid CreateProductRequest createProductRequest);

    ProductResponse getProduct(String productId);

    String createProduct(@Valid CreateProductRequest createProductRequest);

    Page<ProductResponse> getAllProducts(String sort, Integer page, Integer size);

   /* void deleteProduct(String productId);

    void updateProduct(UpdateProductRequest updateProductRequest);

    Page<Product> findAllProducts(Pageable pageable);

    Page<ProductResponse> getAllProducts(String sort, Integer page, Integer size);*/
}

