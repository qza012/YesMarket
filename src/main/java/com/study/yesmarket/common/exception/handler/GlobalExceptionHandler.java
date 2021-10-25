package com.study.yesmarket.common.exception.handler;

import com.study.yesmarket.common.exception.BusinessException;
import com.study.yesmarket.common.exception.errorcode.BusinessErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /*
    런타임 에러
     */
    @ExceptionHandler()
    protected ResponseEntity<String> handleBusinessException(BusinessException e) {
        log.error("handleBusinessException", e);
        BusinessErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(errorCode.getMessage(), errorCode.getStatus());
    }
}
