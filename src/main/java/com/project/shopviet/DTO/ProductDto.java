package com.project.shopviet.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private int product_id;
    private String name;
    private int price;
    private int discount;
    private int sold;
    private double rate;
    private String image;
}
