package com.example.kakao.exception;

public class NotFoundBankException extends CustomException{
    public NotFoundBankException(ExceptionCode exceptionCode) {
        super(exceptionCode.getExceptionMessage());
    }
}
