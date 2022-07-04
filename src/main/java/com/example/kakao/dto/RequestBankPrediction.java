package com.example.kakao.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Getter
public class RequestBankPrediction implements Serializable {

    private String month;
    private String bank;

    public RequestBankPrediction(String month, String bank) {
        this.month = month;
        this.bank = bank;
    }
}
