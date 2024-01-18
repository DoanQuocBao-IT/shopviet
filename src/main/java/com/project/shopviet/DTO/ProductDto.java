package com.project.shopviet.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Getter
@Setter
public class ProductDto {
    private int id;
    private String name;
    private int discount;
    private int sold;
    private double rate;
    private String image;
    private Long price;
    private String province;
}
