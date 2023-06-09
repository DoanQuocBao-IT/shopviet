package com.project.shopviet.DTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CartDto {
    private int id;
    private ProductDto product;
    private int quantity;
    private double totalPrice;
}
