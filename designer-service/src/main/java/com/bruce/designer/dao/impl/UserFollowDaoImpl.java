package com.bruce.designer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.dao.IUserFollowDao;
import com.bruce.designer.dao.mapper.UserFollowMapper;
import com.bruce.designer.model.UserFollow;
import com.bruce.designer.model.UserFollowCriteria;

@Repository
public class UserFollowDaoImpl implements IUserFollowDao, InitializingBean { 
    
    @Autowired
    private UserFollowMapper userFollowMapper;
    
    public int save(UserFollow t) {
        return userFollowMapper.insertSelective(t);
    }

    public List<UserFollow> queryAll() {
        return userFollowMapper.selectByExample(null);
    }

    public int updateById(UserFollow t) {
        return userFollowMapper.updateByPrimaryKeySelective(t);
    }

    public int deleteById(Long id) {
        return userFollowMapper.deleteByPrimaryKey(id);
    }

    public UserFollow loadById(Long id) {
        return userFollowMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UserFollow> getFollowList(int userId) {
        UserFollowCriteria criteria = new UserFollowCriteria();
        criteria.createCriteria().andUserIdEqualTo(userId);
        List<UserFollow> followList = userFollowMapper.selectByExample(criteria);
        return followList;
    }

    @Override
    public int deleteFollow(int uid, int unfollowId) {
        UserFollowCriteria criteria = new UserFollowCriteria();
        criteria.createCriteria().andUserIdEqualTo(uid).andFollowIdEqualTo(unfollowId);
        return userFollowMapper.deleteByExample(criteria);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        
    }

} 
