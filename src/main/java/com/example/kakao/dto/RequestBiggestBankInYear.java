package com.example.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class RequestBiggestBankInYear implements Serializable {

    private Integer year;

    public RequestBiggestBankInYear(Integer year) {
        this.year = year;
    }
}
