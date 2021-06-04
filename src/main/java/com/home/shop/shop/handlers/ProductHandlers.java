package com.home.shop.shop.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProductHandlers {
    public Mono<ServerResponse> getProductById(ServerRequest serverRequest) {
        return Mono.empty();
    }

    public Mono<ServerResponse> createProduct(ServerRequest serverRequest) {
        return Mono.empty();
    }
}
