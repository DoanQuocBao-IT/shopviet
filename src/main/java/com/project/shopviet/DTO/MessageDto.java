package com.project.shopviet.DTO;

import com.project.shopviet.DTO.response.UserSellerResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MessageDto {
    private int id;
    private UserSellerResponse sender;

    private String content;
    private Date createAt;
}
