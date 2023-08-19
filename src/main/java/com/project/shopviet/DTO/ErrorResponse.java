package com.project.shopviet.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String status;
    private String detail;

    public ErrorResponse(String status, String detail) {
        this.status = status;
        this.detail = detail;
    }
}