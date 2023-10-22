package com.project.shopviet.DTO.response;

import com.project.shopviet.DTO.ProductDto;
import com.project.shopviet.DTO.ProductImageDto;
import com.project.shopviet.DTO.UserSellerDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BrandProductResponse {
    private int brand_id;
    private String name;
    private String image;
    private UserSellerDto user_seller;
    private List<ProductImageDto> products;
}
