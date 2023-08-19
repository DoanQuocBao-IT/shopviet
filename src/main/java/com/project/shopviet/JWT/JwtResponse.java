package com.project.shopviet.JWT;

import com.project.shopviet.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
public class JwtResponse {
    private String fullName;
    private String image;
    private Set<Role> role;

    private final String accessToken;
    private String refreshToken;

    public JwtResponse(String accessToken,String refreshToken, String fullName, String image, Set<Role> role) {
        this.accessToken=accessToken;
        this.refreshToken=refreshToken;
        this.fullName = fullName;
        this.image = image;
        this.role = role;
    }
    public JwtResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
