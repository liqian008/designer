package com.bruce.designer.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.model.User;
import com.bruce.designer.model.UserPushToken;
import com.bruce.designer.service.IPushService;
import com.bruce.designer.service.IUserPushTokenService;
import com.bruce.designer.service.IUserService;

@Service
public class IPushServiceImpl implements IPushService, InitializingBean {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserPushTokenService userPushTokenService;
	
	@Override
	public int pushMessage(int messageType, String content, long sourceId, Set<Integer> toIdSet) {
		//查询用户token
		
		//调用百度push
		return 0;
	}
	@Override
	public int pushMessage(int messageType, String content, long sourceId, int toId) {
		//
		User toUser = userService.loadById(toId);
		if(toUser!=null){
			//检查用户的push设置
			boolean pushAllowed = isPushAllow(messageType, toUser.getPushMask());
			if(pushAllowed){
				//查询用户token
				List<UserPushToken> userPushTokenList = userPushTokenService.queryByUserId(toId);
				if(userPushTokenList!=null&&userPushTokenList.size()>0){
					for(UserPushToken userPushToken : userPushTokenList){
						String pushToken = userPushToken.getPushToken();
						//调用百度进行push
						
					}
				}
			}
		}
		return 0;
	}
	
	/**
	 * 检查用户是否允许push
	 * @param messageType
	 * @param toId
	 * @return
	 */
	private boolean isPushAllow(int messageType, long pushMask) {
		if(pushMask>0){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
