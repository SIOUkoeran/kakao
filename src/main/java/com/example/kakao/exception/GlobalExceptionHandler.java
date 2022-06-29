package com.example.kakao.exception;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NotFoundBankException.class)
    @ResponseStatus(HttpStatus.OK)
    protected ErrorResponse notFoundBankExceptionHandler(NotFoundBankException e){
        log.error("Not FoundBankException : {} ", e.getMessage());
        return new ErrorResponse(ExceptionCode.NOT_FOUND_BANK);
    }
}
