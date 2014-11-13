package com.bruce.designer.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.bruce.designer.constants.ConstConfig;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.model.User;
import com.bruce.designer.model.UserPushToken;
import com.bruce.designer.service.IPushService;
import com.bruce.designer.service.IUserPushTokenService;
import com.bruce.designer.service.IUserService;
import com.bruce.foundation.util.JsonUtil;

@Service
public class PushServiceImpl implements IPushService, InitializingBean {

	private BaiduChannelClient channelClient = null;
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserPushTokenService userPushTokenService;

	@Override
	public void afterPropertiesSet() throws Exception {
	}
	
	@Override
	@PostConstruct
	public void init(){
//		//1.设置developer平台的ApiKey/SecretKey
        ChannelKeyPair pair = new ChannelKeyPair(ConstConfig.BAIDU_PUSH_APP_KEY, ConstConfig.BAIDU_PUSH_SECRET_KEY);
        // 2. 创建BaiduChannelClient对象实例
        channelClient = new BaiduChannelClient(pair);
	}
	
	

	@Override
	public int pushMessage(int messageType, String content, long sourceId, int fromId, int toId) {
		//
		System.out.println("进入 pushMessage 方法");
		User toUser = userService.loadById(toId);
		if(toUser!=null&&toUser.getPushMask()!=null&&toUser.getPushMask()>0){
			//检查用户的push设置
			boolean pushAllowed = isPushAllow(messageType, toUser.getPushMask());
			if(pushAllowed){
				//查询用户token
				List<UserPushToken> userPushTokenList = userPushTokenService.queryByUserId(toId);
				if(userPushTokenList!=null&&userPushTokenList.size()>0){
					for(UserPushToken userPushToken : userPushTokenList){
						long pushChannelId = userPushToken.getPushChannelId();
						String pushUserId = userPushToken.getPushUserId();
						//调用百度进行push
						baiduPush(messageType, userPushToken.getOsType(), content, pushChannelId, pushUserId);
					}
				}
			}
		}
		return 0;
	}

	/**
	 * 调用baiduPush进行推送
	 * @param osType
	 * @param pushChannelId
	 * @param content
	 * @param pushUserId 
	 * @return
	 */
	private int baiduPush(int messageType, int osType, String content, long pushChannelId, String pushUserId) {
		try {
            //创建请求类对象
			PushUnicastMessageRequest request = new PushUnicastMessageRequest();
            request.setChannelId(pushChannelId);
            request.setUserId(pushUserId);
            request.setMessageType(1);//1为广播，0为静默消息，默认为0
            
            //构造push消息
            String pushMessage = buildPushMessage(messageType);
            
			PushAndroidMessage androidMessage = new PushAndroidMessage("金玩儿网", pushMessage, 2, 0, 4, "");
            String baiduPushMessage = JsonUtil.gson.toJson(androidMessage);
            request.setMessage(baiduPushMessage);
            
            if(channelClient==null){
            	throw new Exception("push初始化失败");
            }
            
            System.out.println("调用百度进行push, pushUserId: "+pushUserId+", pushChannelId: "+pushChannelId);
            
            // 5. 调用pushMessage接口
            PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);
            // 6. 认证推送成功
            System.out.println("push amount : " + response.getSuccessAmount());
            return response.getSuccessAmount();
        } catch (ChannelClientException e) {
            // 处理客户端错误异常
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        } catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	
	/**
	 * 检查用户是否允许push
	 * @param messageType
	 * @param toId
	 * @return
	 */
	private static boolean isPushAllow(int messageType, long pushMask) {
		if(pushMask>0){
			return true;
		}else{
			return false;
		}
	}
	
	
	public static String buildPushMessage(int messageType) {
		switch (messageType) {
			case ConstService.MESSAGE_TYPE_SYSTEM: {
				return "收到一条系统广播";
			}
			case ConstService.MESSAGE_TYPE_FOLLOW: {
				return "有人关注了你，快去看看他是谁";
			}
			case ConstService.MESSAGE_TYPE_COMMENT: {
				return "收到一条评论";
			}
			case ConstService.MESSAGE_TYPE_LIKE: {
				return "有人赞了你的专辑";
			}
			case ConstService.MESSAGE_TYPE_FAVORITIES: {
				return "有人收藏了你的专辑";
			}
			case ConstService.MESSAGE_TYPE_AT: {
				return "有人@了你，快去看看他是谁";
			}
			default: {
				return "有人给你发了条私信消息，快去看看吧";
			}
		}
	}
}
