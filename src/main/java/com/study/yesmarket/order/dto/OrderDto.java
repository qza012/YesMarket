package com.study.yesmarket.order.dto;

import com.study.yesmarket.member.domain.Member;
import com.study.yesmarket.product.domain.Product;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderDto {

    @Getter
    @EqualsAndHashCode
    public static class RegisterRequest {

        @NotNull(message = "상품 아이디를 입력해주세요.")
        private Integer productId;

        @NotNull(message = "상품 수량을 입력해주세요.")
        @Min(value = 0, message = "입력 가능 최소 수량은 0입니다.")
        private Integer productCount;

        @Builder
        public RegisterRequest(Integer productId, Integer productCount) {
            this.productId = productId;
            this.productCount = productCount;
        }
    }

    @Getter
    @EqualsAndHashCode
    public static class RegisterResponse {

        private int orderId;
        private String memberId;
        private int productId;
        private int productCount;

        @Builder
        public RegisterResponse(int orderId, String memberId, int productId, int productCount) {
            this.orderId = orderId;
            this.memberId = memberId;
            this.productId = productId;
            this.productCount = productCount;
        }
    }
}
