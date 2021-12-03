package com.study.yesmarket.member.exception;

import com.study.yesmarket.common.exception.BusinessException;

public class NotMatchedIdException extends BusinessException {

    public NotMatchedIdException() {
        super(MemberErrorCode.NotMatchedId);
    }
}
