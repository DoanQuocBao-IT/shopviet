package com.project.shopviet.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="brand")
public class Brand {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("brands")
    private Category category;


    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    @JsonIgnoreProperties("brand")
    private List<Product> products;
    @Transient
    private int sold;
    @Transient
    private int total_product;
    public int getSold() {
        int sold = 0;
        for (Product product : products) {
            sold += product.getSold();
        }
        return sold;
    }
    public int getTotal_product() {
        if (products == null) {
            return 0;
        }
        return products.size();
    }
}
