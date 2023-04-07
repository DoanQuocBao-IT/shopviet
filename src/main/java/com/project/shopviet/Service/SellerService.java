package com.project.shopviet.Service;

import com.project.shopviet.Model.Admin;
import com.project.shopviet.Model.Seller;
import com.project.shopviet.Model.User;

import java.util.List;
import java.util.Optional;

public interface SellerService {
    Seller addRegisterSeller(Seller seller);
    String deleteSeller(int id);
    Seller updateUser(int id,Seller user);
    List<Seller> getAllSeller();
    Optional<Seller> getSellerById(int id);
    Seller getSellerProfile(String seller_userName);
    Optional<Seller> getSellerByUsername(String username);
    boolean isExistById(int id);
}
