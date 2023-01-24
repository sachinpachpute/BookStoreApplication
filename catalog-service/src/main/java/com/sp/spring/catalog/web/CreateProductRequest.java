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
public class CreateProductRequest {

    @NotNull(message = "productName should not be null!")
    @NotEmpty(message = "productName should not be empty!")
    private String productName;

    private String description;

    @Min(value = 0)
    private double price;

    private String imageId;

    @NotNull(message = "productCategoryId should not be null!")
    @NotEmpty(message = "productCategoryId should not be empty!")
    private String productCategoryId;

    private int availableItemCount;

}
