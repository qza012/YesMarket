package com.study.yesmarket.common.exception;

import com.study.yesmarket.common.exception.errorcode.BusinessErrorCode;

public class EntityNotFoundException extends BusinessException{

    public EntityNotFoundException(BusinessErrorCode code) {
        super(code);
    }
}
