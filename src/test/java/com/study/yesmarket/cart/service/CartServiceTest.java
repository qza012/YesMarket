package com.study.yesmarket.cart.service;

import com.study.yesmarket.cart.domain.Cart;
import com.study.yesmarket.cart.domain.CartRepository;
import com.study.yesmarket.product.domain.Product;
import com.study.yesmarket.product.domain.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CartService cartService;

    @DisplayName("장바구니에 물품 등록을 성공한다.")
    @Test
    void addProduct() {
        // given
        Cart cart = Cart.builder()
                .cartId(1)
                .build();
        Product product = Product.builder()
                .productId(2)
                .build();

        given(cartRepository.findById(cart.getCartId())).willReturn(Optional.ofNullable(cart));
        given(productRepository.findById(product.getProductId())).willReturn(Optional.ofNullable(product));

        // when
        cartService.addProduct(cart.getCartId(), product.getProductId());

        // then
        assertThat(cart.getProducts().size()).isEqualTo(1);

        verify(cartRepository, times(1)).findById(cart.getCartId());
        verify(productRepository, times(1)).findById(product.getProductId());
    }

    @DisplayName("장바구니에 있는 물품을 삭제한다.")
    @Test
    void removeProduct() {
        // given
        Cart cart = Cart.builder()
                .cartId(1)
                .build();
        Product product = Product.builder()
                .productId(2)
                .build();
        cart.addProduct(product);

        given(cartRepository.findById(cart.getCartId())).willReturn(Optional.ofNullable(cart));
        given(productRepository.findById(product.getProductId())).willReturn(Optional.ofNullable(product));

        // when
        cartService.removeProduct(cart.getCartId(), product.getProductId());

        // then
        assertThat(cart.getProducts().size()).isEqualTo(0);

        verify(cartRepository, times(1)).findById(cart.getCartId());
        verify(productRepository, times(1)).findById(product.getProductId());
    }
}