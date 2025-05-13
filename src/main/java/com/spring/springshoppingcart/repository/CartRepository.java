package com.spring.springshoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.springshoppingcart.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {}
