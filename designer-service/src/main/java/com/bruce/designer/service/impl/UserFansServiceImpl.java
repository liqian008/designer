package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.dao.UserFansMapper;
import com.bruce.designer.model.UserFans;
import com.bruce.designer.model.UserCriteria;
import com.bruce.designer.model.UserFansCriteria;
import com.bruce.designer.model.UserFans;
import com.bruce.designer.model.UserFansCriteria;
import com.bruce.designer.service.IUserFansService;

public class UserFansServiceImpl implements IUserFansService{ 
    
    @Autowired
    private UserFansMapper fansMapper;
    
    public int save(UserFans t) {
        return fansMapper.insert(t);
    }

    public List<UserFans> queryAll() {
        return fansMapper.selectByExample(null);
    }

    public int updateById(UserFans t) {
        return fansMapper.updateByPrimaryKeySelective(t);
    }

    public int deleteById(Long id) {
        return fansMapper.deleteByPrimaryKey(id);
    }

    public UserFans loadById(Long id) {
        return fansMapper.selectByPrimaryKey(id);
    }

    
    @Override
    public List<UserFans> getFansList(int userId, int maxCount) {
        UserFansCriteria criteria = new UserFansCriteria();
        criteria.createCriteria().andUserIdEqualTo(userId);
        List<UserFans> fansList = fansMapper.selectByExample(criteria);
        return fansList;
    }

    @Override
    public int deleteFans(int unfollowId, int uid) {
        UserFansCriteria criteria = new UserFansCriteria();
        criteria.createCriteria().andUserIdEqualTo(unfollowId).andFansIdEqualTo(uid);
        return fansMapper.deleteByExample(criteria);
    }
} 
