package com.shop.orderservice.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {

    private String orderId;
}
