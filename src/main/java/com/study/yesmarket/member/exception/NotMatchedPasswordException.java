package com.study.yesmarket.member.exception;

import com.study.yesmarket.common.exception.BusinessException;
import com.study.yesmarket.common.exception.errorcode.BusinessErrorCode;
import com.study.yesmarket.login.exception.LoginErrorCode;

public class NotMatchedPasswordException extends BusinessException {

    public NotMatchedPasswordException() {
        super(LoginErrorCode.NotMatchedPassword);
    }
}
