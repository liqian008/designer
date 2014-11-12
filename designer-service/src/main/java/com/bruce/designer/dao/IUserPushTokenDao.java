package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.UserPushToken;
import com.bruce.designer.model.UserPushTokenCriteria;
import com.bruce.foundation.dao.IFoundationDao;

public interface IUserPushTokenDao extends IFoundationDao<UserPushToken, Integer, UserPushTokenCriteria> {

	public List<UserPushToken> queryByUserId(Integer userId);

	public int enablePushToken(Integer userId, short osType, long pushChannelId, String pushUserId);
	
	public int disablePushToken(Integer userId, short osType, long pushChannelId, String pushUserId);
	
//	public UserPushToken load(Integer userId, Short channelType);
//
//	public int delete(Integer userId, Short channelType);

}
