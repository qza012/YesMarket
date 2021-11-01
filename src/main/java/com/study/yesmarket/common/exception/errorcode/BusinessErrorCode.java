package com.study.yesmarket.common.exception.errorcode;

import org.springframework.http.HttpStatus;

/**
 * 전략 패턴 (Strategy Pattern)을 위한 인터페이스
 * Error Code를 도메인별로 관리하기 위함.
 */
public interface BusinessErrorCode {

    HttpStatus getStatus();

    String getMessage();
}
