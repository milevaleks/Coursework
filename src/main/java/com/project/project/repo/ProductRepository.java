package com.project.project.repo;

import com.project.project.domain.Category;
import com.project.project.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
    Page<Product> findByCode(String code, Pageable pageable);
    Page<Product> findAllByTitleContainingIgnoreCaseAndCategory(String name, Category category, Pageable pageable);
    Page<Product> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Product>findAllByCategory(Category category, Pageable pageable);
}
