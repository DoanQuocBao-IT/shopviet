package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.DTO.response.Response;
import com.project.shopviet.Model.Follow;
import com.project.shopviet.Model.User;
import com.project.shopviet.Repository.FollowRepository;
import com.project.shopviet.Repository.UserRepository;
import com.project.shopviet.Service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FollowRepository followRepository;
    @Override
    public Response addFollow(int followUser) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.findUserByName(authentication.getName());
            Follow follow=new Follow();
            follow.setUser(user);
            User followedUser=userRepository.findById(followUser).get();
            follow.setFollowedUser(followedUser);
            followRepository.save(follow);
            return Response.builder()
                    .message("Followed")
                    .status("success")
                    .build();
        }catch (Exception e){
            return Response.builder()
                    .message("Followed")
                    .status("error")
                    .build();
        }

    }

    @Override
    public Response unFollow(int followUser) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.findUserByName(authentication.getName());
            Follow follow=followRepository.getReferenceById(user.getId());
            followRepository.delete(follow);
            return Response.builder()
                    .message("Unfollowed")
                    .status("success")
                    .build();
        }catch (Exception e) {
            return Response.builder()
                    .message("Unfollowed")
                    .status("error")
                    .build();
        }
    }

    @Override
    public int countFollowers(int followUser) {
        return followRepository.countFollowByFollowedUserId(followUser);
    }

    @Override
    public int countFollowings(int followUser) {
        return followRepository.countFollowByUserId(followUser);
    }
}
