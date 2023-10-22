package com.project.shopviet.DTO.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private int id;
    private String name;
    private String description;
    private String image;
    private int price;
    private int quantity;
    private int brand_id;
}
