package com.example.kakao.dto;

import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class ResponseSupply implements Serializable {

    private String name;
    private List<SupplyDto> supplies;

    public ResponseSupply(String name, List<SupplyDto> supplies) {
        this.name = name;
        this.supplies = supplies;
    }
}
