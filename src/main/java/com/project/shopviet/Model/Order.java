package com.project.shopviet.Model;

import com.project.shopviet.DTO.Status;
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
@Table(name="order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @OneToOne
    @JoinColumn(name = "consignee_id")
    private Consignee consignee;

    @OneToMany
    @JoinColumn(name = "cart_item_id")
    private List<CartItem> cartItems;

    @OneToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;

    private String status = Status.Pending.getOrderStatus();
    private double totalPrice;
    private Date createDateTime;
    private Date shipDateTime;
    private Date paymentDateTime;
    private Date receiveDateTime;

}
