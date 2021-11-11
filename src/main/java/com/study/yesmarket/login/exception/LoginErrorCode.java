package com.study.yesmarket.login.exception;

import com.study.yesmarket.common.exception.errorcode.BusinessErrorCode;
import org.springframework.http.HttpStatus;

public enum LoginErrorCode implements BusinessErrorCode {

    NotMatchedId(HttpStatus.BAD_REQUEST, "유저 아이디가 일치하지 않습니다."),
    NotMatchedPassword(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.")
    ;

    private HttpStatus status;
    private String message;

    LoginErrorCode(HttpStatus status, String message) {
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
