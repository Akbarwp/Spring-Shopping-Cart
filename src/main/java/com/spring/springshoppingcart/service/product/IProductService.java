package com.spring.springshoppingcart.service.product;

import java.util.List;

import com.spring.springshoppingcart.dto.ProductDto;
import com.spring.springshoppingcart.model.Product;
import com.spring.springshoppingcart.request.AddProductRequest;
import com.spring.springshoppingcart.request.UpdateProductRequest;

public interface IProductService {
    Product getProductById(Long id);
    Product addProduct(AddProductRequest request);
    Product updateProductById(UpdateProductRequest request, Long productId);

    void deleteProductById(Long id);

    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String brand, String name);

    Long countProductsByBrandAndName(String brand, String name);

    List<ProductDto> getConvertedProducts(List<Product> products);
    ProductDto convertToDto(Product product);
}
