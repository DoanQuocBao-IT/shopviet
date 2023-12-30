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
    @JoinColumn(name = "consignee_id")
    private Consignee consignee;
    @OneToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;
    public double getTotalPrice() {
        return this.productType.getPrice() * this.quantity;
    }
    @ManyToOne
    @JoinColumn(name = "shipper_id")
    private User shipper;
    private String status= Status.Pending.getOrderStatus();
    private boolean isFeedback = false;
    private Date createdAt;
    private Date shippedAt;
    private Date paymentAt;
    private Date receivedAt;
}
