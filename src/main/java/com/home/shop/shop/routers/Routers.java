package com.home.shop.shop.routers;

import com.home.shop.shop.handlers.ProductHandlers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class Routers {

    @Value("${app.api.endpoint.product.retrieve_by_id}")
    private String uriGetProduct;

    @Value("${app.api.endpoint.product.create_product}")
    private String uriCreateProduct;

    @Bean
    public RouterFunction<ServerResponse> productRouter() {
        RequestPredicate getProduct = RequestPredicates
                .GET(this.uriGetProduct);
        RequestPredicate createProduct = RequestPredicates
                .POST(this.uriCreateProduct);
        return RouterFunctions
                .route(getProduct, productHandlers()::getProductById)
                .andRoute(createProduct, productHandlers()::getProductById);
    }

    @Bean
    public ProductHandlers productHandlers() {
        return new ProductHandlers();
    }
}
