package com.project.shopviet.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="consignee")
public class Consignee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String phone;
    private String email;
    @OneToOne
    @JoinColumn(name = "area_id")
    private Area area;
    private String address;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User buyer;
}
