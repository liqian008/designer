package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.UserFans;

public interface IUserFansDao extends IBaseDao<UserFans, Long>{ 
    
    public List<UserFans> getFansList(int userId);
    
    public List<UserFans> getFansList(int userId, int maxCount); 

    public int deleteFans(int unfollowId, int uid);
    
} 
