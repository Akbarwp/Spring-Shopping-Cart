package com.spring.springshoppingcart.service.product;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springshoppingcart.dto.ImageDto;
import com.spring.springshoppingcart.dto.ProductDto;
import com.spring.springshoppingcart.exception.ResourceException;
import com.spring.springshoppingcart.model.Category;
import com.spring.springshoppingcart.model.Image;
import com.spring.springshoppingcart.model.Product;
import com.spring.springshoppingcart.repository.CategoryRepository;
import com.spring.springshoppingcart.repository.ImageRepository;
import com.spring.springshoppingcart.repository.ProductRepository;
import com.spring.springshoppingcart.request.AddProductRequest;
import com.spring.springshoppingcart.request.UpdateProductRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final ImageRepository imageRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new ResourceException("Product not found!"));
    }

    @Override
    @Transactional
    public Product addProduct(AddProductRequest request) {
        // check if category exists
        // if Yes, set as new product category
        // if No, save as a new category
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
            .orElseGet(() -> {
                Category newCategory = new Category(request.getCategory().getName());
                return categoryRepository.save(newCategory);
            });

        request.setCategory(category);
        return productRepository.save(createProduct(request, category));
    }

    private Product createProduct(AddProductRequest request, Category category) {
        return new Product(
            request.getName(),
            request.getBrand(),
            request.getPrice(),
            request.getInventory(),
            request.getDescription(),
            category
        );
    }

    @Override
    @Transactional
    public Product updateProductById(UpdateProductRequest request, Long productId) {
        return productRepository.findById(productId)
            .map(existingProduct -> updateExistingProduct(existingProduct, request))
            .map(productRepository::save)
            .orElseThrow(() -> new ResourceException("Product not found!"));
    }

    private Product updateExistingProduct(Product existingProduct, UpdateProductRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);

        return existingProduct;
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        productRepository.findById(id)
            .ifPresentOrElse(
                productRepository::delete,
                () -> {throw new ResourceException("Product not found!");}
            );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

    @Override
    public List<ProductDto> getConvertedProducts(List<Product> products) {
        return products.stream().map(this::convertToDto).toList();
    }

    @Override
    public ProductDto convertToDto(Product product) {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        List<Image> images = imageRepository.findByProductId(product.getId());
        List<ImageDto> imageDtos = images.stream()
                .map(image -> modelMapper.map(image, ImageDto.class))
                .toList();
        productDto.setImages(imageDtos);
        return productDto;
    }
}
