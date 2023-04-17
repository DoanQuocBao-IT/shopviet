package com.project.shopviet.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.shopviet.DTO.Status;
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
@Table(name="order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "orderDetail_id")
    @JsonIgnoreProperties("orderItems")
    private OrderDetail orderDetail;
    public double getTotalPrice() {
        return this.product.getPrice() * this.quantity;
    }
    @ManyToOne
    @JoinColumn(name = "userShipper_id")
    private User userShipper;

    private String status= Status.Pending.getOrderStatus();
    @OneToOne(mappedBy = "orderItem", cascade = CascadeType.ALL)
    @JsonIgnore
    private Review review;
    private Date createDateTime;

}
