package com.service;


import com.model.Product;
import com.repository.CategoryRepository;
import com.model.Category;
import com.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ImageRepository imageRepository;

    public Product addProduct(Product product, Category category) {
        category.addProduct(product);
        product.addCategories(category);
        categoryRepository.save(category);
        return productRepository.save(product);
    }

    public Optional<Product> getOptionalProductById(int id) {
        return productRepository.findById(id);
    }

    public Product getProductById(int id) {
        return productRepository.getProductsById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategories(String category) {
        return productRepository.getProductsByCategories(categoryRepository.getCategoryByCategory(category));
    }
}
