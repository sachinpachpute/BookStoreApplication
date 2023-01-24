package com.sp.spring.catalog.controller;

import com.sp.spring.catalog.service.ProductService;
import com.sp.spring.catalog.web.CreateProductRequest;
import com.sp.spring.catalog.web.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ProductController {

    @GetMapping("/jwt")
    public String home(Authentication a){
        return "Hello, JWT!";
    }

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    //TODO: implement hasAuthority
    //@PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<?> createProduct(@RequestBody @Valid CreateProductRequest createProductRequest){

        String product = productService.createProduct(createProductRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{productId}")
                .buildAndExpand(product).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("productId") String productId) {

        ProductResponse product = productService.getProduct(productId);

        return ResponseEntity.ok(product);
    }
    @GetMapping(value = "/products", produces = "application/json")
    public ResponseEntity<?> getAllProducts(@RequestParam(value = "sort", required = false) String sort,
                                            @RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "size", required = false) Integer size){

        Page<ProductResponse> list = productService.getAllProducts(sort, page, size);

        return ResponseEntity.ok(list);
    }

}
