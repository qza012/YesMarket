package com.study.yesmarket.login.exception;

import com.study.yesmarket.common.exception.BusinessException;
import com.study.yesmarket.common.exception.errorcode.BusinessErrorCode;

public class NotMatchedPasswordException extends BusinessException {

    public NotMatchedPasswordException() {
        super(LoginErrorCode.NotMatchedPassword);
    }
}
