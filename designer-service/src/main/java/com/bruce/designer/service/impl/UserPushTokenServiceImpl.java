package com.bruce.designer.service.impl;

import java.util.Date;
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
		//查询该设备已绑定的其他账户
		UserPushTokenCriteria criteria = new UserPushTokenCriteria();
		UserPushTokenCriteria.Criteria subCriteria = criteria.createCriteria();
		subCriteria.andUserIdNotEqualTo(userId).andOsTypeEqualTo(osType).andPushChannelIdEqualTo(pushChannelId).andPushUserIdEqualTo(pushUserId);
		//同一设备下的用户pushToken列表
		List<UserPushToken> conflictUserPushTokenList = userPushTokenDao.queryByCriteria(criteria);
		Date currentTime = new Date();
		if(conflictUserPushTokenList!=null&&conflictUserPushTokenList.size()>0){
			//禁用（删除）这些pushTokenList
			deleteByCriteria(criteria);
		}
		
		//TODO 如果个人pushToken存在，则更新，不存在则创建
		
		//创建token
		UserPushToken userPushToken = new UserPushToken();
		userPushToken.setOsType(osType);
		userPushToken.setStatus((short)1);
		userPushToken.setUserId(userId);
		userPushToken.setPushChannelId(pushChannelId);
		userPushToken.setPushUserId(pushUserId);
		userPushToken.setCreateTime(currentTime);
		return save(userPushToken);
	}

	@Override
	public int disablePushToken(Integer userId, short osType, long pushChannelId, String pushUserId){
		return userPushTokenDao.disablePushToken(userId, osType, pushChannelId, pushUserId);
	}
	
}
