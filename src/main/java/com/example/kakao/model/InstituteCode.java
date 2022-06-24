package com.example.kakao.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RequiredArgsConstructor
@Getter
public enum InstituteCode {


    NHUF("주택도시기금", 2),
    KB("국민은행", 3),
    WB("우리은행", 4),
    SB("신한은행", 5),
    CB("한국시티은행", 6),
    HB("하나은행", 7),
    NH("농협은행/수협은행", 8),
    KEB("외한은행",9),
    ETC("기타은행",10)

    ;


    private String bankName;
    private Integer bankCode;

    private static final Map<Integer, String> BY_CODE =
            Stream.of(values()).collect(Collectors.toMap(InstituteCode::getBankCode, String::valueOf));

    public static String valueOfCode(Integer code){
        return BY_CODE.get(code);
    }
    InstituteCode(String bankName, Integer bankCode) {
        this.bankName = bankName;
        this.bankCode = bankCode;
    }


    public String getName(){
        return bankName;
    }

    public String getBankName() {
        return bankName;
    }
}
