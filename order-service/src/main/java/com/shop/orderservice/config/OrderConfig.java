package com.shop.orderservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.shop.orderservice.services.OrderService;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Configuration
@Import(SqsConfig.class)
public class OrderConfig {

    @Autowired
    private SendMessageRequest.Builder sendMessageRequestBuilder;

    @Autowired
    private SqsClient sqsClient;

    @Bean
    OrderService orderService() {
        return new OrderService(sendMessageRequestBuilder, sqsClient);
    }

}
