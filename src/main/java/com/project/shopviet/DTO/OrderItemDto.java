package com.project.shopviet.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderItemDto {
    private int id;
    private ProductDto product;
    private int quantity;
    private double totalPrice;
    private OrderDetailDto orderDetail;
    private Date createDateTime;

}
