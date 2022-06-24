package com.example.kakao.dto;

import com.example.kakao.model.Bank;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Getter
public class ResponseBank implements Serializable {

    @JsonProperty("bank_name")
    private String bankName;

    @JsonProperty("bank_code")
    private Integer bankCode;

    public ResponseBank(Bank bank) {
        this.bankName = bank.getInstituteName();
        this.bankCode = bank.getInstituteCode();
    }
}
