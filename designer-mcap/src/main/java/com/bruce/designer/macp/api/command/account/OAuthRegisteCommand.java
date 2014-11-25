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

import com.bruce.designer.mail.MailService;
import com.bruce.designer.model.AccessTokenInfo;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.service.oauth.IAccessTokenService;
import com.bruce.designer.service.oauth.IOAuthService;
import com.bruce.designer.util.VerifyUtils;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.service.impl.MobileClientAppServiceImpl;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.macp.passport.entity.UserPassport;
import com.bruce.foundation.macp.passport.service.PassportService;
import com.bruce.foundation.model.result.ApiResult;

/**
 * 微博登录后绑定注册新账户
 * @author liqian
 *
 */
@Component
public class OAuthRegisteCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(OAuthRegisteCommand.class);
    
    @Autowired
    private PassportService passportService;
    @Autowired
    private IOAuthService oauthService;
    @Autowired
    private IAccessTokenService accessTokenService;
    @Autowired
    private IUserService userService;
    @Autowired
	private MailService mailService;
    
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
    	String nickname = context.getStringParams().get("nickname");
    	String password = context.getStringParams().get("password");
    	
    	String thirdpartyUid = context.getStringParams().get("thirdpartyUid");
    	String thirdpartyUname = context.getStringParams().get("thirdpartyUname");
    	String thirdpartyAvatar = context.getStringParams().get("thirdpartyAvatar");
    	String thirdpartyAccessToken = context.getStringParams().get("thirdpartyAccessToken");
    	String thirdpartyRefreshToken = context.getStringParams().get("thirdpartyRefreshToken");
    	String thirdpartyExpireInStr = context.getStringParams().get("thirdpartyExpireIn");
    	long thirdpartyExpireIn = NumberUtils.toLong(thirdpartyExpireInStr, 0);
    	
    	
    	//验证用户名格式
		VerifyUtils.verifyUsername(username);

		// 创建用户
		User user = new User();
		user.setUsername(username.trim());
		user.setNickname(nickname.trim());
		user.setPassword(password.trim());
		Date currentTime = new Date();
		user.setCreateTime(currentTime);
		try {
			//oauth方式的用户注册
			int result = userService.registerByOauth(user, thirdpartyAvatar);
			if (result == 1) {
				
				//accessToken
				AccessTokenInfo thirdpartyBindInfo = new AccessTokenInfo();
				thirdpartyBindInfo.setUserId(user.getId());
				thirdpartyBindInfo.setThirdpartyType(thirdpartyType);
				thirdpartyBindInfo.setThirdpartyUid(thirdpartyUid);
				thirdpartyBindInfo.setThirdpartyUname(thirdpartyUname);
				thirdpartyBindInfo.setThirdpartyAvatar(thirdpartyAvatar);
				
				thirdpartyBindInfo.setAccessToken(thirdpartyAccessToken);
				thirdpartyBindInfo.setRefreshToken(thirdpartyRefreshToken);
				thirdpartyBindInfo.setExpireIn(thirdpartyExpireIn);
				thirdpartyBindInfo.setCreaeTime(new Date());
				thirdpartyBindInfo.setSyncAlbum((short) 1);//默认为同步策略
				
				accessTokenService.save(thirdpartyBindInfo);
				// 重新加载用户
				user = userService.authUser(username, password);
				
				if(logger.isDebugEnabled()){
                    logger.debug("用户["+username+"]使用第三方账户["+thirdpartyUname+"]注册绑定成功");
                }
				
                //系统异步发送欢迎邮件
                mailService.sendWelcomeMail(username);
                
                //加载新注册的用户数据
                UserPassport userPassport = new UserPassport();
                userPassport.setUserId(user.getId());
                userPassport.setIdentity(String.valueOf(System.currentTimeMillis()));
                String ticket = passportService.createTicket(userPassport);
                userPassport.setTicket(ticket);
                //设置用户的secretkey
        		userPassport.setUserSecretKey(MobileClientAppServiceImpl.SECRET_KEY_DEFAULT);
                
                paramMap.put("userPassport", userPassport);
                paramMap.put("hostUser", user);
                return ResponseBuilderUtil.buildSuccessResult(paramMap);
			}
		} catch (Exception e) {
			logger.error("oauthRegister()", e);
			if(logger.isErrorEnabled()){
                logger.error("用户["+username+"]使用第三方账户["+thirdpartyUname+"]注册绑定失败");
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
