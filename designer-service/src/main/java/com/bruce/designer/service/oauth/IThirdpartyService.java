package com.bruce.designer.service.oauth;

import weibo4j.model.WeiboException;

import com.bruce.designer.bean.User;


public interface IThirdpartyService {
    
    /**
     * 根据返回的code创建用户
     * @param code
     * @return
     */
    public User getUserByWeiboCode(String code) throws WeiboException;
    
    /**
     * 发布至weibo
     * @return
     */
    public long publish2Weibo();
    
    
   
}
