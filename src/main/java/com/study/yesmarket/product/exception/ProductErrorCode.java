package com.study.yesmarket.product.exception;

import com.study.yesmarket.common.exception.errorcode.BusinessErrorCode;
import org.springframework.http.HttpStatus;

public enum ProductErrorCode implements BusinessErrorCode {

    NotFindProduct(HttpStatus.BAD_REQUEST, "등록된 상품 정보가 없습니다.")
    ;

    private HttpStatus status;
    private String message;

    ProductErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
