package com.project.shopviet.JWT;

import com.project.shopviet.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
public class JwtResponse {
    private int id;
    private String fullName;
    private String image;
    private Set<Role> roles;

    private final String accessToken;
    private String refreshToken;

    public JwtResponse(int id,String accessToken,String refreshToken, String fullName, String image, Set<Role> roles) {
        this.id=id;
        this.accessToken=accessToken;
        this.refreshToken=refreshToken;
        this.fullName = fullName;
        this.image = image;
        this.roles = roles;
    }
    public JwtResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
