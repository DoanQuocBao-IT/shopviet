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
    private int discount = 0;
    private int sold = 0;
    private int inventory;
    @Column(length = 1000)
    private String description;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
    private Date createdAt;
    private Date updatedAt;
    private double rate = 0.0;
    private int totalFeedback = 0;
    private int status = 1;
    private String reasonBlock;
    private String province;
    @Override
    public String toString() {
        return " ID: " +id+
                "\n\t   Name: " + name +
                "\n\t   Sold: " + sold +
                "\n\t   Rate"+ rate;
    }
}
