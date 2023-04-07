package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.Model.Product;
import com.project.shopviet.Model.Role;
import com.project.shopviet.Repository.RoleRepository;
import com.project.shopviet.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findRoleByName(String name) {
        try {
            return roleRepository.findRoleByName(name);
        }catch (IllegalArgumentException e){
            System.out.println("Find Role Error: "+e.getMessage());
            return null;
        }
    }

    @Override
    public Role addRole(Role role) {
        try{
            return roleRepository.save(role);
        }catch (IllegalArgumentException e){
            System.out.println("Add Role Error: "+e.getMessage());
            return null;
        }
    }

    @Override
    public List<Role> getAllRole() {
        try{
            return (List<Role>) roleRepository.findAll();
        }catch (IllegalArgumentException e){
            System.out.println("Get All Role Error: "+e.getMessage());
            return null;
        }
    }
}
