package com.example.kakao.exception;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class ErrorResponse implements Serializable {
    private Integer code;
    private String exceptionMessage;
    private LocalDateTime date;

    public ErrorResponse(ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.exceptionMessage = exceptionCode.getExceptionMessage();
        this.date = LocalDateTime.now();
    }
}
