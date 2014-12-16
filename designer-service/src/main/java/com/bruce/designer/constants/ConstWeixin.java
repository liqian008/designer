package com.bruce.designer.constants;

import com.bruce.designer.util.ConfigUtil;

public interface ConstWeixin {
	
	/*微信客户端应用的id&key*/
	public static final String WX_MOBILE_APP_ID = ConfigUtil.getString("weixin_mobile_appid");
	public static final String WX_MOBILE_APP_SECRET_KEY = ConfigUtil.getString("weixin_mobile_appsecret");
	
	//微信oauth accessToken api
	public static final String WX_OAUTH_ACCESS_TOKEN_API = ConfigUtil.getString("weixin_oauth_accesstoken_url");
	//微信oauth 获取个人资料api
	public static final String WX_OAUTH_USER_INFO_API = ConfigUtil.getString("weixin_oauth_userinfo_url");
	
	/*微信公众帐号的图片url*/
	public static final String WEIXINMP_QRCODE_URL = ConfigUtil.getString("weixinmp_qrcode_url");
	
	
}
