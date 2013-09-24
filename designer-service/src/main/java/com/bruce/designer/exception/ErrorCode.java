package com.bruce.designer.exception;

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
    /*Session不存在OAuth Token，无法进行绑定*/
    public static int OAUTH_SESSIONTOKEN_ERROR = 101;
    /*已经绑定同类型token，无法再次绑定*/
    public static int OAUTH_ALREADY_BIND = 102;
    
    /*获取用户失败*/
    public static int USER_ERROR = 110;
    /*用户名已存在*/
    public static int USERNAME_EXISTS_ERROR = 111;
    /*昵称已存在*/
    public static int NICKNAME_EXISTS_ERROR = 112;
    /*Email已存在*/
    public static int EMAIL_EXISTS_ERROR = 113;
    /*账户密码不匹配*/
    public static int LOGIN_FAILED_ERROR = 114;
    
    /*上传未知错误*/
    public static int UPLOAD_ERROR = 150;
    /*图片上传未知错误*/
    public static int UPLOAD_IMAGE_ERROR = 151;
    /*头像上传未知错误*/
    public static int UPLOAD_AVATAR_ERROR = 152;
    
    
    
    
    /*WEB AJAX请求分配10000-19999的错误码*/
    
    /*MCP请求分配20000-29999的错误码*/
}
