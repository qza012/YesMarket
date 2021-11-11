package com.study.yesmarket.login.exception;

import com.study.yesmarket.common.exception.BusinessException;

public class NotMatchedIdException extends BusinessException {

    public NotMatchedIdException() {
        super(LoginErrorCode.NotMatchedId);
    }
}
