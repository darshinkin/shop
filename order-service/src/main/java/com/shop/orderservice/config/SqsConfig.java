package com.shop.orderservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shop.orderservice.properties.AwsProperties;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Configuration
public class SqsConfig {

    @Bean
    @ConfigurationProperties(prefix = "app.aws")
    public AwsProperties awsProperties() {
        return new AwsProperties();
    }

    @Bean
    SqsClient sqlClient() {
        return SqsClient.builder().region(Region.regions().stream()
                        .filter(region -> region.id().equals(awsProperties().getRegion()))
                        .findFirst().orElseThrow())
                .build();
    }

    @Bean
    GetQueueUrlRequest getQueueUrlRequest() {
        return GetQueueUrlRequest.builder()
                .queueName(awsProperties().getQueueName())
                .build();
    }

    @Bean
    SendMessageRequest.Builder sendMessageRequestBuilder() {
        String queueUrl = sqlClient().getQueueUrl(getQueueUrlRequest()).queueUrl();
        return SendMessageRequest.builder().queueUrl(queueUrl);
    }
}
