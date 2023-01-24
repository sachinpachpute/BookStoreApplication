package com.sp.spring.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/jwt")
    public String home(Authentication a){
        return "Hello, JWT!";
    }

}
