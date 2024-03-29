package com.project.shopviet.Controller;

import com.project.shopviet.DTO.*;
import com.project.shopviet.DTO.response.ResponseObject;
import com.project.shopviet.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    FollowService followService;
    @GetMapping("/follow/user/{id}")
    ResponseObject follow(@PathVariable int id){
        return followService.addFollow(id);
    }
    @GetMapping("/unfollow/user/{id}")
    ResponseObject unfollow(@PathVariable int id){
        return followService.unFollow(id);
    }
    @GetMapping("/profile")
    UserDto getProfile(){
        return userService.getProfile();
    }
}
