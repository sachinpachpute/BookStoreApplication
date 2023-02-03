package com.sp.spring.catalog.service;

import com.sp.spring.catalog.repository.dao.ProductCategory;
import com.sp.spring.catalog.web.CreateProductCategoryRequest;
import com.sp.spring.catalog.web.UpdateProductCategoryRequest;
import org.springframework.data.domain.Page;

import jakarta.validation.Valid;

public interface ProductCategoryService {

    String createProductCategory(@Valid CreateProductCategoryRequest createProductCategoryRequest);

    ProductCategory getProductCategory(String productCategoryId);

    void deleteProductCategory(String productCategoryId);

    void updateProductCategory(UpdateProductCategoryRequest updateProductCategoryRequest);

    Page<ProductCategory> getAllProductCategories(String sort, Integer page, Integer size);
}
