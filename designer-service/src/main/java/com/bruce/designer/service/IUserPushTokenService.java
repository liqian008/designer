package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.UserPushToken;
import com.bruce.designer.model.UserPushTokenCriteria;
import com.bruce.foundation.service.IFoundationService;

public interface IUserPushTokenService extends IFoundationService<UserPushToken, Integer, UserPushTokenCriteria> {

	public List<UserPushToken> queryByUserId(Integer userId);
	
	public int enablePushToken(Integer userId, short osType, long pushChannelId, String pushUserId);
	
	public int disablePushToken(Integer userId, short osType, long pushChannelId, String pushUserId);
	
//	public List<UserPushToken> newUserPushToken(Integer userId, short channelType, String pushToken);

//	public UserPushToken load(Integer userId, Short channelType);
//
//	public int delete(Integer userId, Short channelType);

}
