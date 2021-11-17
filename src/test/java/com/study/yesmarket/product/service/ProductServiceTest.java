package com.study.yesmarket.product.service;

import com.study.yesmarket.product.domain.Product;
import com.study.yesmarket.product.domain.ProductRepository;
import com.study.yesmarket.product.dto.ProductDto.GetProductResponse;
import com.study.yesmarket.product.dto.ProductDto.RegisterRequest;
import com.study.yesmarket.product.dto.ProductDto.RegisterResponse;
import com.study.yesmarket.product.dto.ProductDto.UpdateProductRequest;
import com.study.yesmarket.product.exception.NotFindProductException;
import com.study.yesmarket.product.mapper.ProductMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @DisplayName("상품 등록에 성공한다.")
    @Test
    void registProduct() {
        // given
        RegisterRequest registerRequest = RegisterRequest.builder()
                .name("테스트 상품")
                .price(1000)
                .stock(10)
                .build();

        Product product = ProductMapper.INSTANCE.registerRequestToEntity(registerRequest);
        RegisterResponse registerResponse = ProductMapper.INSTANCE.productToRegisterResponse(product);

        given(productMapper.registerRequestToEntity(registerRequest)).willReturn(product);
        given(productRepository.save(product)).willReturn(product);
        given(productMapper.productToRegisterResponse(product)).willReturn(registerResponse);

        // when
        RegisterResponse actual = productService.registerProduct(registerRequest);

        // then
        assertThat(actual.getName()).isEqualTo(registerResponse.getName());
        assertThat(actual.getPrice()).isEqualTo(registerResponse.getPrice());
        assertThat(actual.getStock()).isEqualTo(registerResponse.getStock());

        verify(productMapper, times(1)).registerRequestToEntity(registerRequest);
        verify(productRepository, times(1)).save(product);
        verify(productMapper, times(1)).productToRegisterResponse(product);
    }

    @DisplayName("상품을 가져올 때")
    @Nested
    class GetProduct {

        @DisplayName("요청한 상품이 존재하면, 해당 상품을 반환한다.")
        @Test
        void findProduct() {
            // given
            Product product = Product.builder()
                    .productId(1)
                    .name("테스트 상품")
                    .price(1000)
                    .stock(10)
                    .build();

            GetProductResponse getProductResponse = ProductMapper.INSTANCE.productToGetProductResponse(product);

            given(productRepository.findById(product.getProductId()))
                    .willReturn(Optional.ofNullable(product));
            given(productMapper.productToGetProductResponse(product))
                    .willReturn(getProductResponse);

            // when
            GetProductResponse actual = productService.getProduct(product.getProductId());

            // then
            assertThat(actual.getProductId()).isEqualTo(getProductResponse.getProductId());
            assertThat(actual.getName()).isEqualTo(getProductResponse.getName());
            assertThat(actual.getPrice()).isEqualTo(getProductResponse.getPrice());
            assertThat(actual.getStock()).isEqualTo(getProductResponse.getStock());

            verify(productRepository, times(1)).findById(product.getProductId());
            verify(productMapper, times(1)).productToGetProductResponse(product);
        }

        @DisplayName("요청한 상품이 존재하지 않는다면, 'NotFindProductException' 에러가 발생한다.")
        @Test
        void notFindProduct() {
            // given
            Product product = Product.builder()
                    .productId(1)
                    .build();

            given(productRepository.findById(product.getProductId())).willReturn(Optional.empty());

            // when-then
            assertThrows(NotFindProductException.class, () -> productService.getProduct(product.getProductId()));

            verify(productRepository, times(1)).findById(product.getProductId());
        }


    }

    @DisplayName("상품 정보를 업데이트할 때")
    @Nested
    class updateProduct {

        @DisplayName("요청한 상품을 존재하면, 업데이트에 성공한다.")
        @Test
        void findProduct() {
            // given
            int productId = 1;
            UpdateProductRequest updateProductRequest = UpdateProductRequest.builder()
                    .name("테스트 상품")
                    .price(1000)
                    .stock(10)
                    .build();

            Product product = Product.builder()
                    .productId(1)
                    .name("기존 상품")
                    .price(500)
                    .stock(5)
                    .build();

            given(productRepository.findById(productId))
                    .willReturn(Optional.ofNullable(product));

            // when
            productService.updateProduct(productId, updateProductRequest);

            // then
            assertThat(product.getName()).isEqualTo(updateProductRequest.getName());
            assertThat(product.getPrice()).isEqualTo(updateProductRequest.getPrice());
            assertThat(product.getStock()).isEqualTo(updateProductRequest.getStock());

            verify(productRepository, times(1)).findById(productId);
            verify(productMapper, times(1)).productToUpdateProductResponse(product);
        }

        @DisplayName("요청한 상품이 존재하지 않는다면, 'NotFindProductException' 에러가 발생한다.")
        @Test
        void notFindProduct() {
            // given
            int productId = 1;
            UpdateProductRequest updateProductRequest = UpdateProductRequest.builder()
                    .name("테스트 상품")
                    .price(1000)
                    .stock(10)
                    .build();

            given(productRepository.findById(productId)).willReturn(Optional.empty());

            // when-then
            assertThrows(NotFindProductException.class, () -> productService.updateProduct(productId, updateProductRequest));

            verify(productRepository, times(1)).findById(productId);
        }
    }

    @DisplayName("상품 삭제에 성공한다.")
    @Test
    void deleteProduct() {
        // given
        int productId = 111;

        // when
        productService.deleteProduct(productId);

        // then
        verify(productRepository, times(1)).deleteById(productId);
    }
}