package com.study.yesmarket.cart.exception;

import com.study.yesmarket.common.exception.errorcode.BusinessErrorCode;
import org.springframework.http.HttpStatus;

public enum CartErrorCode implements BusinessErrorCode {

    NotFind_Cart(HttpStatus.BAD_REQUEST, "해당 유저의 장바구니가 존재하지 않습니다.");

    private HttpStatus status;
    private String message;

    CartErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return this.status;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
