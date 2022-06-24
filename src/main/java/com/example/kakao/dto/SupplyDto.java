package com.example.kakao.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;


@Getter
@NoArgsConstructor
public class SupplyDto {
    private String year;
    private Integer maxAmount;
    private Map<String, Integer> detailAmount = new HashMap<>();

    public SupplyDto(String year, Integer maxAmount, Map<String, Integer> detailAmount) {
        this.year = year;
        this.maxAmount = maxAmount;
        this.detailAmount = detailAmount;
    }
    public SupplyDto(String year){
        this.year = year;
    }

    public void addMap(String key, Integer value){
        if (detailAmount.containsKey(key)){
            Integer amount = detailAmount.get(key);

            detailAmount.put(key, amount + value);
        }else{
            detailAmount.put(key, value);
        }
    }

    public void setMaxAmount(Integer max){
        this.maxAmount = max;
    }

}
