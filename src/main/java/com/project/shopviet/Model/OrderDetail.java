package com.project.shopviet.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String consigneeName;
    private String consigneePhone;
    private String consigneeEmail;
    private String consigneeAddress;

    @ManyToOne
    @JoinColumn(name = "userBuyer_id")
    private User userBuyer;

    @JoinColumn(name = "orderDetail_id")
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("orderDetail")
    private List<OrderItem> orderItems;

    public double getTotalPrice() {
        double totalPrice = 0;
        if (this.orderItems ==null){
            return 0;
        }else {
            for (OrderItem orderItem : this.orderItems) {
                totalPrice += orderItem.getTotalPrice();
            }
        }

        return totalPrice;

    }
}
