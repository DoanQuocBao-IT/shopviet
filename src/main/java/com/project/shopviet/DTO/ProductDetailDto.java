package com.project.shopviet.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDetailDto {
    private int id;
    private String name;
    private int price;
    private int inventory;
    private int sold;
    private String description;
    private String image;
    private double rate;
    private UserSellerDto userSeller;
    private BrandDto brand;
    private List<ReviewDto> reviews;
}
