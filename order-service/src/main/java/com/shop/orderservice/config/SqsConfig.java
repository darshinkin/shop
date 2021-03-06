package com.shop.orderservice.config;

import java.net.URI;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shop.orderservice.properties.AwsProperties;

import lombok.extern.log4j.Log4j2;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Configuration
@Log4j2
public class SqsConfig {

    @Bean
    @ConfigurationProperties(prefix = "app.aws")
    public AwsProperties awsProperties() {
        return new AwsProperties();
    }

    @Bean
    SqsClient sqsClient() {
        String region = awsProperties().getRegion();
        String endpointUri = awsProperties().getEndpointUri();
        log.info("Setup AWS region: {}", region);
        log.info("Setup AWS endpoint: {}", endpointUri);
        return SqsClient.builder()
                .region(Region.regions().stream()
                        .filter(awsRegion -> awsRegion.id().equals(region))
                        .findFirst().orElseThrow())
                .endpointOverride(URI.create(endpointUri))
                .build();
    }

    @Bean
    GetQueueUrlRequest getQueueUrlRequest() {
        String queueName = awsProperties().getQueueName();
        log.info("Setup SQS queue: {}", queueName);
        return GetQueueUrlRequest.builder()
                .queueName(queueName)
                .build();
    }

    @Bean
    SendMessageRequest.Builder sendMessageRequestBuilder() {
        String queueUrl = sqsClient().getQueueUrl(getQueueUrlRequest()).queueUrl();
        log.info("Setup SQS queue url: {}", queueUrl);
        return SendMessageRequest.builder().queueUrl(queueUrl);
    }
}
