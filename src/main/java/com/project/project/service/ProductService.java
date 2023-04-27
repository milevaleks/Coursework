package com.project.project.service;

import com.project.project.controller.binding.RegisterProductDto;
import com.project.project.domain.Category;
import com.project.project.domain.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> findProductByCode(String code, int page);
    void saveProduct(RegisterProductDto dto);
    Page<Product> findAllProducts(int page);
    Product findProductById(String id);
    void deleteById(String id);
    Page<Product> findAllByTitleStartingWithAndCategory(String name, Category code, int page);
    Page<Product>findAllByTitleStartingWith(String title, int page);
    Page<Product>findAllByCategory(Category category, int page);
}
