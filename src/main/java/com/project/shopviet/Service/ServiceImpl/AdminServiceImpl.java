package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.Model.Admin;
import com.project.shopviet.Repository.AdminRepository;
import com.project.shopviet.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Override
    public Admin getAdminProfile(String admin_userName) {
        return null;
    }

    @Override
    public Admin addAccountAdmin(Admin admin) {
        return adminRepository.save(admin);
    }
}
