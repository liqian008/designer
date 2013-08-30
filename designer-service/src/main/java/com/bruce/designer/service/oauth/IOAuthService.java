package com.bruce.designer.service.oauth;


import com.bruce.designer.bean.AccessTokenInfo;
import com.bruce.designer.exception.oauth.OAuthException;

public interface IOAuthService {
	
	public static final String OAUTH_WEIBO_TYPE="SINA_WEIBO";
	public static final String OAUTH_TENCENT_WEIBO_TYPE="TENCENT_WEIBO";
	public static final String OAUTH_RENREN_TYPE="RENREN";
	
    public AccessTokenInfo loadTokenByCode(String code, String thirdpartyType) throws OAuthException;
   
    public void publish2Thirdparty(SharedContent sharedContent) throws OAuthException;
    
}
