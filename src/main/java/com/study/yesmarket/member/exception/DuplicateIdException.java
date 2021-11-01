package com.study.yesmarket.member.exception;

import com.study.yesmarket.common.exception.InvalidValueException;

public class DuplicateIdException extends InvalidValueException {

    public DuplicateIdException() {
        super(MemberErrorCode.DUPLICATE_ID);
    }
}
