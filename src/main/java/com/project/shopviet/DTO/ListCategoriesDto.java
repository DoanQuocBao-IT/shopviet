package com.project.shopviet.DTO;

import com.project.shopviet.DTO.response.CategoryResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListCategoriesDto {
    private List<CategoryResponse> categories;
}
