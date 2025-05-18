package com.spring.springshoppingcart.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.springshoppingcart.model.Role;
import com.spring.springshoppingcart.repository.RoleRepository;

@Component
public class RoleSeeder {

    @Autowired
    private RoleRepository roleRepository;

    public Role seed(String name) {
        Role role = new Role();
        role.setName(name);

        Role savedRole = roleRepository.save(role);
        System.out.println("Role seeded successfully!");

        return savedRole;
    }
}
