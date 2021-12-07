package com.study.yesmarket.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.yesmarket.member.service.LoginService;
import com.study.yesmarket.product.dto.ProductDto.GetProductResponse;
import com.study.yesmarket.product.dto.ProductDto.RegisterRequest;
import com.study.yesmarket.product.dto.ProductDto.UpdateProductRequest;
import com.study.yesmarket.product.dto.ProductDto.UpdateProductResponse;
import com.study.yesmarket.product.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(ProductController.class)
@MockBean(JpaMetamodelMappingContext.class)
class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("로그인 중일 때")
    @Nested
    class Login {

        @DisplayName("상품 등록을 등록할 수 있다.")
        @Test
        void registerProduct() throws Exception {
            // given
            RegisterRequest registerRequest = RegisterRequest.builder()
                    .name("테스트 상품")
                    .price(2000)
                    .stock(15)
                    .build();

            // when
            ResultActions perform = mockMvc.perform(post("/products")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(registerRequest))
                    .sessionAttr(LoginService.LOGIN_ID, "testId"));

            // then
            perform.andExpect(status().isCreated());

            verify(productService, times(1)).registerProduct(registerRequest);
        }

        @DisplayName("상품을 조회할 수 있다.")
        @Test
        void getProduct() throws Exception {
            // given
            int productId = 1;
            GetProductResponse response = GetProductResponse.builder()
                    .productId(1)
                    .name("테스트 상품")
                    .price(1000)
                    .stock(10)
                    .build();

            given(productService.getProduct(productId)).willReturn(response);

            // when
            ResultActions perform = mockMvc.perform(get("/products/{productId}", productId)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .sessionAttr(LoginService.LOGIN_ID, "testId"));

            // then
            perform.andExpect(status().isOk())
                    .andExpect(content().string(objectMapper.writeValueAsString(response)));

            verify(productService, times(1)).getProduct(productId);
        }

        @DisplayName("상품 정보를 업데이트할 수 있다.")
        @Test
        void updateProduct() throws Exception {
            // given
            int productId = 1;
            UpdateProductRequest request = UpdateProductRequest.builder()
                    .name("업데이트 상품")
                    .price(2000)
                    .stock(20)
                    .build();
            UpdateProductResponse response = UpdateProductResponse.builder()
                    .productId(1)
                    .name("업데이트 상품")
                    .price(2000)
                    .stock(20)
                    .build();

            given(productService.updateProduct(productId, request)).willReturn(response);

            // when
            ResultActions perform = mockMvc.perform(put("/products/{productId}", productId)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(request))
                    .sessionAttr(LoginService.LOGIN_ID, "testId"));

            // then
            perform.andExpect(status().isOk());

            verify(productService, times(1)).updateProduct(productId, request);
        }

        @DisplayName("상품 삭제를 할 수 있다.")
        @Test
        void deleteProduct() throws Exception {
            // given
            int productId = 1;

            // when
            ResultActions perform = mockMvc.perform(delete("/products/{productId}", productId)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .sessionAttr(LoginService.LOGIN_ID, "testId"));

            //then
            perform.andExpect(status().isOk());

            verify(productService, times(1)).deleteProduct(productId);
        }
    }

    @DisplayName("로그인 중이 아닐 때")
    @Nested
    class NotLogin {

        @DisplayName("상품을 등록하면, 'UnAuthorizationExceptioon' 예외가 발생한다.")
        @Test
        void registerProduct() throws Exception {
            // given
            RegisterRequest registerRequest = RegisterRequest.builder()
                    .name("테스트 상품")
                    .price(2000)
                    .stock(15)
                    .build();

            // when
            ResultActions perform = mockMvc.perform(post("/products")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(registerRequest)));

            // then
            perform.andExpect(status().isUnauthorized());

            verify(productService, never()).registerProduct(registerRequest);
        }

        @DisplayName("상품을 조회할 수 있다.")
        @Test
        void getProduct() throws Exception {
            // given
            int productId = 1;
            GetProductResponse response = GetProductResponse.builder()
                    .productId(1)
                    .name("테스트 상품")
                    .price(1000)
                    .stock(10)
                    .build();

            given(productService.getProduct(productId)).willReturn(response);

            // when
            ResultActions perform = mockMvc.perform(get("/products/{productId}", productId)
                    .contentType(MediaType.APPLICATION_JSON_VALUE));

            // then
            perform.andExpect(status().isOk())
                    .andExpect(content().string(objectMapper.writeValueAsString(response)));

            verify(productService, times(1)).getProduct(productId);
        }

        @DisplayName("상품 정보를 업데이트하면, 'UnAuthorizationExceptioon' 예외가 발생한다.")
        @Test
        void updateProduct() throws Exception {
            // given
            int productId = 1;
            UpdateProductRequest request = UpdateProductRequest.builder()
                    .name("업데이트 상품")
                    .price(2000)
                    .stock(20)
                    .build();
            UpdateProductResponse response = UpdateProductResponse.builder()
                    .productId(1)
                    .name("업데이트 상품")
                    .price(2000)
                    .stock(20)
                    .build();

            given(productService.updateProduct(productId, request)).willReturn(response);

            // when
            ResultActions perform = mockMvc.perform(put("/products/{productId}", productId)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(request)));

            // then
            perform.andExpect(status().isUnauthorized());

            verify(productService, never()).updateProduct(productId, request);
        }

        @DisplayName("상품을 삭제하면, 'UnAuthorizationExceptioon' 예외가 발생한다.")
        @Test
        void deleteProduct() throws Exception {
            // given
            int productId = 1;

            // when
            ResultActions perform = mockMvc.perform(delete("/products/{productId}", productId)
                    .contentType(MediaType.APPLICATION_JSON_VALUE));

            //then
            perform.andExpect(status().isUnauthorized());

            verify(productService, never()).deleteProduct(productId);
        }
    }
}