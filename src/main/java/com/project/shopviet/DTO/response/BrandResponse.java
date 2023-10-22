package com.project.shopviet.DTO.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandResponse {
    private int id;
    private String name;
    private String image;
    private int sold;
    private int total_product;
}
