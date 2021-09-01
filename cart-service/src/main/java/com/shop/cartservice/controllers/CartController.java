package com.shop.cartservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.cartservice.controllers.dto.Message;
import com.shop.cartservice.controllers.dto.ResponseDto;
import com.shop.cartservice.persistence.models.Cart;
import com.shop.cartservice.services.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping(path = "${app.api.endpoint.cart.retrieve.byCartId}")
    public ResponseEntity<ResponseDto<Cart>> retrieveListing(@PathVariable long cartId) {
        Optional<Cart> maybeCart = cartService.retrieveCart(cartId);
        return maybeCart.map(cart -> ResponseEntity.ok(ResponseDto.<Cart>builder()
                        .entity(cart)
                        .messages(List.of(Message.builder()
                                .message(cartId == cart.getCartId() ? String.format("The cart %d was received successfully. ", cart.getCartId())
                                        : String.format("The cart %d had not found, created new cart with id: %d", cartId, cart.getCartId()))
                                .status(HttpStatus.OK)
                                .build()))
                        .build()))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping(path = "${app.api.endpoint.cart.delete.byCartId}")
    public ResponseEntity<ResponseDto<Long>> deleteCart(@PathVariable long cartId) {
        Optional<Long> maybeCartId = cartService.deleteCart(cartId);
        return maybeCartId.map(id -> ResponseEntity.ok(ResponseDto.<Long>builder()
                        .entity(id)
                        .messages(List.of(Message.builder()
                                .message(String.format("The cart %s was deleted successfully.", id))
                                .status(HttpStatus.OK)
                                .build()))
                        .build()))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping(path = "${app.api.endpoint.cart.update.putDefaultProducts}")
    public ResponseEntity<ResponseDto<Cart>> populateCartByDefaultProducts(@PathVariable long cartId) {
        Optional<Cart> maybeCart = cartService.populateCartByDefaultProducts(cartId);
        return maybeCart.map(cart -> ResponseEntity.ok(ResponseDto.<Cart>builder()
                        .entity(cart)
                        .messages(List.of(Message.builder()
                                .message(String.format("The cart %s was populated successfully. ", cart.getCartId()))
                                .status(HttpStatus.OK)
                                .build()))
                        .build()))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
