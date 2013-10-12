package com.bruce.designer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.dao.IUserFanDao;
import com.bruce.designer.dao.mapper.UserFanMapper;
import com.bruce.designer.model.UserFan;
import com.bruce.designer.model.UserFanCriteria;

@Repository
public class UserFanDaoImpl implements IUserFanDao, InitializingBean {
     
    @Autowired
    private UserFanMapper userFanMapper;
    
    public int save(UserFan t) {
        return userFanMapper.insertSelective(t);
    }

    public List<UserFan> queryAll() {
        return userFanMapper.selectByExample(null);
    }

    public int updateById(UserFan t) {
        return userFanMapper.updateByPrimaryKeySelective(t);
    }

    public int deleteById(Long id) {
        return userFanMapper.deleteByPrimaryKey(id);
    }

    public UserFan loadById(Long id) {
        return userFanMapper.selectByPrimaryKey(id);
    }

    
    @Override
    public List<UserFan> getFanList(int userId) {
        return getFanList(userId, -1);
    }
    
    @Override
    public List<UserFan> getFanList(int userId, int maxCount) {
        UserFanCriteria criteria = new UserFanCriteria();
        criteria.createCriteria().andUserIdEqualTo(userId);
        List<UserFan> fansList = userFanMapper.selectByExample(criteria);
        return fansList;
    }

    @Override
    public int deleteFan(int unfollowId, int uid) {
        UserFanCriteria criteria = new UserFanCriteria();
        criteria.createCriteria().andUserIdEqualTo(unfollowId).andFanIdEqualTo(uid);
        return userFanMapper.deleteByExample(criteria);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        
    }
} 
