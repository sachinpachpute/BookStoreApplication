package com.sp.spring.orderservice.service.impl;

import com.sp.spring.feignclient.feign.AuthServerFeignClient;
import com.sp.spring.feignclient.feign.CatalogFeignClient;
import com.sp.spring.feignclient.web.GetProductResponse;
import com.sp.spring.orderservice.repository.CartItemRepository;
import com.sp.spring.orderservice.repository.dao.Cart;
import com.sp.spring.orderservice.repository.dao.CartItem;
import com.sp.spring.orderservice.service.CartItemService;
import com.sp.spring.orderservice.service.CartService;
import com.sp.spring.orderservice.web.CartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    CartService cartService;

    @Autowired
    CatalogFeignClient catalogFeignClient;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    AuthServerFeignClient authServerFeignClient;

    @Override
    public void addCartItem(CartItemRequest cartItemRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //String userName = (String) authentication.getPrincipal();
        String userName = (String) authentication.getName();
        Cart cartByUserName = cartService.getCartByUserName(userName);

        synchronized (CartServiceImpl.class) {
            if (cartByUserName == null) {
                //create cart for user if not exists.
                cartService.createCart();
                cartByUserName = cartService.getCartByUserName(userName);
            }
        }
    
        GetProductResponse getProductResponse = catalogFeignClient.getProduct(cartItemRequest.getProductId());

        if (cartItemRequest.getQuantity() > getProductResponse.getAvailableItemCount()) {
            throw new RuntimeException("Quantity is greater than available item count!");
        }

        //If the product already exists in the cart, update its quantity and itemPrice.

        if (cartByUserName.getCartItems() != null) {
            for (CartItem ci : cartByUserName.getCartItems()) {
    
                if (getProductResponse.getProductId().equals(ci.getProductId())) {
                    ci.setQuantity(cartItemRequest.getQuantity());
                    ci.setItemPrice(getProductResponse.getPrice());
                    ci.setExtendedPrice(ci.getQuantity() * getProductResponse.getPrice());
                    cartItemRepository.save(ci);
                    return;
                }
            }
        }

        //If cart doesn't have any cartItems, then create cartItems.
        CartItem cartItem = CartItem.builder()
                                    .cart(cartByUserName)
                                    .itemPrice(getProductResponse.getPrice())
                                    .extendedPrice(cartItemRequest.getQuantity() * getProductResponse.getPrice())
                                    .quantity(cartItemRequest.getQuantity())
                                    .productId(getProductResponse.getProductId())
                                    .productName(getProductResponse.getProductName())
                                    .build();

        cartItemRepository.save(cartItem);
    }

    @Override
    public void removeCartItem(String cartItemId) {
        CartItem cartItem = this.getCartItem(cartItemId);
        cartItemRepository.delete(cartItem);
    }

    @Override
    public CartItem getCartItem(String cartItemId) {
        Optional<CartItem> byCartItemId = cartItemRepository.findByCartItemId(cartItemId);
        return byCartItemId.orElseThrow(()-> new RuntimeException("CartItem doesn't exist!!"));
    }

    @Override
    public void removeAllCartItems(String cartId) {

        Cart cart = cartService.getCartByCartId(cartId);
        List<String> cartItemIds = cart.getCartItems().stream().map(cartItem -> cartItem.getCartItemId()).collect(Collectors.toList());
        if (!cartItemIds.isEmpty()) {
            cartItemIds.forEach(this::removeCartItem);
        }
    }
}
