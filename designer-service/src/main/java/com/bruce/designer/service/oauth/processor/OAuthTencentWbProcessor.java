package com.bruce.designer.service.oauth.processor;

import com.bruce.designer.bean.AccessTokenInfo;
import com.bruce.designer.exception.oauth.OAuthException;
import com.bruce.designer.service.oauth.SharedContent;

public class OAuthTencentWbProcessor implements IOAuthProcessor{
    
    
    public void publishContent(SharedContent sharedContent) throws OAuthException{
        System.out.println("发布tencent weibo");
    }

	@Override
	public AccessTokenInfo loadToken(String code) throws OAuthException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loadThirdpartyUid(AccessTokenInfo tokenInfo) throws OAuthException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccessTokenInfo loadThirdpartyProfile(AccessTokenInfo tokenInfo) throws OAuthException {
		// TODO Auto-generated method stub
		return null;
	}

}
