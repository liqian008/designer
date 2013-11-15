package com.bruce.designer.util;

import com.bruce.designer.model.User;
import com.bruce.designer.data.JsonResultBean;
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
    public static JsonResultBean generateSucceedResult(Object obj){
        JsonResultBean jsonResult = new JsonResultBean();
        jsonResult.setData(obj);
        return jsonResult;
    }
    
    /**
     * 生成失败的响应json
     * @param e
     * @return
     */
    public static JsonResultBean generateExceptionResult(DesignerException e){
        JsonResultBean jsonResult = new JsonResultBean();
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
    public static JsonResultBean generateExceptionResult(int errorCode, String message){
        JsonResultBean jsonResult = new JsonResultBean();
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
        
        JsonResultBean jsonSucceedResult = generateSucceedResult(user);
        System.out.println(jsonSucceedResult);
        
    }

}