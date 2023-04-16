package com.project.shopviet.DTO;

import com.project.shopviet.Model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartDto {
    private int id;
    private ProductDto product;
    private int quantity;
    private double totalPrice;
}
