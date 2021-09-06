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
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class OrderController {

    private final OrderService orderService;

    @PostMapping(path = "${app.api.endpoint.order.checkout}")
    public ResponseEntity<ResponseDto<Order>> checkout(@RequestBody CartRequest cartRequest) {
        log.info("REST method 'checkout' has been invoked. Cart request: {}", cartRequest);
        Optional<Order> maybeOrder = orderService.checkout(cartRequest);
        log.info("Order: {}", maybeOrder.map(Order::toString).orElse("Order was not been processed"));
        return log.traceExit(maybeOrder.map(order -> ResponseEntity.ok(ResponseDto.<Order>builder()
                        .entity(order)
                        .messages(List.of(Message.builder()
                                .message(String.format("The order %s was created successfully. ", order.getOrderId()))
                                .status(HttpStatus.OK)
                                .build()))
                        .build()))
                .orElseGet(() -> ResponseEntity.badRequest().build()));
    }
}
