package com.bruce.designer.exception.oauth;

public interface ResultCode {
    
    /*正确请求*/
    public static int SUCCESS = 0;
    /*错误请求*/
    public static int ERROR = 1;
    /*系统错误分配0-1000内的错误码*/
    public static int USER_NOT_LOGIN = 10;
    public static int OAUTH_FAILED = 20;
    /*已经绑定同类型token，无法再次绑定*/
    public static int OAUTH_ALREADY_BIND = 30;
    
    
    
    /*WEB AJAX请求分配10000-19999的错误码*/
    
    /*MCP请求分配20000-29999的错误码*/
}
