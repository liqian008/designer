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

import com.bruce.designer.exception.ErrorCode;
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
 * 微博登录后绑定老账户
 * @author liqian
 *
 */
@Component
public class OAuthBindCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(OAuthBindCommand.class);
    
    @Autowired
    private PassportService passportService;
    @Autowired
    private IOAuthService oauthService;
    @Autowired
    private IAccessTokenService accessTokenService;
    @Autowired
    private IUserService userService;
    
    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	
    	String thirdpartyTypeStr = context.getStringParams().get("thirdpartyType");
    	short thirdpartyType = NumberUtils.toShort(thirdpartyTypeStr, (short)0);
    	if(thirdpartyType==0){
    		return ResponseBuilderUtil.buildErrorResult();
    	}
    	
    	String username = context.getStringParams().get("username");
    	String password = context.getStringParams().get("password");
    	
    	String thirdpartyUid = context.getStringParams().get("thirdpartyUid");
    	String thirdpartyUname = context.getStringParams().get("thirdpartyUname");
    	String thirdpartyAccessToken = context.getStringParams().get("thirdpartyAccessToken");
    	String thirdpartyRefreshToken = context.getStringParams().get("thirdpartyRefreshToken");
    	String thirdpartyExpireInStr = context.getStringParams().get("thirdpartyExpireIn");
    	long thirdpartyExpireIn = NumberUtils.toLong(thirdpartyExpireInStr, 0);
    	
    	// 加载用户
		User user = userService.authUser(username, password);
		
		if (user != null) { // 登录检查通过
			Map<Short, AccessTokenInfo> accessTokenMap = user.getAccessTokenMap();
			// 判断该用户是否被绑定过同类型账户
			boolean alreadyBind = accessTokenMap.get(thirdpartyType) != null;
			if (!alreadyBind) {// 之前未绑定过同类型
				//accessToken
				AccessTokenInfo thirdpartyBindInfo = new AccessTokenInfo();
				thirdpartyBindInfo.setUserId(user.getId());
				thirdpartyBindInfo.setThirdpartyType(thirdpartyType);
				thirdpartyBindInfo.setThirdpartyUid(thirdpartyUid);
				thirdpartyBindInfo.setThirdpartyUname(thirdpartyUname);
				
				thirdpartyBindInfo.setAccessToken(thirdpartyAccessToken);
				thirdpartyBindInfo.setRefreshToken(thirdpartyRefreshToken);
				thirdpartyBindInfo.setExpireIn(thirdpartyExpireIn);
				thirdpartyBindInfo.setCreaeTime(new Date());
				
				int result = accessTokenService.save(thirdpartyBindInfo);
				
				if(logger.isDebugEnabled()){
		            logger.debug("用户["+username+"]使用第三方账户["+thirdpartyUname+"]登录绑定成功");
		        }
				if(result==1){
					
					//加载新注册的用户数据
	                UserPassport userPassport = new UserPassport();
	                userPassport.setUserId(user.getId());
	                userPassport.setIdentity(String.valueOf(System.currentTimeMillis()));
	                String ticket = passportService.createTicket(userPassport);
	                userPassport.setTicket(ticket);
	                
	                paramMap.put("userPassport", userPassport);
	                paramMap.put("hostUser", user);
	                return ResponseBuilderUtil.buildSuccessResult(paramMap);
				}
			} else {// 账户之前已经绑定过本token，不能重复绑定
			    if(logger.isDebugEnabled()){
	                logger.debug("用户["+username+"]已绑定过该第三方账户，不能重复绑定");
	            }
			    ResponseBuilderUtil.buildErrorResult(ErrorCode.OAUTH_ALREADY_BIND);
			}
		}else{//用户名密码不匹配
		    ResponseBuilderUtil.buildErrorResult(ErrorCode.USER_PASSWORD_NOT_MATCH);
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
