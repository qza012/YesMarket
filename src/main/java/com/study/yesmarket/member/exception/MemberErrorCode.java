package com.study.yesmarket.member.exception;

import com.study.yesmarket.common.exception.errorcode.BusinessErrorCode;
import org.springframework.http.HttpStatus;

public enum MemberErrorCode implements BusinessErrorCode {

    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "중복된 닉네임입니다.");

    private HttpStatus status;
    private String message;

    MemberErrorCode(HttpStatus status, String message) {
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
