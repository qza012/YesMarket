package com.study.yesmarket.order.controller.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

public class OrderDto {

    @Getter
    @EqualsAndHashCode
    public static class RegisterRequest {

        private Integer productId;

        private Integer productCount;
    }
}
