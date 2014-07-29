package com.bruce.designer.service.oauth;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bruce.designer.model.AccessTokenInfo;
import com.bruce.designer.model.Album;

public interface IOAuthService {

	public static final Short OAUTH_WEIBO_TYPE = 1;// "SINA_WEIBO";

	public static final Short OAUTH_TENCENT_TYPE = 2; // "TENCENT";

	public static final Short OAUTH_RENREN_TYPE = 3; // "RENREN";

	public AccessTokenInfo loadTokenByCallback(HttpServletRequest request, short thirdpartyType);
	
	public AccessTokenInfo loadTokenByClient(String thirdpartyUid, String accessToken, String refreshToken, long expireIn,  short thirdpartyType);
	    

	public void shareout(List<SharedInfo> sharedInfoList);

//	public void shareout(SharedInfo sharedInfo);
//	public void shareOut(Album album, short sharedType);

}
