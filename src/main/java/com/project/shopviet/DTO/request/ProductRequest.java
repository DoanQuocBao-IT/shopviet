package com.project.shopviet.DTO.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductRequest {
    private int id;
    private String name;
    private String description;
    private String province;
    private int discount;
    private int brand_id;
    private int category_id;
    private List<ProductTypeRequest> productTypes;
}
