package com.project.shopviet.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Seller {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long sId;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    User user;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable
    @JsonIgnore
    List<Product> products;

}

