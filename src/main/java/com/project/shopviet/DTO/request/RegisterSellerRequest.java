package com.project.shopviet.DTO.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterSellerRequest {
    private String name_store;
    private String image;
    private String description;
    private String address;
    private String province;
    private String district;
    private String precinct;
}
