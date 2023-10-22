package com.project.shopviet.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class ProductDto {
    private int id;
    private String name;
    private int price;
    private int discount;
    private int sold;
    private double rate;
    private String image;
    public String getImage(){
        return "http://localhost:8080/images/"+this.image;
    }
}
