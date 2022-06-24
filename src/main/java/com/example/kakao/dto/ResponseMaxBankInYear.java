package com.example.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Getter
public class ResponseMaxBankInYear implements Serializable {

    @JsonProperty("bank_name")
    private String bankName;
    private Integer year;

    public ResponseMaxBankInYear(String bankName, Integer year) {
        this.bankName = bankName;
        this.year = year;
    }
}
