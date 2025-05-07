package com.spring.springshoppingcart.service.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springshoppingcart.exception.ResourceException;
import com.spring.springshoppingcart.model.Category;
import com.spring.springshoppingcart.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    
    @Autowired
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new ResourceException("Category not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Category addCategory(Category category) {
        return Optional.of(category).filter(c -> !categoryRepository.existsByName(c.getName()))
            .map(categoryRepository::save)
            .orElseThrow(() -> new ResourceException(category.getName() + " already exists!"));
    }

    @Override
    @Transactional
    public Category updateCategory(Category category, Long id) {
        return Optional.ofNullable(getCategoryById(id))
            .map(oldCategory -> {
                oldCategory.setName(category.getName());
                return categoryRepository.save(oldCategory);
            })
            .orElseThrow(() -> new ResourceException("Category not found!"));
    }

    @Override
    @Transactional
    public void deleteCategoryById(Long id) {
        categoryRepository.findById(id)
            .ifPresentOrElse(
                categoryRepository::delete,
                () -> {throw new ResourceException("Category not found!");}
            );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
