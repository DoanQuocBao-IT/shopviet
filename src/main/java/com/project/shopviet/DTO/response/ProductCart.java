package com.project.shopviet.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductCart {
    private int id;
    private String name;
    private int price;
    private int discount;
    private String image;
    private int inventory;
    private int quantity;
    private double totalPrice;
}
