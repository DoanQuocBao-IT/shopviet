package com.project.shopviet.DTO.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SellerProductRequest {
    private int seller_id;
    private List<ItemCartRequest> products;
}
