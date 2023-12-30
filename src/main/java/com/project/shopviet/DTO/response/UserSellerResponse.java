package com.project.shopviet.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class UserSellerResponse {
    private int id;
    private String name_store;
    private String image;
    private int totalProduct;
    private int totalFollower;
    private int totalFeedback;
    private boolean mall;
    private Date createdAt;
}
