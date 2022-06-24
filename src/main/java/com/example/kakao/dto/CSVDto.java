package com.example.kakao.dto;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class CSVDto {

    @DateTimeFormat(pattern = "yyyy-MM")
    private LocalDate date;
    private String amount;
    private Integer bankCode;

    public CSVDto(LocalDate date, String amount, Integer bankCode) {
        this.date = date;
        this.amount = amount;
        this.bankCode = bankCode;
    }
}
