package com.project.shopviet.DTO.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private int consigneeId;
    private List<ItemCartRequest> products;
}
