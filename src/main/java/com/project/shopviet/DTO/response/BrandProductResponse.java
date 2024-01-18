package com.project.shopviet.DTO.response;

import com.project.shopviet.DTO.ProductDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BrandProductResponse {
    private int id;
    private String name;
    private String image;
    private int total_product;
    List<ProductDto> products;
}
