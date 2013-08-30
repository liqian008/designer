package com.bruce.designer.service.oauth.processor;

import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;

import com.bruce.designer.bean.AccessTokenInfo;
import com.bruce.designer.service.oauth.SharedContent;

public class OAuthTencentWbProcessor implements IOAuthProcessor{
    
    
    public void publishContent(SharedContent sharedContent){
        System.out.println("发布tencent weibo");
    }

	@Override
	public AccessTokenInfo loadToken(String code) throws WeiboException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loadThirdpartyUid(AccessTokenInfo tokenInfo)
			throws WeiboException, JSONException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccessTokenInfo loadThirdpartyProfile(AccessTokenInfo tokenInfo)
			throws WeiboException {
		// TODO Auto-generated method stub
		return null;
	}

}
