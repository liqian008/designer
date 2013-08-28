package com.bruce.designer.service.oauth;

import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;

import com.bruce.designer.bean.AccessTokenInfo;

public interface IOAuthService {
     
    public AccessTokenInfo loadTokenByWeiboCode(String code)  throws WeiboException, JSONException;
   
}
