package com.bruce.designer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.dao.IUserFansDao;
import com.bruce.designer.dao.mapper.UserFansMapper;
import com.bruce.designer.model.UserFans;
import com.bruce.designer.model.UserFansCriteria;

@Repository
public class UserFansDaoImpl implements IUserFansDao, InitializingBean {
    
    @Autowired
    private UserFansMapper userFansMapper;
    
    public int save(UserFans t) {
        return userFansMapper.insertSelective(t);
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
    public List<UserFans> getFansList(int userId) {
        return getFansList(userId, -1);
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

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        
    }
} 
