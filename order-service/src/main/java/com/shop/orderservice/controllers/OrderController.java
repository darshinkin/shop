package com.shop.orderservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shop.orderservice.controllers.dto.CartRequest;
import com.shop.orderservice.controllers.dto.Message;
import com.shop.orderservice.controllers.dto.ResponseDto;
import com.shop.orderservice.domain.Order;
import com.shop.orderservice.services.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(path = "${app.api.endpoint.order.checkout}")
    public ResponseEntity<ResponseDto<Order>> checkout(@RequestBody CartRequest cartRequest) {
        Optional<Order> maybeOrder = orderService.checkout(cartRequest);
        return maybeOrder.map(order -> ResponseEntity.ok(ResponseDto.<Order>builder()
                        .entity(order)
                        .messages(List.of(Message.builder()
                                .message(String.format("The order %s was created successfully. ", order.getOrderId()))
                                .status(HttpStatus.OK)
                                .build()))
                        .build()))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
