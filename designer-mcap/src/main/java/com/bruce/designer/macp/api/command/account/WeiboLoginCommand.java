/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.account;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bruce.designer.model.AccessTokenInfo;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.service.oauth.IOAuthService;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.service.impl.MobileClientAppServiceImpl;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.macp.passport.entity.UserPassport;
import com.bruce.foundation.macp.passport.service.PassportService;
import com.bruce.foundation.model.result.ApiResult;

/**
 * @author liqian
 * 
 */
@Component
public class WeiboLoginCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(WeiboLoginCommand.class);
    
    @Autowired
    private PassportService passportService;
    @Autowired
    private IOAuthService oauthService;
    @Autowired
    private IUserService userService;
    
    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	String weiboUid = context.getStringParams().get("weiboUid");
    	String weiboAccessToken = context.getStringParams().get("weiboAccessToken");
    	String weiboRefreshToken = context.getStringParams().get("weiboRefreshToken");
    	String weiboExpireInStr = context.getStringParams().get("weiboExpireIn");
    	long weiboExpireIn = NumberUtils.toLong(weiboExpireInStr, 0);
    	
    	//TODO 此处完全信任客户端传入的uid，可能引发注入bug
    	AccessTokenInfo accessToken = oauthService.loadTokenByClient(weiboUid, weiboAccessToken, weiboRefreshToken, weiboExpireIn, IOAuthService.OAUTH_WEIBO_TYPE);
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
    		paramMap.put("thirdpartyType",  String.valueOf(IOAuthService.OAUTH_WEIBO_TYPE));
    		paramMap.put("thirdpartyUname", accessToken.getThirdpartyUname());
    		paramMap.put("thirdpartyAvatar", accessToken.getThirdpartyAvatar());
    	}
        return ResponseBuilderUtil.buildSuccessResult(paramMap);
    }

	public PassportService getPassportService() {
		return passportService;
	}

	public void setPassportService(PassportService passportService) {
		this.passportService = passportService;
	}
    
}
