package com.bruce.designer.dao;


import java.util.List;

import com.bruce.designer.model.AccessTokenInfo;
import com.bruce.designer.model.AccessTokenInfoCriteria;
import com.bruce.foundation.dao.IFoundationDao;



public interface IAccessTokenDao extends IFoundationDao<AccessTokenInfo, Integer, AccessTokenInfoCriteria> {
    
    public List<AccessTokenInfo> queryByUserId(Integer userId);
    
    public AccessTokenInfo load(String thirdpartyUid, Short thirdpartyType);
    
    public int delete(Integer userId, Short thirdpartyType);

	public int updateSyncStatus(short syncStatus, String thirdpartyUid, Short thirdpartyType); 
    
}
