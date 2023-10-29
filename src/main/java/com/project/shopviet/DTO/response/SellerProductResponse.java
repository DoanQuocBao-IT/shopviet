package com.project.shopviet.DTO.response;

import com.project.shopviet.DTO.UserSellerDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SellerProductResponse {
    private UserSellerDto seller;
    private List<ProductCart> products;
}
