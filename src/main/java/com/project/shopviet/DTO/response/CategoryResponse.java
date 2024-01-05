package com.project.shopviet.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CategoryResponse {
    private int id;
    private String name;
    private String image;
    private List<CategoryResponse> child;
}
