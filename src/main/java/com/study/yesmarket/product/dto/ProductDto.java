package com.study.yesmarket.product.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class ProductDto {

    @Getter
    public static class RegistRequest {

        @NotBlank(message = "상품 이름을 입력해주세요")
        private String name;

        @NotBlank(message = "상품 가격을 입력해주세요")
        private int price;

        @NotBlank(message = "상품 수량을 입력해주세요")
        private int stock;

        @Builder
        public RegistRequest(String name, int price, int stock) {
            this.name = name;
            this.price = price;
            this.stock = stock;
        }
    }

    @Getter
    public static class RegistResponse {

        private int productId;
        private String name;
        private int price;
        private int stock;

        @Builder
        public RegistResponse(int productId, String name, int price, int stock) {
            this.productId = productId;
            this.name = name;
            this.price = price;
            this.stock = stock;
        }
    }

    @Getter
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
    public static class UpdateProductRequest {

        @NotBlank(message = "상품 Id를 입력해주세요")
        private int productId;

        private String name;
        private int price;
        private int stock;

        @Builder
        public UpdateProductRequest(int productId, String name, int price, int stock) {
            this.productId = productId;
            this.name = name;
            this.price = price;
            this.stock = stock;
        }
    }
}
