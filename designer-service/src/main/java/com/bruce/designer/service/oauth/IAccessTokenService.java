package com.bruce.designer.service.oauth;


import java.util.List;

import com.bruce.baseSkeleton.service.IBaseService;
import com.bruce.designer.bean.AccessTokenInfo;



public interface IAccessTokenService extends IBaseService<AccessTokenInfo, Integer> {
    
    //创建accessToken
    //public int addAccessTokenInfo(AccessTokenInfo accessTokenInfo);
    //更新accessToken
    //public int updateAccessTokenInfo(AccessTokenInfo accessTokenInfo);
    
    public List<AccessTokenInfo> queryByUserId(Integer userId);
    
    public AccessTokenInfo load(String thirdpartyUid, Short thirdpartyType);
    
    public int delete(Integer userId, Short thirdpartyType);
    
}
