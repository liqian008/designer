package com.bruce.designer.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.model.UserPushToken;
import com.bruce.designer.model.UserPushTokenCriteria;
import com.bruce.designer.dao.IUserPushTokenDao;
import com.bruce.designer.dao.mapper.UserPushTokenMapper;

@Repository
public class UserPushTokenDaoImpl implements IUserPushTokenDao {

	@Autowired
	private UserPushTokenMapper userPushTokenMapper;

	public int save(UserPushToken t) {
		return userPushTokenMapper.insert(t);
	}

	public UserPushToken loadById(Integer id) {
		return userPushTokenMapper.selectByPrimaryKey(id);
	}

	public int updateById(UserPushToken t) {
		return userPushTokenMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(UserPushToken t, UserPushTokenCriteria criteria) {
		return userPushTokenMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteByCriteria(UserPushTokenCriteria criteria) {
		return userPushTokenMapper.deleteByExample(criteria);
	}

	public int deleteById(Integer id) {
		return userPushTokenMapper.deleteByPrimaryKey(id);
	}

	public List<UserPushToken> queryAll() {
		return userPushTokenMapper.selectByExample(null);
	}

	@Override
	public List<UserPushToken> queryAll(String orderByClause) {
		UserPushTokenCriteria criteria = new UserPushTokenCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<UserPushToken> queryByCriteria(UserPushTokenCriteria criteria) {
		return userPushTokenMapper.selectByExample(criteria);
	}

	/**
	 * 查询用户已绑定的pushToken
	 */
	@Override
	public List<UserPushToken> queryByUserId(Integer userId) {
		UserPushTokenCriteria criteria = new UserPushTokenCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo((short)1);
		return userPushTokenMapper.selectByExample(criteria);
	}

	@Override
	public int enablePushToken(Integer userId, short osType, long pushChannelId, String pushUserId) {
		//查询是否有该pushToken，有则更新，没有则创建
		UserPushTokenCriteria criteria = new UserPushTokenCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId).andOsTypeEqualTo(osType).andPushChannelIdEqualTo(pushChannelId).andPushUserIdEqualTo(pushUserId);
		List<UserPushToken> pushTokenList = userPushTokenMapper.selectByExample(criteria);
		Date currentTime = new Date();
		if(pushTokenList!=null&&pushTokenList.size()>0){
			//更新
			UserPushToken userPushToken = new UserPushToken();
			userPushToken.setStatus((short)1);
			userPushToken.setUpdateTime(currentTime);
			return userPushTokenMapper.updateByExampleSelective(userPushToken, criteria);
		}else{
			//创建token
			UserPushToken userPushToken = new UserPushToken();
			userPushToken.setOsType((short)1);
			userPushToken.setUserId(userId);
			userPushToken.setPushChannelId(pushChannelId);
			userPushToken.setPushUserId(pushUserId);
			userPushToken.setCreateTime(currentTime);
			return save(userPushToken);
		}
	}

	@Override
	public int disablePushToken(Integer userId, short osType, long pushChannelId, String pushUserId) {
		UserPushToken userPushToken = new UserPushToken();
		userPushToken.setStatus((short)0);
		UserPushTokenCriteria criteria = new UserPushTokenCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId).andOsTypeEqualTo(osType).andPushChannelIdEqualTo(pushChannelId).andPushUserIdEqualTo(pushUserId);;
		return userPushTokenMapper.updateByExampleSelective(userPushToken, criteria);
	}
	
}
