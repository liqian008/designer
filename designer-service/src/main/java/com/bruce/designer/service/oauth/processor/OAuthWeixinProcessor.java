package com.bruce.designer.service.oauth.processor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import weibo4j.Account;
import weibo4j.TimelineAdvanced;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONObject;

import com.bruce.designer.exception.DesignerException;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.model.AccessTokenInfo;
import com.bruce.designer.service.oauth.IOAuthService;
import com.bruce.designer.service.oauth.SharedInfo;

/**
 * 处理微信登录的processor
 * @author liqian
 *
 */
@Component
public class OAuthWeixinProcessor implements IOAuthProcessor {
	
	private weibo4j.Oauth weiboOauth = new weibo4j.Oauth();

	/**
	 * 用code换取weixin的accessToken
	 * 
	 * @param code
	 * @return
	 * @throws DesignerException
	 */
	public AccessTokenInfo loadToken(HttpServletRequest request) {
		weibo4j.http.AccessToken wbToken;
		try {
			wbToken = weiboOauth.getAccessTokenByCode(request.getParameter("code"));
			AccessTokenInfo tokenInfo = parseWeiboToken(wbToken);
			return tokenInfo;
		} catch (Exception e) {
			throw new DesignerException(ErrorCode.OAUTH_ERROR);
		}
	}

	/**
	 * 根据token获取第三方的用户Id，后续用于在token表中查询其绑定记录
	 * 
	 * @param tokenInfo
	 * @return
	 * @throws DesignerException
	 */
	public String loadThirdpartyUid(AccessTokenInfo tokenInfo) {
		// 根据token获取第三方用户资料
		Account am = new Account(tokenInfo.getAccessToken());
		JSONObject uidJson;
		try {
			uidJson = am.getUid();
			String thirdpartyUid = uidJson.getString("uid");
			// 第三方uid
			tokenInfo.setThirdpartyUid(thirdpartyUid);
			return thirdpartyUid;
		} catch (Exception e) {
			throw new DesignerException(ErrorCode.OAUTH_ERROR);
		}
	}

	/**
	 * 加载第三方账户的基本信息，如昵称、性别、年龄等（目前只使用了昵称），通常用在前端的注册页面中展示
	 * 
	 * @param tokenInfo
	 * @return
	 * @throws DesignerException
	 */
	public AccessTokenInfo loadThirdpartyProfile(AccessTokenInfo tokenInfo) {
		//因为微信登录操作时，已经取到了个人资料等，因此在此步省略，无需再次获取了
		return null;
	}

	/**
	 * 发布时的分享（微信无此功能）
	 * 
	 * @param sharedInfo
	 * @throws DesignerException
	 */
	public void shareout(SharedInfo sharedInfo) {
		//do nothing
	}

	/**
	 * 将weixin的accessToken转换为通用accessToken
	 * 
	 * @param at
	 * @return
	 */
	private static AccessTokenInfo parseWeiboToken(weibo4j.http.AccessToken wbToken) {
		AccessTokenInfo accessTokenInfo = new AccessTokenInfo();
		accessTokenInfo.setAccessToken(wbToken.getAccessToken());
		accessTokenInfo.setRefreshToken(wbToken.getRefreshToken());
		accessTokenInfo.setExpireIn(Long.parseLong(wbToken.getExpireIn()));
		accessTokenInfo.setThirdpartyType(IOAuthService.OAUTH_WEIBO_TYPE);
		return accessTokenInfo;
	}
}