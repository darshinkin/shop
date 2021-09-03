package com.shop.orderservice.services;

import java.util.Optional;
import java.util.UUID;

import com.shop.orderservice.controllers.dto.CartRequest;
import com.shop.orderservice.domain.Order;

public class OrderService {

    public Optional<Order> checkout(CartRequest cartRequest) {
        var order = Order.builder().orderId(UUID.randomUUID().toString()).build();
        return Optional.of(order);
    }
}
