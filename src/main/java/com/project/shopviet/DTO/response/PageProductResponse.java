package com.project.shopviet.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PageProductResponse {
    private int per_page;
    private int current_page;
    private int total_page;
    private long total_product;
    private Object data;
}
