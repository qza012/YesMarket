package com.study.yesmarket.member.exception;

import com.study.yesmarket.common.exception.BusinessException;

public class NotMatchedPasswordException extends BusinessException {

    public NotMatchedPasswordException() {
        super(MemberErrorCode.NotMatchedPassword);
    }
}
