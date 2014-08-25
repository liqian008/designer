package com.bruce.designer.service.oauth;


import java.util.List;

import com.bruce.designer.model.AccessTokenInfo;
import com.bruce.designer.model.AccessTokenInfoCriteria;
import com.bruce.foundation.service.IFoundationDao;



public interface IAccessTokenService extends IFoundationDao<AccessTokenInfo, Integer, AccessTokenInfoCriteria> {
    
    //创建accessToken
    //public int addAccessTokenInfo(AccessTokenInfo accessTokenInfo);
    //更新accessToken
    //public int updateAccessTokenInfo(AccessTokenInfo accessTokenInfo);
    
    public List<AccessTokenInfo> queryByUserId(Integer userId);
    
    public AccessTokenInfo load(String thirdpartyUid, Short thirdpartyType);
    
    public int delete(Integer userId, Short thirdpartyType);
    
}
