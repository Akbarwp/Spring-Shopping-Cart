package com.spring.springshoppingcart.service.cart;

import java.math.BigDecimal;

import com.spring.springshoppingcart.model.Cart;
import com.spring.springshoppingcart.model.User;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);
    Cart initializeNewCart(User user);
    Cart getCartByUserId(Long userId);
}
