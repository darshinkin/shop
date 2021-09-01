package com.shop.cartservice.persistence.dao;

import java.util.Optional;

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

    public Cart saveCart(Cart cart) {
        return cartRepository.saveAndFlush(cart);
    }

    public Cart createCart() {
        return cartRepository.save(Cart.builder().build());
    }

    public void deleteCartById(long cartId) {
        cartRepository.deleteById(cartId);
    }
}
