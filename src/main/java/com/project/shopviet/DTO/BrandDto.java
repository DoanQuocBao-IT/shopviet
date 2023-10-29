package com.project.shopviet.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BrandDto {
    private int id;
    private String name;
    private String image;
}
