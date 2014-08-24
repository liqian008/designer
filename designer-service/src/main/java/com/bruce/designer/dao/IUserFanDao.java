package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.UserFan;
import com.bruce.designer.model.UserFanCriteria;
import com.bruce.foundation.dao.IFoundationDao;

public interface IUserFanDao extends IFoundationDao<UserFan, Long, UserFanCriteria>{ 
    
    public List<UserFan> getFanList(int userId);
    
    public List<UserFan> getFanList(int userId, int maxCount); 

    public int deleteFan(int unfollowId, int uid);
    
} 
