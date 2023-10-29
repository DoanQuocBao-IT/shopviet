package com.project.shopviet.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CartResponse {
    private List<SellerProductResponse> sellerProduct;
    private int totalProduct;
    private int totalSeller;
}
