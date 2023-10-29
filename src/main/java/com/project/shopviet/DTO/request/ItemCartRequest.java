package com.project.shopviet.DTO.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCartRequest {
    private int product_id;
    private int quantity;
}
