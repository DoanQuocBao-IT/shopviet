package com.project.shopviet.Service;

import com.project.shopviet.DTO.response.ResponseObject;

public interface FollowService {
    ResponseObject addFollow(int followUser);
    ResponseObject unFollow(int followUser);
    int countFollowers(int followUser);
    int countFollowings(int followUser);
}
