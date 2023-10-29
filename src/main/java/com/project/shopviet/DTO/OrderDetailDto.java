package com.project.shopviet.DTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderDetailDto {
    private int id;
    private String consigneeName;
    private String consigneePhone;
    private String consigneeEmail;
    private String consigneeAddress;
}
