package com.project.shopviet.DTO;

import com.project.shopviet.Model.Role;
import lombok.Getter;

import java.util.Set;

@Getter
public class AccessToken {
    private final String access_token;

    public AccessToken(String access_token) {
        this.access_token = access_token;
    }
}
