package com.shop.cartservice.persistence.dao;

import com.shop.cartservice.persistence.models.Cart;
import com.shop.cartservice.persistence.repositories.CartRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CartDao {

    private final CartRepository cartRepository;

    public Cart getCartById(long cartId) {
        return cartRepository.findByCartId(cartId);
    }

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart createCart() {
        return cartRepository.save(new Cart());
    }

    public void deleteCartById(long cartId) {
        cartRepository.deleteById(cartId);
    }
}
