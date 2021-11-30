package com.study.yesmarket.cart.controller;

import com.study.yesmarket.cart.domain.Cart;
import com.study.yesmarket.cart.service.CartService;
import com.study.yesmarket.product.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest({
        CartController.class,
})
@MockBean(JpaMetamodelMappingContext.class)
class CartControllerTest {

    @MockBean
    private CartService cartService;

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("장바구니에 물품 등록을 성공한다.")
    @Test
    void addProduct() throws Exception {
        // given
        Cart cart = Cart.builder()
                .cartId(1)
                .build();
        Product product = Product.builder()
                .productId(2)
                .build();

        // when
        ResultActions perform = mockMvc.perform(
                post("/carts/{cartId}/products/{productId}", cart.getCartId(), product.getProductId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE));

        // then
        perform.andExpect(status().isCreated());

        verify(cartService, times(1)).addProduct(cart.getCartId(), product.getProductId());
    }

    @DisplayName("장바구니에 있는 물품을 삭제한다.")
    @Test
    void removeProduct() throws Exception {
        // given
        Cart cart = Cart.builder()
                .cartId(1)
                .build();
        Product product = Product.builder()
                .productId(2)
                .build();

        // when
        ResultActions perform = mockMvc.perform(
                delete("/carts/{cartId}/products/{productId}", cart.getCartId(), product.getProductId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE));

        // then
        perform.andExpect(status().isNoContent());

        verify(cartService, times(1)).removeProduct(cart.getCartId(), product.getProductId());
    }
}