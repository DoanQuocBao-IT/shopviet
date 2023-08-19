package com.project.shopviet.DTO;

import com.project.shopviet.Model.Role;
import lombok.Getter;

import java.util.Set;

@Getter
public class AccessToken {
    private final String accessToken;

    public AccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
