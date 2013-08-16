package com.bruce.designer.service.oauth;


import com.bruce.baseSkeleton.service.IBaseService;
import com.bruce.designer.bean.AccessTokenInfo;



public interface IAccessTokenService extends IBaseService<AccessTokenInfo, Integer> {
    
    //创建accessToken
    //public int addAccessTokenInfo(AccessTokenInfo accessTokenInfo);
    //更新accessToken
    //public int updateAccessTokenInfo(AccessTokenInfo accessTokenInfo);
    
    public AccessTokenInfo loadAccessTokenInfo(String thirdpartyUid, String thirdpartyType);
    
    
}
