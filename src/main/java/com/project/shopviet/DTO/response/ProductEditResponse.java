package com.project.shopviet.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductEditResponse {
    private int id;
    private String name;
    private int discount;
    private String description;
    private String province;
    private BrandResponse brand;
    private CategoryResponse category;
}
