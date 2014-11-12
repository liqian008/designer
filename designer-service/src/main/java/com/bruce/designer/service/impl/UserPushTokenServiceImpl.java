package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.dao.IUserPushTokenDao;
import com.bruce.designer.model.UserPushToken;
import com.bruce.designer.model.UserPushTokenCriteria;
import com.bruce.designer.service.IUserPushTokenService;

@Service
public class UserPushTokenServiceImpl implements IUserPushTokenService {

	@Autowired
	private IUserPushTokenDao userPushTokenDao;

	public int save(UserPushToken t) {
		return userPushTokenDao.save(t);
	}

	public int updateById(UserPushToken t) {
		return userPushTokenDao.updateById(t);
	}

	@Override
	public int updateByCriteria(UserPushToken t, UserPushTokenCriteria criteria) {
		return userPushTokenDao.updateByCriteria(t, criteria);
	}

	public int deleteById(Integer id) {
		return userPushTokenDao.deleteById(id);
	}

	@Override
	public int deleteByCriteria(UserPushTokenCriteria criteria) {
		return userPushTokenDao.deleteByCriteria(criteria);
	}

	public UserPushToken loadById(Integer id) {
		return userPushTokenDao.loadById(id);
	}

	@Override
	public List<UserPushToken> queryAll() {
		return userPushTokenDao.queryAll();
	}

	@Override
	public List<UserPushToken> queryAll(String orderByClause) {
		return userPushTokenDao.queryAll(orderByClause);
	}

	@Override
	public List<UserPushToken> queryByCriteria(UserPushTokenCriteria criteria) {
		return userPushTokenDao.queryByCriteria(criteria);
	}


	@Override
	public List<UserPushToken> queryByUserId(Integer userId) {
		return userPushTokenDao.queryByUserId(userId);
	}

	@Override
	public int enablePushToken(Integer userId, short osType, long pushChannelId, String pushUserId){
		return userPushTokenDao.enablePushToken(userId, osType, pushChannelId, pushUserId);
	}

	@Override
	public int disablePushToken(Integer userId, short osType, long pushChannelId, String pushUserId){
		return userPushTokenDao.disablePushToken(userId, osType, pushChannelId, pushUserId);
	}
	
}
