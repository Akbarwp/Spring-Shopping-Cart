package com.spring.springshoppingcart.dto;

import java.math.BigDecimal;
import java.util.List;

import com.spring.springshoppingcart.model.Category;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;
    private List<ImageDto> images;
}
