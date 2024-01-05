package com.project.shopviet.DTO.response;

import com.project.shopviet.Model.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class LoginResponse {
    private int id;
    private String full_name;
    private String image;
    private Set<Role> roles;
    private String access_token;
    private String refresh_token;
}
