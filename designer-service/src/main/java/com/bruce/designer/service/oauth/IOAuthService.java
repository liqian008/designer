package com.bruce.designer.service.oauth;


import javax.servlet.http.HttpServletRequest;

import com.bruce.designer.bean.AccessTokenInfo;
import com.bruce.designer.exception.oauth.OAuthException;

public interface IOAuthService {
	
	public static final String OAUTH_WEIBO_TYPE="SINA_WEIBO";
	
	public static final String OAUTH_TENCENT_TYPE="TENCENT";
	
	public static final String OAUTH_RENREN_TYPE="RENREN";
	
    public AccessTokenInfo loadTokenByCallback(HttpServletRequest request, String thirdpartyType) throws OAuthException;
   
    public void shareout(SharedContent sharedContent) throws OAuthException;
    
}
