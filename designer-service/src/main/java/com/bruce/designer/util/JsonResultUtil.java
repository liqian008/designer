package com.bruce.designer.util;

import com.bruce.designer.model.User;
import com.bruce.designer.data.JsonResultObject;
import com.bruce.designer.exception.DesignerException;


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
    
    /**
     * 生成失败的响应json
     * @param errorCode
     * @param message
     * @return
     */
    public static JsonResultObject generateExceptionResult(int errorCode, String message){
        JsonResultObject jsonResult = new JsonResultObject();
        jsonResult.setResult(errorCode);
        jsonResult.setMessage(message);
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