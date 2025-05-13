package com.spring.springshoppingcart.service.cart;

import com.spring.springshoppingcart.model.CartItem;

public interface ICartItemService {
    CartItem getCartItem(Long cartId, Long productId);
    void addItemToCart(Long cartId, Long productId, int quantity);
    void removeItemFromCart(Long cartId, Long productId);
    void updateItemQuantity(Long cartId, Long productId, int quantity);
}
