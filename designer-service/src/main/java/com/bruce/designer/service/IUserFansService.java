package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.UserFans;

public interface IUserFansService extends IBaseService<UserFans, Long>{ 
    
    public List<UserFans> getFansList(int userId, int maxCount); 

    public int deleteFans(int unfollowId, int uid);
    
} 
