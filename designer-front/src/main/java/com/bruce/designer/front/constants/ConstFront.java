package com.bruce.designer.front.constants;

import org.apache.commons.lang3.math.NumberUtils;

import com.bruce.designer.util.ConfigUtil;

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
	
	/*第三方系统的用户名*/
    public static final String THIRDPARTY_USERNAME = "_thirdpartyUsername";
	
	public static final String YYYY_MM_DD_FORMAT = "yyyy_MM_dd"; 
	
	public static final String YYYY_MM_DD_HH_MM_FORMAT = "yyyy-MM-dd HH:mm";
	
	/*访问域名*/
	public static final String DOMAIN = ConfigUtil.getString("domain");
	/*contextPath*/
	public static final String CONTEXT_PATH = ConfigUtil.getString("contextPath");
	
	
	public static final String WEIXIN_WELCOME_CONTENT = ConfigUtil.getString("weixin_welcome_content");
	
	public static final String WEIXIN_AUTO_REPLY_CONTENT = ConfigUtil.getString("weixin_auto_reply_content");
	
	
	/*日热门作品limit*/
	public static final int HOT_ALBUM_DAILY_LIMIT = NumberUtils.toInt(ConfigUtil.getString("hot_album_daily_limit"), 20);
	/*周热门作品limit*/
	public static final int HOT_ALBUM_WEEKLY_LIMIT = NumberUtils.toInt(ConfigUtil.getString("hot_album_weekly_limit"), 20);
	/*月热门作品limit*/
	public static final int HOT_ALBUM_MONTHLY_LIMIT = NumberUtils.toInt(ConfigUtil.getString("hot_album_monthly_limit"), 20);

	/*日热门设计师limit*/
    public static final int HOT_DESIGNER_DAILY_LIMIT = NumberUtils.toInt(ConfigUtil.getString("hot_designer_daily_limit"), 32);
    /*周热门设计师limit*/
    public static final int HOT_DESIGNER_WEEKLY_LIMIT = NumberUtils.toInt(ConfigUtil.getString("hot_designer_weekly_limit"), 32);
    /*月热门设计师limit*/
    public static final int HOT_DESIGNER_MONTHLY_LIMIT = NumberUtils.toInt(ConfigUtil.getString("hot_designer_monthly_limit"), 32);
    
    /*微信渠道——热门作品limit*/
	public static final int HOT_ALBUM_WEIXIN_LIMIT = NumberUtils.toInt(ConfigUtil.getString("hot_album_weixin_limit"), 3);
    /*微信渠道——热门设计师limit*/
    public static final int HOT_DESIGNER_WEIXIN_LIMIT = NumberUtils.toInt(ConfigUtil.getString("hot_designer_weixin_limit"), 3);

}
