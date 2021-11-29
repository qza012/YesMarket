package com.study.yesmarket.cart.service;

import com.study.yesmarket.cart.domain.Cart;
import com.study.yesmarket.cart.domain.CartRepository;
import com.study.yesmarket.cart.exception.NotFindCartException;
import com.study.yesmarket.product.domain.Product;
import com.study.yesmarket.product.domain.ProductRepository;
import com.study.yesmarket.product.exception.NotFindProductException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void addProduct(int cartId, int productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(NotFindCartException::new);
        Product product = productRepository.findById(productId)
                .orElseThrow(NotFindProductException::new);

        cart.addProduct(product);
    }

    @Transactional
    public void removeProduct(int cartId, int productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(NotFindCartException::new);
        Product product = productRepository.findById(productId)
                .orElseThrow(NotFindProductException::new);

        cart.removeProduct(product);
    }
}
