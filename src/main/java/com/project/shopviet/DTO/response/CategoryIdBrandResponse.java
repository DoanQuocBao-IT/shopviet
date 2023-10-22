package com.project.shopviet.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CategoryIdBrandResponse {
    private String name;
    private List<BrandProductResponse> brand;
}
