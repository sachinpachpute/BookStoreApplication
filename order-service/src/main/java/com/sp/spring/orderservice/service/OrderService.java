package com.sp.spring.orderservice.service;


import com.sp.spring.orderservice.web.CreateOrderRequest;
import com.sp.spring.orderservice.web.CreateOrderResponse;
import com.sp.spring.orderservice.web.PreviewOrderRequest;
import com.sp.spring.orderservice.web.PreviewOrderResponse;

import java.util.List;

public interface OrderService {

    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);

    PreviewOrderResponse previewOrder(PreviewOrderRequest previewOrderRequest);

    CreateOrderResponse getOrderById(String orderId);

    List<CreateOrderResponse> getMyOrders();

    List<CreateOrderResponse> getAllOrders();
}
