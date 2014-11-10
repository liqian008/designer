/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.account;

import java.util.Date;
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
import com.bruce.designer.service.oauth.IAccessTokenService;
import com.bruce.designer.service.oauth.IOAuthService;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.macp.passport.entity.UserPassport;
import com.bruce.foundation.macp.passport.service.PassportService;
import com.bruce.foundation.model.result.ApiResult;

/**
 * 为通过sina微博审核做的临时做的接口（）
 * @author liqian
 *
 */
@Component
public class WeiboLoginTempCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(WeiboLoginTempCommand.class);
    
    @Autowired
    private PassportService passportService;
    @Autowired
	private IUserService userService;
    @Autowired
    private IOAuthService oauthService;
    @Autowired
    private IAccessTokenService accessTokenService;

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
    	
    	//根据第oauth方的用户id加载accessToken
    	AccessTokenInfo accessToken = oauthService.loadTokenByClient(weiboUid, weiboAccessToken, weiboRefreshToken, weiboExpireIn, IOAuthService.OAUTH_WEIBO_TYPE);
    	logger.debug("用户加载查询accessToken["+weiboUid+", "+weiboUid+""+","+weiboAccessToken+ "]");
    	if(accessToken!=null){
    		int result = 0;
    		int userId = 0;
    		if(accessToken.getUserId()!=null&&accessToken.getUserId()>0){//之前已经绑定过，无需进行登录绑定操作
	    		result = 1;
	    		userId = accessToken.getUserId();
	    	}else{//之前未绑定过，但考虑到此接口是为了绕过sina微博审核，故无需进入完善资料界面。
	    		User user = new User();
	    		//获取昵称
	    		String nickname = accessToken.getThirdpartyUname();
	    		user.setUsername(nickname);
	    		user.setNickname(nickname);
	    		user.setPassword("f7c78e6132affce11e1e80d73fc16e01");
	    		Date currentTime = new Date();
	    		user.setCreateTime(currentTime);
	    		user.setDesignerStatus((short) 0);
	    		result = userService.save(user);
	    		userId = user.getId();
	    		//保存token
	    		accessToken.setUserId(userId);
	    		accessToken.setCreaeTime(currentTime);
	    		accessTokenService.save(accessToken);
	    	}
    		if(result>0&&userId>0){
    			UserPassport userPassport = new UserPassport();
				userPassport.setUserId(userId);
				userPassport.setIdentity(String.valueOf(System.currentTimeMillis()));
				String ticket = passportService.createTicket(userPassport);
				userPassport.setTicket(ticket);
				paramMap.put("userPassport", userPassport);
				User loginUser = userService.loadById(userId);
				paramMap.put("hostUser", loginUser);
    		}
    		return ResponseBuilderUtil.buildSuccessResult(paramMap);
    	}
        return ResponseBuilderUtil.buildErrorResult();
    }

	public PassportService getPassportService() {
		return passportService;
	}

	public void setPassportService(PassportService passportService) {
		this.passportService = passportService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IOAuthService getOauthService() {
		return oauthService;
	}

	public void setOauthService(IOAuthService oauthService) {
		this.oauthService = oauthService;
	}
	
}
