package com.project.shopviet.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailMessage {
    private String to;
    private String subject;
    private String message;
}
