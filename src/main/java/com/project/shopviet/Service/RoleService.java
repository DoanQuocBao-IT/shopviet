package com.project.shopviet.Service;

import com.project.shopviet.Model.Role;

import java.util.List;

public interface RoleService {
    Role findRoleByName(String name);
    Role addRole(Role role);
    List<Role> getAllRole();
}
