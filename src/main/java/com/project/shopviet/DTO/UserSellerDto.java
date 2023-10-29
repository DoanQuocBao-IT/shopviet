package com.project.shopviet.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserSellerDto {
    private int id;
    private String fname;
    private String image;
    private String address;
    private String follower;
    private String following;
}
