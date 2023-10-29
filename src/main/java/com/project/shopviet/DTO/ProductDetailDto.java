package com.project.shopviet.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductDetailDto {
    private int id;
    private String name;
    private int price;
    private int discount;
    private int inventory;
    private String severity;
    private int sold;
    private String description;
    private String image;
    private double rate;
    private UserSellerDto seller;
    private BrandDto brand;
    private List<ReviewDto> reviews;
}
