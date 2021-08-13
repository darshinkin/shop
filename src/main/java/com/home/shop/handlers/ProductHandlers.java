package com.home.shop.handlers;

import com.home.shop.persistence.models.Product;
import com.home.shop.routers.models.ProductCreateRequest;
import com.home.shop.routers.models.ProductResponse;
import com.home.shop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProductHandlers {

    public static final String PATH_VARIABLE_ID = "id";
    private final ProductService productService;

    public Mono<ServerResponse> getProductById(ServerRequest serverRequest) {
        String productId = serverRequest.pathVariable(PATH_VARIABLE_ID);
        Mono<ProductResponse> productMono = productService.retrieveProductById(Long.parseLong(productId));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productMono, Product.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createProduct(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(ProductCreateRequest.class)
                .flatMap(productCreateRequest -> {
                    Mono<ProductResponse> productMono = productService.save(productCreateRequest);
                    return ServerResponse
                            .ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(productMono, ProductResponse.class);
                });
    }
}
