package com.study.yesmarket.product.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductDto {

    @Getter
    @EqualsAndHashCode
    public static class RegisterRequest {

        @NotBlank(message = "상품 이름을 입력해주세요")
        private String name;

        @NotNull(message = "상품 가격을 입력해주세요")
        private Integer price;

        @NotNull(message = "상품 수량을 입력해주세요")
        @Min(value = 0, message = "입력 가능 최소 수량은 0입니다.")
        private Integer stock;

        @NotNull(message = "분류 코드를 입력해주세요.")
        private String categoryCode;

        @Builder
        public RegisterRequest(String name, Integer price, Integer stock, String categoryCode) {
            this.name = name;
            this.price = price;
            this.stock = stock;
            this.categoryCode = categoryCode;
        }
    }

    @Getter
    @EqualsAndHashCode
    public static class RegisterResponse {

        private int productId;
        private String name;
        private int price;
        private int stock;

        @Builder
        public RegisterResponse(int productId, String name, int price, int stock) {
            this.productId = productId;
            this.name = name;
            this.price = price;
            this.stock = stock;
        }
    }

    @Getter
    @EqualsAndHashCode
    public static class GetProductResponse {

        private int productId;
        private String name;
        private int price;
        private int stock;

        @Builder
        public GetProductResponse(int productId, String name, int price, int stock) {
            this.productId = productId;
            this.name = name;
            this.price = price;
            this.stock = stock;
        }
    }

    @Getter
    @EqualsAndHashCode
    public static class UpdateProductRequest {

        private String name;
        private int price;
        private int stock;
        private String categoryCode;

        @Builder
        public UpdateProductRequest(String name, int price, int stock, String categoryCode) {
            this.name = name;
            this.price = price;
            this.stock = stock;
            this.categoryCode = categoryCode;
        }
    }

    @Getter
    @EqualsAndHashCode
    public static class UpdateProductResponse {

        private int productId;
        private String name;
        private int price;
        private int stock;

        @Builder
        public UpdateProductResponse(int productId, String name, int price, int stock) {
            this.productId = productId;
            this.name = name;
            this.price = price;
            this.stock = stock;
        }
    }
}
