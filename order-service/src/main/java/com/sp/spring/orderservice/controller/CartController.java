package com.sp.spring.orderservice.controller;

import com.sp.spring.orderservice.service.CartService;
import com.sp.spring.orderservice.web.CreateCartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @CrossOrigin("*")
    @PostMapping("/cart")
    public ResponseEntity<CreateCartResponse> createCart() {
        String cartId = cartService.createCart();

        CreateCartResponse createCartResponse = CreateCartResponse.builder()
                .cartId(cartId)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(createCartResponse);
    }

    @GetMapping("/cart")
    public ResponseEntity<?> getCart(){

        return ResponseEntity.ok(cartService.getCart());

    }

    @GetMapping("/demoNew")
    public String demoNew() {
        System.out.println("Hello 1");
        System.out.println("Hello 2");
        System.out.println("Hello 3");
        return "Demo";
    }
}
