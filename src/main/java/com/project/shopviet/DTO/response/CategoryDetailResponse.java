package com.project.shopviet.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class CategoryDetailResponse {
    private int id;
    private String name;
    private String image;
    private String description;
    private int status;
    private int priority;
    private Date created_at;
    private Date updated_at;
    private List<CategoryDetailResponse> child;
}
