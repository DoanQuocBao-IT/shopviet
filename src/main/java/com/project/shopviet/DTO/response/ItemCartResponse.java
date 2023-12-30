package com.project.shopviet.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ItemCartResponse {
    private int id;
    private String name;
    private ProductTypeResponse product_type;
    private int quantity;
    private Long total_price;
}
