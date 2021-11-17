package com.study.yesmarket.product.exception;

import com.study.yesmarket.common.exception.BusinessException;

public class NotFindProductException extends BusinessException {

    public NotFindProductException() {
        super(ProductErrorCode.NotFindProduct);
    }

}
