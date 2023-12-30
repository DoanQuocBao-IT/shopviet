package com.project.shopviet.DTO.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductTypeRequest {
    private String name;
    private String image;
    private Long price;
    private int quantity;
}
