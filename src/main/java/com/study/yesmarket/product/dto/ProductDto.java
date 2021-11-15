package com.study.yesmarket.product.dto;

import lombok.Builder;
import lombok.Getter;

public class ProductDto {

    @Getter
    public static class RegistRequest {

        private int productId;
        private String name;
        private int price;
        private int stock;

        @Builder
        public RegistRequest(int productId, String name, int price, int stock) {
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
