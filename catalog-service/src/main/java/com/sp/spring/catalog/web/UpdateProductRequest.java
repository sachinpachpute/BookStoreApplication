package com.sp.spring.catalog.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {

    @NotNull(message = "productId should not be null!")
    @NotEmpty(message = "productId should not be empty!")
    private String productId;

    private String productName;

    private String description;

    @Min(value = 0)
    private Double price;

    private String imageId;

    private String productCategoryId;

    private Integer availableItemCount;

}
