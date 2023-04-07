package com.project.shopviet.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {
    private String username;
    private String password;
    private String fname;
    private String email;
    private String phone;
    private String image;
    private String roleName;
}
