package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.UserFollow;


public interface IUserFollowService extends IBaseService<UserFollow, Long>{
    
    public List<UserFollow> getFollowList(int userId);

    public int deleteFollow(int uid, int unfollowId);
    
}
