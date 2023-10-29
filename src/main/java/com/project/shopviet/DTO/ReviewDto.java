package com.project.shopviet.DTO;

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
    private UserSellerDto userBuyer;
    private String feedback;
}
