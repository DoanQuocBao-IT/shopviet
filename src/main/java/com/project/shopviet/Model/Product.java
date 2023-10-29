package com.project.shopviet.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int price;
    private int discount;
    private int sold;
    private int inventory;
    private String description;
    private String image;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    @JsonIgnoreProperties("products")
    private Brand brand;

    @ManyToOne
    private User userSeller;
    private Date createdAt;
    private Date updatedAt;
    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("product")
    private List<Review> reviews;
    private double rate;

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        double rate = 0;
        if (this.reviews!=null){
            for (Review review : this.reviews) {
                rate += review.getRating();
            }
            if (reviews.size()==0){
                return rate;
            }
            return rate/reviews.size();
        }

        return rate;
    }
    @Override
    public String toString() {
        return " ID: " +id+
                "\n\t   Name: " + name +
                "\n\t   Price: " + price +
                "\n\t   Sold: " + sold +
                "\n\t   Rate"+ rate +
                "\n\t   Image : '" + image;
    }
}
