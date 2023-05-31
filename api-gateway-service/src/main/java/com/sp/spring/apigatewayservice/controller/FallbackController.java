package com.sp.spring.apigatewayservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/orderFallBack")
    public Mono<String> catalogServiceFallBack(){
        return Mono.just(" Order Service is taking too long to respond or is down. Please try again later");
    }

    @RequestMapping("/catalogFallBack")
    public Mono<String> orderServiceFallBack(){
        return Mono.just("Catalog Service is taking too long to respond or is down. Please try again later");
    }
}
