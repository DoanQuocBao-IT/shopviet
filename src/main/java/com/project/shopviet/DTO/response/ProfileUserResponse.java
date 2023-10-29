package com.project.shopviet.DTO.response;

import com.project.shopviet.DTO.OrderDetailDto;
import com.project.shopviet.DTO.OrderItemDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProfileUserResponse {
    private int id;
    private String fname;
    private String email;
    private String phone;
    private String image;
    private String birthday;
    private String sex;
    private String follower;
    private String following;
    private List<OrderDetailDto> orderDetail;
    private List<OrderItemDto> orderItem;
}
