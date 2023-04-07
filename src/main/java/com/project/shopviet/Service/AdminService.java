package com.project.shopviet.Service;

import com.project.shopviet.Model.Admin;

public interface AdminService {
    Admin getAdminProfile(String admin_userName);
    Admin addAccountAdmin(Admin admin);
}
