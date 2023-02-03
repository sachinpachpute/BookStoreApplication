package com.sp.spring.orderservice.repository;

import com.sp.spring.orderservice.repository.dao.OrderShippingAddress;
import org.springframework.data.repository.CrudRepository;

public interface OrderShippingAddressRepository extends CrudRepository<OrderShippingAddress, String> {
    OrderShippingAddress findByOrderId(String orderId);
}
