package com.project.shopviet.Controller;

import com.project.shopviet.DTO.OrderItemDto;
import com.project.shopviet.DTO.OrderUserDto;
import com.project.shopviet.Model.*;
import com.project.shopviet.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = {"*"})
public class AdminController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    BrandService brandService;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    RoleService roleService;
    @Autowired
    OrderItemService orderItemService;
    @GetMapping("/allOrder")
    public List<OrderUserDto> getAllOrder(){
        return orderService.getAllOrder();
    }
    @GetMapping("/allOrderItem")
    public List<OrderItemDto> getAllOrderItem(){
        return orderItemService.getAllOrderItem();
    }

    @PostMapping("/addBrand")
    public Brand addBrand(@RequestBody Brand brand){
        return brandService.addBrand(brand);
    }
    @DeleteMapping("/delBrand/{id}")
    public String deleteBrand(@PathVariable int id){
        return brandService.deleteBrand(id);
    }
    @PutMapping("/putBrand/{id}")
    public Brand updateBrand(@RequestBody Brand brand,@PathVariable int id){
        return brandService.updateBrand(id, brand);
    }

    @PostMapping("/addCat")
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }
    @DeleteMapping("/delCat/{id}")
    public String deleteCategory(@PathVariable int id){
        return categoryService.deleteCategory(id);
    }
    @PutMapping("/putCat/{id}")
    public Category updateCategory(@RequestBody Category category,@PathVariable int id){
        return categoryService.updateCategory(id, category);
    }


    @GetMapping("/all/{role}")
    public List<User> getUserByRolee(@PathVariable String role){
        Role roleUser=roleService.findRoleByName(role);
        return userService.findUserByRole(roleUser);
    }
    @GetMapping("/all/{role1}/and{role2}")
    public List<User> getUserByRole(@PathVariable String role1,@PathVariable String role2){
        Set<Role> roles = new HashSet<Role>();
        roles.add(roleService.findRoleByName(role1));
        roles.add(roleService.findRoleByName(role2));
        return userService.findByRolesIn(roles);
    }
    @GetMapping("/allUser")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
    @GetMapping("/set/{user_id}/role/{role_id}")
    public String setRole(@PathVariable int user_id,@PathVariable int role_id){
        userService.addRoleToUser(user_id,role_id);
        return "thanh cong";
    }

    @PostMapping("/add/role")
    public Role addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }
    @GetMapping("/all/role")
    public List<Role> getAllRole(){
        return roleService.getAllRole();
    }

    @GetMapping("/approve/user/{id}")
    public void approveSellerOrShipper(@PathVariable int id){
        userService.approveSellerOrShipper(id);
    }
    @GetMapping("/block/user/{id}")
    public void blockSellerOrShipper(@PathVariable int id){
        userService.blockSellerOrShipper(id);
    }
    @GetMapping("/unblock/user/{id}")
    public void unBlockSellerOrShipper(@PathVariable int id){
        userService.unBlockSellerOrShipper(id);
    }
    @GetMapping("/all/approve")
    public List<User> getAllUserApproveSellerOrShipper(){
        return userService.getAllUserApprove();
    }
    @GetMapping("/all/block")
    public List<User> getAllUserBlock(){
        return userService.getAllUserBlock();
    }
    @GetMapping("/profile")
    User getProfileAdmin(){
        return userService.getProfile();
    }
}
