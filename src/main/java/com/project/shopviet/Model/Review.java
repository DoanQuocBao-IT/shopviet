package com.project.shopviet.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int rating;
    private Date createTime;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userBuyer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String feedback;
    @OneToOne
    @JoinColumn(name = "orderItem_id")
    @JsonIgnore
    private OrderItem orderItem;
}
