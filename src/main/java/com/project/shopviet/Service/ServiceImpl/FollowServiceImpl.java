package com.project.shopviet.Service.ServiceImpl;

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
    public Follow addFollow(int followUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        Follow follow=new Follow();
        follow.setUser(user);
        User followedUser=userRepository.findById(followUser).get();
        follow.setFollowedUser(followedUser);
        return followRepository.save(follow);
    }

    @Override
    public void deteleFollow(int followUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByName(authentication.getName());
        Follow follow=followRepository.getReferenceById(user.getId());
        followRepository.delete(follow);
    }

    @Override
    public int countFollow(int followUser) {
        return followRepository.countFollowersByUserId(followUser);
    }
}
