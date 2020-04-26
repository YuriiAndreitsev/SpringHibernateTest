package com.repository;

import com.model.Category;
import com.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

//    public List<Product> getProductsByCategories(String category);
public List<Product> getProductsByCategories(Category category);
    public Product getProductsById(int id);
}
