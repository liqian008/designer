package com.bruce.designer.constants;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.bruce.designer.util.ConfigUtil;

public interface ConstConfig {

	//分布式的服务器编号index, 从1开始
	public static final String SERVER_INDEX = ConfigUtil.getString("server_index");
	
	/*默认每页显示的条数*/
	public static final int PAGE_SIZE_DEFAULT = NumberUtils.toInt(ConfigUtil.getString("page_size_default"), 20);

	
	/* contextPath */
	public static final String CONTEXT_PATH = ConfigUtil.getString("contextPath");
	
	/* 静态资源的域名 */
	public static final String RESOURCE_DOMAIN = ConfigUtil.getString("resource_domain");
	
	/* 文件系统的基本路径, 通常为$localDir/staticFile */
	public static final String UPLOAD_PATH_BASE = ConfigUtil.getString("upload_path_base");
	
	/* 本地文件系统的url前缀，补齐后续路径&文件名则可成为真正能访问的文件*/
	public static final String UPLOAD_URL_BASE = ConfigUtil.getString("upload_url_base");
	/* 完整的url前缀*/
	public static final String UPLOAD_URL_FULL = RESOURCE_DOMAIN + CONTEXT_PATH + UPLOAD_URL_BASE;
	
	/* pc上展示的专辑url前缀 */
	public static final String ALBUM_URL_WEB_PREFIX = ConfigUtil.getString("contextPath");
	/* mobile上展示的专辑url前缀 */
	public static final String ALBUM_URL_MOBILE_PREFIX = ConfigUtil.getString("contextPath");

	
	/*百度push的appkey和secretkey*/
	public static final String BAIDU_PUSH_APP_KEY= ConfigUtil.getString("baidu_push_api_key");
	public static final String BAIDU_PUSH_SECRET_KEY = ConfigUtil.getString("baidu_push_secret_key");
	
	/*七牛开发者的appkey和secretkey*/
	public static final String UPLOAD_QINIU_APP_KEY= ConfigUtil.getString("upload_qiniu_appkey");
	public static final String UPLOAD_QINIU_SECRET_KEY = ConfigUtil.getString("upload_qiniu_secretkey");
	
	/*七牛的bucket*/
	public static final String UPLOAD_QINIU_BUCKET = ConfigUtil.getString("upload_qiniu_bucket");
	/*七牛的绑定的cname域名*/
	public static final String UPLOAD_QINIU_BIND_DOMAIN = ConfigUtil.getString("upload_qiniu_bind_domain");
	

	/*阿里oss开发者的appkey和secretkey*/
	public static final String UPLOAD_ALI_OSS_APP_KEY= ConfigUtil.getString("upload_ali_oss_appkey");
	public static final String UPLOAD_ALI_OSS_SECRET_KEY = ConfigUtil.getString("upload_ali_oss_secretkey");
	
	/*阿里oss开发者的的bucket*/
	public static final String UPLOAD_ALI_OSS_BUCKET = ConfigUtil.getString("upload_ali_oss_bucket");
	/*阿里oss绑定的cname域名*/
	public static final String UPLOAD_ALI_OSS_BIND_DOMAIN = ConfigUtil.getString("upload_ali_oss_bind_domain");
	
	
	/*微信分享的标题模板*/
	public static final String SHARE_TITLE_TEMPLATE = ConfigUtil.getString("share_title_template");
	/*微信分享的内容模板*/
	public static final String SHARE_CONTENT_TEMPLATE = ConfigUtil.getString("share_content_template");
	
	
	/*发布至第三方的总开关（通常切换测试、线上环境）*/
	public static final boolean THIRDPARTY_SHAREOUT_ON = BooleanUtils.toBoolean(ConfigUtil.getString("thirdparty_shareout_on"), "true", "false");

	
	/*微博注册情况下发布的文案*/
	public static final String WEIBO_REGISTER_POST_CONTENT = ConfigUtil.getString("weibo_register_post_content");
	/*微博注册情况下发布的图片*/
	public static final String WEIBO_REGISTER_POST_IMG = ConfigUtil.getString("weibo_register_post_img");
	
}
