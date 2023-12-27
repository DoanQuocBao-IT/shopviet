package com.project.shopviet.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ConsigneeDto {
    private String name;
    private String phone;
    private String email;
    private String province;
    private String district;
    private String ward;
    private String address;
}
