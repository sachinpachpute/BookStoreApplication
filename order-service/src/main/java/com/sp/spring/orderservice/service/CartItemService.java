package com.sp.spring.orderservice.service;

import com.sp.spring.orderservice.repository.dao.CartItem;
import com.sp.spring.orderservice.web.CartItemRequest;

public interface CartItemService {

    void addCartItem(CartItemRequest cartItemRequest);
    void removeCartItem(String cartItemId);
    CartItem getCartItem(String cartItemId);
    void removeAllCartItems(String cartId);

}
