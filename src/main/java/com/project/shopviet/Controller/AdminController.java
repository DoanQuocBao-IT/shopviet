package com.project.shopviet.Controller;

import com.project.shopviet.DTO.MessageDto;
import com.project.shopviet.DTO.OrderItemDto;
import com.project.shopviet.DTO.request.CategoryRequest;
import com.project.shopviet.DTO.response.ResponseObject;
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
    RoleService roleService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    MessageService messageService;
    @PostMapping("/category")
    public ResponseObject addCategory(@RequestBody CategoryRequest category){
        return categoryService.addCategory(category);
    }
    @DeleteMapping("/category/{id}")
    public ResponseObject deleteCategory(@PathVariable int id){
        return categoryService.deleteCategory(id);
    }
    @PutMapping("/category/{id}")
    public ResponseObject updateCategory(@RequestBody CategoryRequest category,@PathVariable int id){
        return categoryService.updateCategory(id, category);
    }

    @GetMapping("/order")
    public List<OrderItemDto> getAllOrderItem(){
        return orderItemService.getAllOrderItem();
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

    @PostMapping("/message/user/{receiver_id}")
    Messages sendMessage(@RequestBody Messages messages,@PathVariable int receiver_id){
        return messageService.sendMessage(messages,receiver_id);
    }
    @GetMapping("/message/user/{receiver_id}")
    List<MessageDto> getAllMessage(@PathVariable int receiver_id)
    {
        return messageService.findBySenderAndReceiverOrderByCreateAtAsc(receiver_id);
    }
}
