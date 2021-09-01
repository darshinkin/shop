package com.shop.cartservice.services;

import java.util.Optional;

import com.shop.cartservice.persistence.dao.CartDao;
import com.shop.cartservice.persistence.models.Cart;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CartService {

    private final CartDao cartDao;

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
        return Optional.empty();
    }
}
