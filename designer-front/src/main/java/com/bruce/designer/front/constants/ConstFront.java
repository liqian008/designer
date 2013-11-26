package com.bruce.designer.front.constants;

public interface ConstFront {
	/*jsonView*/
	public static final String JSON_VIEW = "jsonView";
	
	/* 登录的sessionAttribute*/
	public static final String CURRENT_USER = "_currentUser";
	
	/* 用户对象的Attribute*/
    public static final String REQUEST_USER_ATTRIBUTE = "_user";
    
    /* 用户对象的Attribute*/
    public static final String MESSAGE_TARGET_USER_ATTRIBUTE = "_message_target_user";

	/*登录or新用户注册的tab激活状态*/
	public static final String REGISTER_ACTIVE = "_registerActive";
	
    /*登录错误消息*/
	public static final String LOGIN_ERROR_MESSAGE = "_loginErrorMessage";

    /*登录错误消息*/
	public static final String REG_ERROR_MESSAGE = "_regErrorMessage";
    
    /*跳转请求的后续链接*/
	public static final String REDIRECT_URL = "_redirect";
	
	/*跳转时的文字提示*/
	public static final String REDIRECT_PROMPT = "_redirectPrompt";
	
	/*accessToken的临时key*/
	public static final String TEMPLATE_ACCESS_TOKEN = "_templateAccessToken";
	
	public static final String YYYY_MM_DD_FORMAT = "yyyy_MM_dd"; 
	
	public static final String YYYY_MM_DD_HH_MM_FORMAT = "yyyy-MM-dd HH:mm";
	

}
