package com.project.project.configuration.init;


import com.project.project.domain.Category;
import com.project.project.domain.Product;
import com.project.project.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductInit implements CommandLineRunner {
    private ProductRepository productRepository;


    public ProductInit(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
            Product productOne = new Product("First test title",
                    "First test description", new ArrayList<>(), new BigDecimal("15.00"),
                    new BigDecimal("20.00"), 5, Category.FOOD, "1234");
            Product productTwo = new Product("Second test title",
                    "Second test description", new ArrayList<>(), new BigDecimal("15.00"),
                    new BigDecimal("20.00"), 5, Category.CANCELAR, "1234567");
            Product productThree = new Product("Third test title",
                    "Third test description", new ArrayList<>(), new BigDecimal("15.00"),
                    new BigDecimal("20.00"), 5, Category.CANCELAR, "123458");
            Product productFour = new Product("Fourth test title",
                    "Fourth test description", new ArrayList<>(), new BigDecimal("15.00"),
                    new BigDecimal("20.00"), 5, Category.CANCELAR, "1234569");
            Product productFive = new Product("Five test title",
                    "Five test description", new ArrayList<>(), new BigDecimal("15.00"),
                    new BigDecimal("20.00"), 5, Category.CANCELAR, "gfdgd");
            Product productSix = new Product("Six test title",
                    "Six test description", new ArrayList<>(), new BigDecimal("15.00"),
                    new BigDecimal("20.00"), 5, Category.CANCELAR, "1234SDa6");
            Product productSeven = new Product("Seventh test title",
                    "Second test description", new ArrayList<>(), new BigDecimal("15.00"),
                    new BigDecimal("20.00"), 5, Category.CANCELAR, "12sdad3456");
            Product productEight = new Product("Eight test title",
                    "Second test description", new ArrayList<>(), new BigDecimal("15.00"),
                    new BigDecimal("20.00"), 5, Category.CANCELAR, "12345DSdas6");
            productRepository.saveAll(List.of(productOne, productTwo,productThree, productFour, productFive,
                    productSix, productSeven, productEight));
        }

    }
}
