package com.project.shopviet.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductDetailResponse {
    private int id;
    private String name;
    private int discount;
    private int inventory;
    private int sold;
    private String description;
    private double rate;
    private UserSellerResponse seller;
    private BrandResponse brand;
    private CategoryResponse category;
    private List<ProductTypeResponse> productTypes;
}
