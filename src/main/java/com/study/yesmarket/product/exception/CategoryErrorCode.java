package com.study.yesmarket.product.exception;

import com.study.yesmarket.common.exception.errorcode.BusinessErrorCode;
import org.springframework.http.HttpStatus;

public enum CategoryErrorCode implements BusinessErrorCode {

    NotFindCategory(HttpStatus.BAD_REQUEST, "잘못된 카테고리 입니다.")
    ;

    private HttpStatus status;
    private String message;

    CategoryErrorCode(HttpStatus status, String message) {
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
