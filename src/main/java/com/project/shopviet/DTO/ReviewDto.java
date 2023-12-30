package com.project.shopviet.DTO;

import com.project.shopviet.DTO.response.UserSellerResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ReviewDto {
    private int id;
    private int rating;
    private Date createTime;
    private String comment;
    private UserSellerResponse userBuyer;
    private String feedback;
}
