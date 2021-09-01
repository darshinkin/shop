package com.shop.cartservice.controllers.dto;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {

    private String message;
    private HttpStatus status;
}
