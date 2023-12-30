package com.project.shopviet.DTO;

import com.project.shopviet.DTO.response.UserSellerResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BrandProductDto {
    private int id;
    private String name;
    private String image;
    private UserSellerResponse user_seller;
    private List<ProductDto> products;
}
