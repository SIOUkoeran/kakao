package com.example.kakao.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Response {

    private String code;
    private String message;
    private Object data;

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
