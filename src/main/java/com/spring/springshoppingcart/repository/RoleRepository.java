package com.spring.springshoppingcart.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.springshoppingcart.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Collection<Role> findByName(String name);
}
