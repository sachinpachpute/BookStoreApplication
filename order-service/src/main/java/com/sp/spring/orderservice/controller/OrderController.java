package com.sp.spring.orderservice.controller;

import com.sp.spring.orderservice.service.OrderService;
import com.sp.spring.orderservice.web.CreateOrderRequest;
import com.sp.spring.orderservice.web.CreateOrderResponse;
import com.sp.spring.orderservice.web.PreviewOrderRequest;
import com.sp.spring.orderservice.web.PreviewOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
public class OrderController {
    
    @Autowired
    OrderService orderService;
    
    @PostMapping("/order")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest) {

        CreateOrderResponse createOrderResponse = orderService.createOrder(createOrderRequest);
        return ResponseEntity.ok(createOrderResponse);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<CreateOrderResponse> getOrderById(@PathVariable("orderId") String orderId) {

        CreateOrderResponse createOrderResponse = orderService.getOrderById(orderId);
        return ResponseEntity.ok(createOrderResponse);
    }

    @GetMapping("/order/myorders")
    public ResponseEntity<List<CreateOrderResponse>> getMyOrders() {

        List<CreateOrderResponse> createOrderResponse = orderService.getMyOrders();
        return ResponseEntity.ok(createOrderResponse);
    }

    @GetMapping("/orders")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<List<CreateOrderResponse>> getAllOrders() {
        List<CreateOrderResponse> createOrderResponse = orderService.getAllOrders();
        return ResponseEntity.ok(createOrderResponse);
    }

    @PostMapping("/previewOrder")
    public ResponseEntity<PreviewOrderResponse> previewOrder(@RequestBody @Valid PreviewOrderRequest previewOrderRequest) {

        PreviewOrderResponse previewOrderResponse = orderService.previewOrder(previewOrderRequest);

        return ResponseEntity.ok(previewOrderResponse);
    }
}
