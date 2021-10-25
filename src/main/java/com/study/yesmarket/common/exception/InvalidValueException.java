package com.study.yesmarket.common.exception;

import com.study.yesmarket.common.exception.errorcode.BusinessErrorCode;

public class InvalidValueException extends BusinessException {

    public InvalidValueException(BusinessErrorCode code) {
        super(code);
    }
}
