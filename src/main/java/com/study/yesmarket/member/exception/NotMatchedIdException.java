package com.study.yesmarket.member.exception;

import com.study.yesmarket.common.exception.BusinessException;
import com.study.yesmarket.login.exception.LoginErrorCode;

public class NotMatchedIdException extends BusinessException {

    public NotMatchedIdException() {
        super(LoginErrorCode.NotMatchedId);
    }
}
