package com.project.shopviet.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sellers")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameStore;
    private String image;
    private int totalProduct;
    private int totalFollow;
    private int totalFollower;
    private int totalFeedback;
    private double rate;
    @Column(length = 1000)
    private String description;
    private boolean mall = false;
    private int status = 1;
    private String reasonBlock;
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;
    private String address;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
