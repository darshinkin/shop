package com.shop.orderservice.controllers.dto;

import com.shop.orderservice.domain.Cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CartRequest {

    private Cart cart;
}
