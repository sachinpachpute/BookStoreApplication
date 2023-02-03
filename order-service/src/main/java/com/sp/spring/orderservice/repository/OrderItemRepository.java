package com.sp.spring.orderservice.repository;

import com.sp.spring.orderservice.repository.dao.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, String> {
}
