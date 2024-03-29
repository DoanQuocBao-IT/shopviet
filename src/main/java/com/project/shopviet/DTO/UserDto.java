package com.project.shopviet.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {
    private int id;
    private String fname;
    private String email;
    private String phone;
    private String image;
    private String birthday;
    private String sex;
    private String follower;
    private String following;
}
