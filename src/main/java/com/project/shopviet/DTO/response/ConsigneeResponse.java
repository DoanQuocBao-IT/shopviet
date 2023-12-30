package com.project.shopviet.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ConsigneeResponse {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String province;
    private String district;
    private String precinct;
    private String address;
}
