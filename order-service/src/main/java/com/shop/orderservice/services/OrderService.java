package com.shop.orderservice.services;

import java.util.Optional;

import com.shop.orderservice.controllers.dto.CartRequest;
import com.shop.orderservice.domain.Order;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

@Log4j2
@RequiredArgsConstructor
public class OrderService {

    private final SendMessageRequest.Builder sendMessageRequestBuilder;
    private final SqsClient sqsClient;

    public Optional<Order> checkout(CartRequest cartRequest) {
        log.traceEntry();
        SendMessageRequest sendMessageRequest = sendMessageRequestBuilder.messageBody(cartRequest.toString()).build();
        log.info("SendMessageRequest has been created: {}", sendMessageRequest);
        SendMessageResponse sendMessageResponse = sqsClient.sendMessage(sendMessageRequest);
        log.info("Request hase been sent to SQS. Response: {}", sendMessageResponse);
        return log.traceExit(Optional.ofNullable(sendMessageResponse.messageId())
                .map(messageId -> Optional.of(Order.builder().orderId(sendMessageResponse.messageId()).build()))
                .orElseGet(() -> {
                    log.error("Error occurred during sending message SQS. CartRequest: {}", cartRequest);
                    return Optional.empty();
                }));
    }
}
