package com.project.shopviet.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BrandProductDto {
    private int id;
    private String name;
    private String image;
    private List<ProductDto> products;
}
