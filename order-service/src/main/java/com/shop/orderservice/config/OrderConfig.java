package com.shop.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shop.orderservice.services.OrderService;

@Configuration
public class OrderConfig {

    @Bean
    OrderService orderService() {
        return new OrderService();
    }
}
