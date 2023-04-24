package com.project.shopviet.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private int id;
    private String name;
    private int price;
    private int sold;
    private double rate;
    private String image;
}
