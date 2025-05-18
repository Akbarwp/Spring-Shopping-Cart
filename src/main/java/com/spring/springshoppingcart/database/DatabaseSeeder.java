package com.spring.springshoppingcart.database;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.spring.springshoppingcart.model.Category;

@Component
public class DatabaseSeeder implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private RoleSeeder roleSeeder;

    @Autowired
    private UserSeeder userSeeder;

    @Autowired
    private CategorySeeder categorySeeder;

    @Autowired
    private ProductSeeder productSeeder;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // roleSeeder.seed("ROLE_ADMIN");
        // roleSeeder.seed("ROLE_USER");

        // userSeeder.seed("Admin", "admin@gmail.com", "Admin123");

        // for (int i = 1; i <= 5; i++) {
        //     userSeeder.seed("User " + i, "user" + i + "@gmail.com", null);
        // }

        // Category laptop = categorySeeder.seed("Laptop");
        // Category smartphone = categorySeeder.seed("Smartphone");
        // categorySeeder.seed("Electronic");

        // productSeeder.seed("Lenovo Legion 5", "Lenovo", new BigDecimal(20000000), 30, "", laptop.getId());
        // productSeeder.seed("Lenovo Legion 5i", "Lenovo", new BigDecimal(22000000), 23, "", laptop.getId());
        // productSeeder.seed("Axioo Pongo 760 v2", "Axioo", new BigDecimal(15000000), 53, "", laptop.getId());

        // productSeeder.seed("Samsung Galaxy S24 Ultra", "Samsung", new BigDecimal(25000000), 15, "", smartphone.getId());
        // productSeeder.seed("Samsung Galaxy A73", "Samsung", new BigDecimal(7500000), 7, "", smartphone.getId());
        // productSeeder.seed("Iphone 16 Pro", "Iphone", new BigDecimal(21000000), 15, "", smartphone.getId());

        // System.out.println("Database seeding completed successfully!");
    }
}
