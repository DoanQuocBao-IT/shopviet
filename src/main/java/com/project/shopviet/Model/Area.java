package com.project.shopviet.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int areaId;
    @Column(name = "province")
    private String province;
    @Column(name = "district")
    private String district;
    @Column(name = "precinct")
    private String precinct;
    @Column(name = "name")
    private String name;
    @Column(name = "full_name")
    private String fullName;
    private String status;
}
