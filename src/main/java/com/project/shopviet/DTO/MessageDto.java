package com.project.shopviet.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MessageDto {
    private int id;
    private UserSellerDto sender;

    private String content;
    private Date createAt;
}
