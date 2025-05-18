package com.spring.springshoppingcart.database;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.springshoppingcart.model.Category;
import com.spring.springshoppingcart.model.Product;
import com.spring.springshoppingcart.repository.CategoryRepository;
import com.spring.springshoppingcart.repository.ProductRepository;

@Component
public class ProductSeeder {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public Product seed(String name, String brand, BigDecimal price, int inventory, String description, Long category_id) {
        Category category = categoryRepository.findById(category_id).orElseThrow(() -> new RuntimeException("Category not found."));

        Product product = new Product();
        product.setName(name);
        product.setBrand(brand);
        product.setPrice(price);
        product.setInventory(inventory);
        product.setDescription(description);
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);
        System.out.println("Product seeded successfully!");

        return savedProduct;
    }
}
