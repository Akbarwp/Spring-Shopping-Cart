package com.spring.springshoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.springshoppingcart.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    void deleteAllByCartId(Long id);
}
