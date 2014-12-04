/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.account;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bruce.designer.model.AccessTokenInfo;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.service.oauth.IOAuthService;
import com.bruce.designer.service.weixin.WxOauthService;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.service.impl.MobileClientAppServiceImpl;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.macp.passport.entity.UserPassport;
import com.bruce.foundation.macp.passport.service.PassportService;
import com.bruce.foundation.model.result.ApiResult;
import com.bruce.geekway.model.wx.json.response.WxOauthTokenResult;
import com.bruce.geekway.model.wx.json.response.WxUserInfoResult;

/**
 * 微信登录功能
 * @author liqian
 *
 */
@Component
public class WeixinLoginCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(WeixinLoginCommand.class);
    
    @Autowired
    private PassportService passportService;
    @Autowired
    private IOAuthService oauthService;
    @Autowired
    private IUserService userService;
    @Autowired
    private WxOauthService wxOauthService;
    
    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	String weixinCode = context.getStringParams().get("weixinCode");
//    	String weixinState = context.getStringParams().get("weixinState");
    	
    	//TODO使用code获取accessToken
    	WxOauthTokenResult oauthTokenResult = wxOauthService.getOauthAccessToken(weixinCode);
		if(oauthTokenResult!=null){
			//使用accessToken获取个人资料
			WxUserInfoResult userInfoResult = wxOauthService.getOauthUserinfo(oauthTokenResult.getAccess_token(), oauthTokenResult.getOpenid());
			
			if(userInfoResult!=null){//用户资料获取成功
				//TODO unionId
				String weixinUnionId = userInfoResult.getOpenid();
				String weixinAccessToken = oauthTokenResult.getAccess_token();
				String weixinRefreshToken = oauthTokenResult.getRefresh_token();
				long weixinExpireIn = oauthTokenResult.getExpires_in();
				
				AccessTokenInfo accessToken = oauthService.loadTokenByClient(weixinUnionId, weixinAccessToken, weixinRefreshToken, weixinExpireIn, IOAuthService.OAUTH_WEIXIN_TYPE);
		    	if(accessToken!=null&&accessToken.getUserId()!=null&&accessToken.getUserId()>0){//之前已经绑定过，无需进行登录绑定操作
					UserPassport userPassport = new UserPassport();
					userPassport.setUserId(accessToken.getUserId());
					userPassport.setIdentity(String.valueOf(System.currentTimeMillis()));
					String ticket = passportService.createTicket(userPassport);
					userPassport.setTicket(ticket);
					//设置用户的secretkey
					userPassport.setUserSecretKey(MobileClientAppServiceImpl.SECRET_KEY_DEFAULT);
					paramMap.put("userPassport", userPassport);
					
					User hostUser = userService.loadById(accessToken.getUserId(), true);
					if(hostUser!=null){
						paramMap.put("hostUser", hostUser);
					}
		    	}else{//之前未绑定过，需要进行登录或注册绑定操作
		    		paramMap.put("needBind", true);
		    		paramMap.put("thirdpartyType",  String.valueOf(IOAuthService.OAUTH_WEIXIN_TYPE));//微信类型
		    		paramMap.put("thirdpartyUname", userInfoResult.getNickname());
		    		paramMap.put("thirdpartyAvatar", userInfoResult.getHeadimgurl());
		    	}
		    	return ResponseBuilderUtil.buildSuccessResult(paramMap);
			}
		}
		return ResponseBuilderUtil.buildErrorResult();
    }

	public PassportService getPassportService() {
		return passportService;
	}

	public void setPassportService(PassportService passportService) {
		this.passportService = passportService;
	}
    
}
