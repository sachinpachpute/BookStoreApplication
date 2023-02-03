package com.sp.spring.orderservice.repository;


import com.sp.spring.orderservice.repository.dao.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, String> {
    Cart findCartByUserName(String userName);

    Optional<Cart> findByCartId(String cartId);
}
