package com.study.yesmarket.member.exception;

import com.study.yesmarket.common.exception.errorcode.BusinessErrorCode;
import org.springframework.http.HttpStatus;

public enum MemberErrorCode implements BusinessErrorCode {

    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "중복된 닉네임이 있습니다."),
    DUPLICATE_ID(HttpStatus.BAD_REQUEST, "중복된 아이디가 있습니다."),
    NotFind_Member(HttpStatus.BAD_REQUEST, "해당 아이디의 유저가 존재하지 않습니다."),
    NotMatchedId(HttpStatus.BAD_REQUEST, "유저 아이디가 일치하지 않습니다."),
    NotMatchedPassword(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    UnAuthorization(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");

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
