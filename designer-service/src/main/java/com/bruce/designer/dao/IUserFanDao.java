package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.UserFan;

public interface IUserFanDao extends IBaseDao<UserFan, Long>{ 
    
    public List<UserFan> getFanList(int userId);
    
    public List<UserFan> getFanList(int userId, int maxCount); 

    public int deleteFan(int unfollowId, int uid);
    
} 
