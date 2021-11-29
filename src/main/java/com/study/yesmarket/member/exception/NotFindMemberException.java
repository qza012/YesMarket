package com.study.yesmarket.member.exception;

import com.study.yesmarket.common.exception.BusinessException;

public class NotFindMemberException extends BusinessException {

    public NotFindMemberException() {
        super(MemberErrorCode.NotFind_Member);
    }
}
