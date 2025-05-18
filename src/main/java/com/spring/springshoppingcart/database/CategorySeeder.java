package com.spring.springshoppingcart.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.springshoppingcart.model.Category;
import com.spring.springshoppingcart.repository.CategoryRepository;

@Component
public class CategorySeeder {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category seed(String name) {
        Category category = new Category();
        category.setName(name);

        Category savedCategory = categoryRepository.save(category);
        System.out.println("Category seeded successfully!");

        return savedCategory;
    }
}
