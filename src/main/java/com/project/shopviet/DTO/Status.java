package com.project.shopviet.DTO;

public enum Status {
    Shipped("Shipped"),
    Approved ("Approved"),
    OnTheWay("On the way"),
    Cancelled("Cancelled"),
    Delivered("Delivered"),
    Pending("Pending"),
    Success("Success");
    private String orderStatus;

    Status(String status){this.orderStatus=status;}

    public String getOrderStatus(){
        return this.orderStatus;
    }
}
