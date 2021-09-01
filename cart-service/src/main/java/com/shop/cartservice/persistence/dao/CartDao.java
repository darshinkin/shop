package com.shop.cartservice.persistence.dao;

import com.shop.cartservice.persistence.models.Cart;
import com.shop.cartservice.persistence.repositories.CartRepository;
import com.shop.cartservice.persistence.repositories.ProductRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CartDao {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public Cart getCartById(long cartId) {
        return cartRepository.findByCartId(cartId);
    }

    public Cart createCart() {
        return cartRepository.save(Cart.builder().build());
    }
}
