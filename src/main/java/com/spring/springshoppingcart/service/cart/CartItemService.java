package com.spring.springshoppingcart.service.cart;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springshoppingcart.exception.ResourceException;
import com.spring.springshoppingcart.model.Cart;
import com.spring.springshoppingcart.model.CartItem;
import com.spring.springshoppingcart.model.Product;
import com.spring.springshoppingcart.repository.CartItemRepository;
import com.spring.springshoppingcart.repository.CartRepository;
import com.spring.springshoppingcart.service.product.IProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService {

    @Autowired
    private final CartRepository cartRepository;

    @Autowired
    private final CartItemRepository cartItemRepository;

    @Autowired
    private final IProductService productService;
    
    @Autowired
    private final ICartService cartService;

    @Override
    @Transactional
    public void addItemToCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartService.getCart(cartId);
        Product product = productService.getProductById(productId);
        CartItem cartItem = cart.getCartItems()
            .stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst().orElse(new CartItem());

        // Check if cartItem already exists
        // if Yes, create a new one
        // if No, update quantity
        if (cartItem.getId() == null) {
            // Check if product inventory is enough
            checkProductInventory(productId, quantity);

            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(product.getPrice());

        } else {
            int totalQuantity = cartItem.getQuantity() + quantity;
            checkProductInventory(productId, totalQuantity);
            cartItem.setQuantity(totalQuantity);
        }

        cartItem.setTotalPrice();
        cart.addItem(cartItem);
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void removeItemFromCart(Long cartId, Long productId) {
        Cart cart = cartService.getCart(cartId);
        CartItem cartItem = getCartItem(cartId, productId);
        cart.removeItem(cartItem);
        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void updateItemQuantity(Long cartId, Long productId, int quantity) {
        Cart cart = cartService.getCart(cartId);
        cart.getCartItems()
            .stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst()
            .ifPresent(item -> {
                item.setQuantity(quantity);
                item.setUnitPrice(item.getProduct().getPrice());
                item.setTotalPrice();
            });

        BigDecimal totalAmount = cart.getCartItems()
            .stream().map(CartItem ::getTotalPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        cart.setTotalAmount(totalAmount);

        cartRepository.save(cart);
    }

    @Override
    @Transactional(readOnly = true)
    public CartItem getCartItem(Long cartId, Long productId) {
        Cart cart = cartService.getCart(cartId);
        return cart.getCartItems()
            .stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst().orElseThrow(() -> new ResourceException("Item not found!"));
    }

    @Transactional(readOnly = true)
    public void checkProductInventory(Long productId, int quantity) {
        Product product = productService.getProductById(productId);
        if (product.getInventory() < quantity) {
            throw new ResourceException("Product item is not enough!");
        }
    }
}
