package com.study.yesmarket.cart.exception;

import com.study.yesmarket.common.exception.InvalidValueException;

public class NotFindCartException extends InvalidValueException {
    public NotFindCartException() {
        super(CartErrorCode.NotFind_Cart);
    }
}
