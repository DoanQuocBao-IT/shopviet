package com.project.shopviet.Controller;

import com.project.shopviet.Model.Product;
import com.project.shopviet.Model.Role;
import com.project.shopviet.Service.ProductService;
import com.project.shopviet.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@CrossOrigin("*")
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping("/add")
    public Role addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }
    @GetMapping("/all")
    public List<Role> getAllRole(){
        return roleService.getAllRole();
    }

}
