package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.dao.UserFansMapper;
import com.bruce.designer.model.UserFans;
import com.bruce.designer.model.UserCriteria;
import com.bruce.designer.model.UserFansCriteria;
import com.bruce.designer.model.UserFans;
import com.bruce.designer.model.UserFansCriteria;
import com.bruce.designer.service.IUserFansService;

@Service
public class UserFansServiceImpl implements IUserFansService{ 
    
    @Autowired
    private UserFansMapper userFansMapper;
    
    public int save(UserFans t) {
        return userFansMapper.insert(t);
    }

    public List<UserFans> queryAll() {
        return userFansMapper.selectByExample(null);
    }

    public int updateById(UserFans t) {
        return userFansMapper.updateByPrimaryKeySelective(t);
    }

    public int deleteById(Long id) {
        return userFansMapper.deleteByPrimaryKey(id);
    }

    public UserFans loadById(Long id) {
        return userFansMapper.selectByPrimaryKey(id);
    }

    
    @Override
    public List<UserFans> getFansList(int userId, int maxCount) {
        UserFansCriteria criteria = new UserFansCriteria();
        criteria.createCriteria().andUserIdEqualTo(userId);
        List<UserFans> fansList = userFansMapper.selectByExample(criteria);
        return fansList;
    }

    @Override
    public int deleteFans(int unfollowId, int uid) {
        UserFansCriteria criteria = new UserFansCriteria();
        criteria.createCriteria().andUserIdEqualTo(unfollowId).andFansIdEqualTo(uid);
        return userFansMapper.deleteByExample(criteria);
    }
} 
