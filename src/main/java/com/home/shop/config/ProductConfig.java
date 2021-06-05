package com.home.shop.config;

import com.home.shop.persistence.dao.ProductDao;
import com.home.shop.persistence.repositories.ProductRepository;
import com.home.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Autowired
    private ProductRepository productRepository;

    @Bean
    public ProductService productService() {
        return new ProductService(productDao());
    }

    @Bean
    public ProductDao productDao() {
        return new ProductDao(productRepository);
    }
}
