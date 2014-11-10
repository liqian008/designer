package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.UserPushToken;
import com.bruce.designer.model.UserPushTokenCriteria;
import com.bruce.foundation.service.IFoundationService;

public interface IUserPushTokenService extends IFoundationService<UserPushToken, Integer, UserPushTokenCriteria> {

	public List<UserPushToken> queryByUserId(Integer userId);

	public UserPushToken load(Integer userId, Short channelType);

	public int delete(Integer userId, Short channelType);

}
