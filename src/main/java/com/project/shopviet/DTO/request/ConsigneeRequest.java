package com.project.shopviet.DTO.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsigneeRequest {
    private String name;
    private String phone;
    private String email;
    private String province;
    private String district;
    private String precinct;
    private String address;
}
