package com.project.shopviet.Service;

import com.project.shopviet.Model.Follow;

public interface FollowService {
    Follow addFollow(int followUser);
    void deteleFollow(int followUser);
    int countFollow(int followUser);
}
