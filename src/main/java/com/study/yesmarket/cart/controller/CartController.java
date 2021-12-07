package com.study.yesmarket.cart.controller;

import com.study.yesmarket.cart.service.CartService;
import com.study.yesmarket.common.interceptor.ForLoginMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @ForLoginMember
    @PostMapping("/{cartId}/products/{productId}")
    public ResponseEntity<Void> addProduct(@PathVariable int cartId, @PathVariable int productId) {
        cartService.addProduct(cartId, productId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ForLoginMember
    @DeleteMapping("/{cartId}/products/{productId}")
    public ResponseEntity<Void> removeProduct(@PathVariable int cartId, @PathVariable int productId) {
        cartService.removeProduct(cartId, productId);
        return ResponseEntity.noContent().build();
    }
}
