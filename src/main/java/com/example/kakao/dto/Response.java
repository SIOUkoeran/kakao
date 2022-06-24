package com.example.kakao.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class Response implements Serializable {

    private String code;
    private String message;
    private Object data;

    @Builder
    public Response(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Builder(builderMethodName = "response", buildMethodName = "response")
    public Response(String code, String message){
        this.code = code;
        this.message = message;
        this.data = null;
    }
}
