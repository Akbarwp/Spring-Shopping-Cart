package com.spring.springshoppingcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.springshoppingcart.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryName(String category);

    List<Product> findByBrand(String brand);

    List<Product> findByCategoryNameAndBrand(String category, String brand);

    @Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%', :name, '%')")
    List<Product> findByName(String name);

    @Query("SELECT p FROM Product p WHERE p.brand = :brand AND p.name LIKE CONCAT('%', :name, '%')")
    List<Product> findByBrandAndName(String brand, String name);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.brand = :brand AND p.name LIKE CONCAT('%', :name, '%')")
    Long countByBrandAndName(String brand, String name);
}
