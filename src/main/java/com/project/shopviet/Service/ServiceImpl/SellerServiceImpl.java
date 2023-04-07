package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.Model.Seller;
import com.project.shopviet.Repository.SellerRepository;
import com.project.shopviet.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerRepository sellerRepository;
    @Override
    public Seller addRegisterSeller(Seller seller) {
        return null;
    }

    @Override
    public String deleteSeller(int id) {
        return null;
    }

    @Override
    public Seller updateUser(int id, Seller user) {
        return null;
    }

    @Override
    public List<Seller> getAllSeller() {
        return (List<Seller>) sellerRepository.findAll();
    }

    @Override
    public Optional<Seller> getSellerById(int id) {
        return sellerRepository.findById(id);
    }

    @Override
    public Seller getSellerProfile(String seller_userName) {
        return null;
    }

    @Override
    public Optional<Seller> getSellerByUsername(String username) {
        return sellerRepository.findSellerByUsername(username);
    }

    @Override
    public boolean isExistById(int id) {
        return false;
    }
}
