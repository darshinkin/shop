package com.shop.cartservice.services;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.shop.cartservice.DefaultCartProperties;
import com.shop.cartservice.persistence.dao.CartDao;
import com.shop.cartservice.persistence.models.Cart;
import com.shop.cartservice.persistence.models.Product;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CartService {

    private final CartDao cartDao;
    private final DefaultCartProperties defaultCartProperties;

    public Optional<Cart> retrieveCart(long cartId) {
        var cart = cartDao.getCartById(cartId);
        Cart resultCart = Optional.ofNullable(cart).orElseGet(cartDao::createCart);
        return Optional.of(resultCart);
    }

    public Optional<Long> deleteCart(long cartId) {
        cartDao.deleteCartById(cartId);
        return Optional.of(cartId);
    }

    public Optional<Cart> populateCartByDefaultProducts(long cartId) {
        var maybeCart = cartDao.getCartById(cartId);
        return Optional.ofNullable(maybeCart).map(cart -> {
            Set<String> productNames = defaultCartProperties.getProducts();
            productNames.stream()
                    .map(productName -> Product.builder()
                            .productName(productName)
                            .cart(cart)
                            .build())
                    .peek(cart::addProduct)
                    .collect(Collectors.toSet());
            return cartDao.saveCart(cart);
        });
    }
}
