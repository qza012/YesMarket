package com.study.yesmarket.member.exception;

import com.study.yesmarket.common.exception.BusinessException;

public class UnAuthorizationExceptioon extends BusinessException {

    public UnAuthorizationExceptioon() {
        super(MemberErrorCode.UnAuthorization);
    }
}
