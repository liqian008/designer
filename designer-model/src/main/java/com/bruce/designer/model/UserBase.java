package com.bruce.designer.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserBase{
	
	private Map<Short, AccessTokenInfo> accessTokenMap = new HashMap<Short, AccessTokenInfo>();
	
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
	
	
	
	private int fansCount;
	
	private int followsCount;
	
	
	public Map<Short, AccessTokenInfo> getAccessTokenMap() {
		return accessTokenMap;
	}

	public void setAccessTokenMap(Map<Short, AccessTokenInfo> accessTokenMap) {
		this.accessTokenMap = accessTokenMap;
	}
	
	/**
	 * 根据tokenList刷新tokenMap
	 * @return
	 */
	public Map<Short, AccessTokenInfo> refreshTokenMap(List<AccessTokenInfo> tokenList){
		if(tokenList!=null && tokenList.size() > 0){
			for(AccessTokenInfo token: tokenList){
				accessTokenMap.put(token.getThirdpartyType(), token);
			}
		}
		return accessTokenMap;
	}

	public int getFansCount() {
		return fansCount;
	}

	public void setFansCount(int fansCount) {
		this.fansCount = fansCount;
	}

	public int getFollowsCount() {
		return followsCount;
	}

	public void setFollowsCount(int followsCount) {
		this.followsCount = followsCount;
	}
	
    
}