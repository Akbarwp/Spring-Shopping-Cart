package com.spring.springshoppingcart.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.springshoppingcart.model.User;
import com.spring.springshoppingcart.request.CreateUserRequest;
import com.spring.springshoppingcart.service.user.UserService;

@Component
public class UserSeeder {

    @Autowired
    private UserService userService;

    public User seed(String name, String email, String password) {
        if (password == null) {
            password = "password";
        }

        CreateUserRequest request = new CreateUserRequest();
        request.setName(name);
        request.setEmail(email);
        request.setPassword(password);

        User user = userService.createUser(request);
        System.out.println("User seeded successfully!");

        return user;
    }
}
