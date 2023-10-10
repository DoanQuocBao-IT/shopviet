package com.project.shopviet.DTO.response;

import com.project.shopviet.DTO.ProductDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PagedProductResponse {
    private int total_page;
    private int current_page;
    private int per_page;
    private Long total_product;
    private List<ProductDto> products;
}
