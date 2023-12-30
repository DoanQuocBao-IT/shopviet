package com.project.shopviet.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductTypeDto {
    private int id;
    private String name;
    private String image;
    private int price;
    private int quantity;
}
