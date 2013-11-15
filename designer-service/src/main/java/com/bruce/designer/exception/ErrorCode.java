package com.bruce.designer.exception;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ErrorCode {

	/* 正确请求 */
	// public static final int SUCCESS = 0;

	/* 系统错误分配100内的错误码 */

	/* 系统未知错误 */
	public final static int SYSTEM_ERROR = 1;

	/* 参数不正确 */
	public final static int SYSTEM_MISSING_PARAM = 2;

	/* 验证码不正确 */
	public final static int SYSTEM_RANDIMG_INVALIDATE = 3;
	
	/* 没有更多数据 */
	public final static int SYSTEM_NO_MORE_DATA = 4;

	/* 权限相关分配100-200的错误码 */
	
	/* 权限不够，需登陆 */
	public static final int AUTHORIZE_NEED_LOGIN = 100; 
	/* 用户权限不够，需设计师权限 */
	public static final int AUTHORIZE_NEED_DESIGNER = 101;
	
	/* 账户相关分配200-300的错误码 */
	
	/* 获取用户失败 */
	public static final int USER_ERROR = 200;
	/* 用户已经被封禁 */
	public static final int USER_FORBIDDEN = 201;
	/* 用户名已存在 */
	public static final int USER_USERNAME_EXISTS = 202;
	/* 昵称已存在 */
	public static final int USER_NICKNAME_EXISTS = 203;
	/* Email已存在 */
	public static final int USER_EMAIL_EXISTS = 204;
	/* 账户密码不匹配 */
	public static final int USER_PASSWORD_NOT_MATCH = 205;
	/* 修改密码失败 */
	public static final int USER_CHANGE_PASSWORD_FAILED = 206;
	/* 用户不存在 */
	public static final int USER_NOT_EXIST = 207;

	/* OAuth未知错误 */
	public static final int OAUTH_ERROR = 220;
	/* Session不存在OAuth Token，无法进行绑定 */
	public static final int OAUTH_SESSIONTOKEN_ERROR = 220;
	/* 已经绑定同类型token，无法再次绑定 */
	public static final int OAUTH_ALREADY_BIND = 222;
	
	
	/* 关系图谱分配300-400的错误码 */
	/* 未知错误 */
	public static final int GRAPH_ERROR = 300;
	/* 尚未关注 */
	public static final int GRAPH_HAS_NOT_FOLLOW = 301;
	/* 已经关注过了 */
	public static final int GRAPH_ALREADY_FOLLOW = 302;
	
	/* 消息分配400-500的错误码 */
	/* 消息未知错误 */
	public static final int MESSAGE_ERROR = 400;
	/* 不支持的消息类型 */
	public static final int MESSAGE_UNSUPPORT_TYPE = 401;
	/* 不能给自己发消息 */
	public static final int MESSAGE_TO_SELF = 402;
	

	/* 上传分配x00-x00的错误码 */
	/* 未知错误 */
	public static final int UPLOAD_ERROR = 500;
	/* 文件格式不支持 */
	public static final int UPLOAD_FORMAT_NOT_SUPPORT = 501;
	/* 图片上传未知错误 */
	public static final int UPLOAD_IMAGE_ERROR = 502;
	/* 头像上传未知错误 */
	public static final int UPLOAD_AVATAR_ERROR = 503;
	/* 图片尺寸过大 */
	public static final int UPLOAD_IMAGE_OVERSIZE = 504;
	
	/* tag相关分配x00-x00的错误码 */
	/* 未知错误 */
	public static final int TAG_ERROR = 600;
	/* 未选择tag */
	public static final int TAG_EMPTY = 601;
	/* 选择的tag不存在 */
	public static final int TAG_NOT_EXISTS = 602;
	

	/* WEB AJAX请求分配10000-19999的错误码 */

	/* MCP请求分配20000-29999的错误码 */

	private final static Map<Integer, String> msgMap = new HashMap<Integer, String>();

	/**
	 * static initialize
	 */
	static {
		staticInit();
	}

	public final static String getMessage(int resultCode) {
		return msgMap.get(resultCode);
	}

	/**
	 * static init
	 */
	private static void staticInit() {
		Field[] fields = ErrorCode.class.getFields();
		for (Field field : fields) { 
			int modifiers = field.getModifiers();
			if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)//
					&& Modifier.isFinal(modifiers) && (field.getType() == int.class)) {
				try {
					msgMap.put(field.getInt(null), Messages.getString(field.getName()));
				} catch (IllegalArgumentException e) {
					// logger.error("staticInit()", e);
				} catch (IllegalAccessException e) {
					// logger.error("staticInit()", e);
				}
			}
		}
	}

	static class Messages {

		private static final String BUNDLE_NAME = "error_code_messages"; //$NON-NLS-1$

		static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

		public static String getString(String key) {
			try {
				return RESOURCE_BUNDLE.getString(key);
			} catch (MissingResourceException e) {
				return  key + " not found!";
			}
		}

		private Messages() {
		}
	}
}
