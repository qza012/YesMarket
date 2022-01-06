package com.study.yesmarket.product.exception;

import com.study.yesmarket.common.exception.BusinessException;

public class NotFindCategoryException extends BusinessException {

    public NotFindCategoryException() {
        super(CategoryErrorCode.NotFindCategory);
    }
}
