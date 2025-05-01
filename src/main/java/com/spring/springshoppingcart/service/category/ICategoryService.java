package com.spring.springshoppingcart.service.category;

import java.util.List;

import com.spring.springshoppingcart.model.Category;

public interface ICategoryService {
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    Category addCategory(Category category);
    Category updateCategory(Category category, Long id);

    void deleteCategoryById(Long id);

    List<Category> getAllCategories();
}
