package com.project.shopviet.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseObject {
    private int code;
    private String message;
    private Object data;

    public ResponseObject() {
    }

    public ResponseObject(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseObject(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
