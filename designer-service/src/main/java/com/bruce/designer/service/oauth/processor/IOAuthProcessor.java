package com.bruce.designer.service.oauth.processor;

import javax.servlet.http.HttpServletRequest;

import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;

import com.bruce.designer.bean.AccessTokenInfo;
import com.bruce.designer.exception.oauth.OAuthException;
import com.bruce.designer.service.oauth.SharedContent;

/**
 * 第三方发布接口
 * 
 * @author liqian
 * 
 */
public interface IOAuthProcessor {
	
    /**
     * 用code换取accessToken
     * @param code
     * @return
     * @throws OAuthException
     */
	public AccessTokenInfo loadToken(HttpServletRequest request) throws OAuthException;

	/**
	 * 根据token获取第三方的用户Id，后续用于在token表中查询其绑定记录
	 * 
	 * @param tokenInfo
	 * @return
	 * @throws WeiboException
	 * @throws JSONException
	 */
	public String loadThirdpartyUid(AccessTokenInfo tokenInfo) throws OAuthException;

	/**
	 * 加载第三方账户的基本信息，如昵称、性别、年龄等（目前只使用了昵称），通常用在前端的注册页面中展示
	 * 
	 * @param tokenInfo
	 * @return
	 * @throws WeiboException
	 */
	public AccessTokenInfo loadThirdpartyProfile(AccessTokenInfo tokenInfo) throws OAuthException;

	/**
	 * 分享至第三方
	 * @param sharedContent
	 * @throws OAuthException
	 */
	public void shareout(SharedContent sharedContent) throws OAuthException;

}
