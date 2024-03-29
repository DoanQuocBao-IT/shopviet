package com.project.shopviet.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductTypeResponse {
    private int id;
    private String name;
    private String image;
    private Long price;
    private int quantity;
}
