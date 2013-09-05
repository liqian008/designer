package com.bruce.designer.exception.oauth;

import com.bruce.designer.bean.User;


/**
 * 
 * @author liqian
 *
 */
public class JsonResultUtil{
    
    /**
     * 生成成功的响应json
     * @param obj
     * @return
     */
    public static JsonResultObject generateSucceedResult(Object obj){
        JsonResultObject jsonResult = new JsonResultObject();
        jsonResult.setData(obj);
        return jsonResult;
    }
    
    /**
     * 生成失败的响应json
     * @param e
     * @return
     */
    public static JsonResultObject generateExceptionResult(DesignerException e){
        JsonResultObject jsonResult = new JsonResultObject();
        jsonResult.setResult(e.getErrorCode());
        jsonResult.setMessage(e.getMessage());
        return jsonResult;
    }
    
    public static void main(String[] args) {
//        JsonResultObject jsonExceptionResult = generateExceptionResult(new OAuthException("oauth error"));
//        System.out.println(jsonExceptionResult);
        
        
        User user = new User();
        user.setUsername("liqian");
        user.setNickname("nick_liqian");
        JsonResultObject jsonSucceedResult = generateSucceedResult(user);
        System.out.println(jsonSucceedResult);
        
    }

}