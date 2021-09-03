package com.shop.cartservice.properties;

import java.util.Set;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class DefaultCartProperties {

    private Set<String> products;
}
