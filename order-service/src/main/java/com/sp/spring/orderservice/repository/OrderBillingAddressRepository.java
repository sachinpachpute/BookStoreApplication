package com.sp.spring.orderservice.repository;

import com.sp.spring.orderservice.repository.dao.OrderBillingAddress;
import org.springframework.data.repository.CrudRepository;

public interface OrderBillingAddressRepository extends CrudRepository<OrderBillingAddress, String> {
    OrderBillingAddress findByOrderId(String orderId);
}
