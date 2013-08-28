package com.bruce.designer.bean;

public class AccessTokenInfoBase {
    
    //sina weibo转换
    public static AccessTokenInfo parseWeibo(weibo4j.http.AccessToken at) {
        AccessTokenInfo accessTokenInfo = new AccessTokenInfo();
        accessTokenInfo.setAccessToken(at.getAccessToken());
        accessTokenInfo.setRefreshToken(at.getRefreshToken());
        accessTokenInfo.setExpiresIn(Long.parseLong(at.getExpireIn()));
        accessTokenInfo.setThirdpartyType("SINA_WEIBO");
        return accessTokenInfo;
    }

}
