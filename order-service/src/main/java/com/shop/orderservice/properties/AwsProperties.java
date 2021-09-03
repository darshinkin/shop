package com.shop.orderservice.properties;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class AwsProperties {

    private String region;
    private String queueName;
}
