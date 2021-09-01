package com.shop.cartservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shop.cartservice.persistence.dao.CartDao;
import com.shop.cartservice.persistence.repositories.CartRepository;
import com.shop.cartservice.persistence.repositories.ProductRepository;
import com.shop.cartservice.services.CartService;

@Configuration
public class CartConfig {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Bean
    CartService cartService() {
        return new CartService(cartDao());
    }

    @Bean
    CartDao cartDao() {
        return new CartDao(cartRepository, productRepository);
    }
}
