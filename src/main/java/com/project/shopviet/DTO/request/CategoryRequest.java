package com.project.shopviet.DTO.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {
    private String name;
    private String image;
    private String description;
    private int parent_id;
}
