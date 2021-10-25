package com.study.yesmarket.common.exception.errorcode;

import org.springframework.http.HttpStatus;

/**
 *
 */
public interface BusinessErrorCode {

    HttpStatus getStatus();

    String getMessage();
}
