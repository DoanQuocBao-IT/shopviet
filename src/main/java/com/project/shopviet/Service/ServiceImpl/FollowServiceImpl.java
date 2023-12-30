package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.DTO.response.ResponseObject;
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
    public ResponseObject addFollow(int followUser) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.findUserByName(authentication.getName());
            Follow follow = new Follow();
            follow.setUser(user);
            User followedUser = userRepository.findById(followUser).get();
            follow.setFollowedUser(followedUser);
            followRepository.save(follow);
            return ResponseObject.builder()
                    .code(200)
                    .message("Followed")
                    .build();
        } catch (Exception e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Followed")
                    .build();
        }

    }

    @Override
    public ResponseObject unFollow(int followUser) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.findUserByName(authentication.getName());
            Follow follow = followRepository.getReferenceById(user.getId());
            followRepository.delete(follow);
            return ResponseObject.builder()
                    .code(200)
                    .message("Unfollowed")
                    .build();
        } catch (Exception e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Unfollowed")
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
