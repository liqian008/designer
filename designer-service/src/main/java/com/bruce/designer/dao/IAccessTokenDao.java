package com.bruce.designer.dao;


import java.util.List;

import com.bruce.designer.model.AccessTokenInfo;



public interface IAccessTokenDao extends IBaseDao<AccessTokenInfo, Integer> {
    
    public List<AccessTokenInfo> queryByUserId(Integer userId);
    
    public AccessTokenInfo load(String thirdpartyUid, Short thirdpartyType);
    
    public int delete(Integer userId, Short thirdpartyType);
    
}
