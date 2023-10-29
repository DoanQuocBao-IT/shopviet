package com.project.shopviet.Model;

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
@Table(name="cart_seller_item")
public class CartSellerItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @OneToMany
    @JoinColumn(name = "cart_id")
    private List<CartItem> cartItem;

    @OneToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;

}
