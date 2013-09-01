package com.bruce.designer.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserBase{
	
	private Map<String, AccessTokenInfo> accessTokenMap = new HashMap<String, AccessTokenInfo>();
	
//	private List<AccessTokenInfo> accessTokenList;
//
//    public List<AccessTokenInfo> getAccessTokenList() {
//        return accessTokenList;
//    }
//
//    public void setAccessTokenList(List<AccessTokenInfo> accessTokenList) {
//        this.accessTokenList = accessTokenList;
//		//同时刷新tokenMap
//        refreshTokenMap();
//    }

//	/**
//	 * 根据User对象中tokenList刷新tokenMap
//	 * @return
//	 */
//	public Map<String, AccessTokenInfo> refreshTokenMap(){
//		return refreshTokenMap(accessTokenList);
//	}
	
	public Map<String, AccessTokenInfo> getAccessTokenMap() {
		return accessTokenMap;
	}

	public void setAccessTokenMap(Map<String, AccessTokenInfo> accessTokenMap) {
		this.accessTokenMap = accessTokenMap;
	}
	
	/**
	 * 根据tokenList刷新tokenMap
	 * @return
	 */
	public Map<String, AccessTokenInfo> refreshTokenMap(List<AccessTokenInfo> tokenList){
		if(tokenList!=null && tokenList.size() > 0){
			for(AccessTokenInfo token: tokenList){
				accessTokenMap.put(token.getThirdpartyType(), token);
			}
		}
		return accessTokenMap;
	}
	
	
    
}