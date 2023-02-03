package com.sp.spring.orderservice.service;

import com.sp.spring.orderservice.repository.dao.Cart;

public interface CartService {

    Cart getCart();
    
    Cart getCartByCartId(String cartId);

    String createCart();

    Cart getCartByUserName(String userName);

}
