package com.project.shopviet.DTO.response;

import com.project.shopviet.DTO.ProductDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Builder
public class CartResponse {
    private SellerResponse seller;
    private List<ItemCartResponse> products;
}
