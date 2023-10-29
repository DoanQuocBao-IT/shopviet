package com.project.shopviet.Service;

import com.project.shopviet.DTO.response.Response;
import com.project.shopviet.Model.Follow;

public interface FollowService {
    Response addFollow(int followUser);
    Response unFollow(int followUser);
    int countFollowers(int followUser);
    int countFollowings(int followUser);
}
