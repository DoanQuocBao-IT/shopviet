package com.project.shopviet.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ProfileSellerResponse {
    private int id;
    private String name_store;
    private String image;
    private int totalProduct;
    private int totalFollow;
    private int totalFollower;
    private int totalFeedback;
    private double rate;
    private boolean mall;
    private Date createdAt;
    private Date updatedAt;
    private String description;
    private String address;
    private String phone;
}
