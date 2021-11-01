package com.study.yesmarket.common.exception.handler;

import com.study.yesmarket.common.exception.BusinessException;
import com.study.yesmarket.common.exception.errorcode.BusinessErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 런타임 에러
     * @param e RuntimeError
     * @return ResponseEntity
     */
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<String> handleBusinessException(BusinessException e) {
        log.error("handleBusinessException" ,e);
        BusinessErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(errorCode.getMessage(), errorCode.getStatus());
    }

    /**
     * @param e validation error
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException : {}", e.getFieldError().getDefaultMessage());
        return new ResponseEntity<>(e.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }
}
