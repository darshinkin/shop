package com.shop.orderservice.controllers.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto<T> {

    private List<Message> messages;
    private Error error;
    private String errStatus;
    private T entity;
}
