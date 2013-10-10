package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.UserFollow;


public interface IUserFollowDao extends IBaseDao<UserFollow, Long>{
    
    public List<UserFollow> getFollowList(int userId);

    public int deleteFollow(int uid, int unfollowId);
    
}
