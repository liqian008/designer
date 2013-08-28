package com.bruce.designer.bean;

import java.util.List;

public class UserBase{
	
	private List<AccessTokenInfo> accessTokenList;

    public List<AccessTokenInfo> getAccessTokenList() {
        return accessTokenList;
    }

    public void setAccessTokenList(List<AccessTokenInfo> accessTokenList) {
        this.accessTokenList = accessTokenList;
    }
	
}