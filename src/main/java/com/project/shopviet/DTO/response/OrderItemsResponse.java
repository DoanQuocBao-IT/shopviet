package com.project.shopviet.DTO.response;

import com.project.shopviet.DTO.OrderDetailDto;
import com.project.shopviet.DTO.ProductDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class OrderItemsResponse {
    private int id;
    private ProductDto product;
    private int quantity;
    private double totalPrice;
    private String status;
    private Date createDateTime;
}
