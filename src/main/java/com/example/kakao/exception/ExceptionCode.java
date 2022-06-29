package com.example.kakao.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.classfile.Code;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {
    NOT_FOUND_BANK(4040, "Bank Not Found");

    private Integer code;
    private String exceptionMessage;

    ExceptionCode(Integer code, String exceptionMessage) {
        this.code = code;
        this.exceptionMessage = exceptionMessage;
    }
}
