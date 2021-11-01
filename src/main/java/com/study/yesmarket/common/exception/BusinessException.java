package com.study.yesmarket.common.exception;

import com.study.yesmarket.common.exception.errorcode.BusinessErrorCode;

public class BusinessException extends RuntimeException{

    private final BusinessErrorCode errorCode;

    public BusinessException(BusinessErrorCode code) {
        super(code.getMessage());
        this.errorCode = code;
    }

    public BusinessErrorCode getErrorCode() {
        return this.errorCode;
    }
}
