package com.study.yesmarket.member.exception;

import com.study.yesmarket.common.exception.InvalidValueException;

public class DuplicateNicknameException extends InvalidValueException {

    public DuplicateNicknameException() {
        super(MemberErrorCode.DUPLICATE_NICKNAME);
    }
}
