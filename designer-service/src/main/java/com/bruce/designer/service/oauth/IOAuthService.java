package com.bruce.designer.service.oauth;

import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;

import com.bruce.designer.bean.AccessTokenInfo;

public interface IOAuthService {
	
	public static final String OAUTH_WEIBO_TYPE="SINA_WEIBO";
	public static final String OAUTH_TENCENT_WEIBO_TYPE="TENCENT_WEIBO";
	public static final String OAUTH_RENREN_TYPE="RENREN";
	
    public AccessTokenInfo loadTokenByCode(String code, String thirdpartyType)  throws WeiboException, JSONException;
   
    public void publish2Thirdparty(SharedContent sharedContent);
    
}
