package com.project.shopviet.DTO;

import com.project.shopviet.DTO.response.SellerProductResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderDto {
    private ConsigneeDto consignee;
    private SellerProductResponse products;
    private double totalPrice;
    private String status;
    private String createDateTime;
    private String shipDateTime;
    private String paymentDateTime;
    private String receiveDateTime;
}
