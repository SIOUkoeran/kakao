package com.example.kakao.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseKEBInfo implements Serializable {
    private String bank;

    private List<SupportAmount> supportAmount;

    public ResponseKEBInfo(String bank, List<SupportAmount> supportAmount) {
        this.bank = bank;
        this.supportAmount = supportAmount;
    }

    @Getter
    @NoArgsConstructor
    public static class SupportAmount implements Serializable{
        private Integer year;
        private Integer amount;

        public SupportAmount(Integer year, Integer amount) {
            this.year = year;
            this.amount = amount;
        }
    }


}
