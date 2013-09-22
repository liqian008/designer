package com.bruce.designer.exception.oauth;

public interface ErrorCode {
    
    /*正确请求*/
    public static int SUCCESS = 0;
    /*错误请求*/
    public static int SYSTEM_ERROR = 1;
    /*系统错误分配0-1000内的错误码*/
    public static int USER_NOT_LOGIN = 10;
    
    /*OAuth错误，分配100-100的错误码*/
    /*OAuth未知错误*/
    public static int OAUTH_ERROR = 100;
    /*已经绑定同类型token，无法再次绑定*/
    public static int OAUTH_ALREADY_BIND = 101;
    
    /*上传未知错误*/
    public static int UPLOAD_ERROR = 110;
    /*图片上传未知错误*/
    public static int UPLOAD_IMAGE_ERROR = 111;
    /*头像上传未知错误*/
    public static int UPLOAD_AVATAR_ERROR = 112;
    
    
    
    
    /*WEB AJAX请求分配10000-19999的错误码*/
    
    /*MCP请求分配20000-29999的错误码*/
}
