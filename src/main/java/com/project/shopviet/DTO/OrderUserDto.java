package com.project.shopviet.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderUserDto {
    private int id;

    private String consigneeName;
    private String consigneePhone;
    private String consigneeEmail;
    private String consigneeAddress;
    private List<ItemDto> orderItems;
    private double totalPrice;
}
