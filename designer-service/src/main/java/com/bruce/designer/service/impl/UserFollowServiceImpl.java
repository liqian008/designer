package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.dao.UserFollowMapper;
import com.bruce.designer.model.UserFollow;
import com.bruce.designer.model.UserFollowCriteria;
import com.bruce.designer.service.IUserFollowService;

@Service
public class UserFollowServiceImpl implements IUserFollowService{ 
    
    @Autowired
    private UserFollowMapper userFollowMapper;
    
    public int save(UserFollow t) {
        return userFollowMapper.insert(t);
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

} 
