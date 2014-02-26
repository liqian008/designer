package com.bruce.designer.constants;

public interface ConstService {

	public static final short ALBUM_DELETE_STATUS = 0;

	public static final short ALBUM_OPEN_STATUS = 1;

	public static final short ALBUM_PRIVATE_STATUS = 2;

	// ////////////////////用户状态常量////////////////////////////////
	/* 用户正常状态 */
	public static final short USER_STATUS_OPEN = 1;
	/* 用户被封禁状态 */
	public static final short USER_STATUS_FORBIDDEN = 2;
	
	/* 设计师原始状态 */
	public static final short DESIGNER_APPLY_NONE = 0;
	/* 设计师申请已发送 */
	public static final short DESIGNER_APPLY_SENT = 1;
	/* 设计师审核通过 */
	public static final short DESIGNER_APPLY_APPROVED = 2;
	/* 设计师审核拒绝 */
	public static final short DESIGNER_APPLY_DENIED = 3;

	// ////////////////////用户状态常量////////////////////////////////
	/* 未选定cover的状态 */
	public static final short ALBUM_SLIDE_ISNOT_COVER = 0;
	/* 选定cover的状态 */
	public static final short ALBUM_SLIDE_IS_COVER = 1;

	// ////////////////////消息常量////////////////////////////////

	/* 系统类型消息 */
	public static final int MESSAGE_TYPE_SYSTEM = 1;
	/* 评论类型消息 */
	public static final int MESSAGE_TYPE_COMMENT = 2;
	/* 关注类型消息 */
	public static final int MESSAGE_TYPE_FLOWER = 3;
	/* like类型消息 */
	public static final int MESSAGE_TYPE_LIKE = 4;
	/* 收藏类型消息 */
	public static final int MESSAGE_TYPE_FAVORITIES = 5;
	/* @类型消息 */
	public static final int MESSAGE_TYPE_AT = 6;
	/* 聊天类型消息 */
	// public static final short MESSAGE_TYPE_CHAT = 100;

	/* 系统广播专用的fromId */
	public static final int MESSAGE_DELIVER_ID_BROADCAST = MESSAGE_TYPE_SYSTEM;
	/* 评论类型消息 */
	public static final int MESSAGE_DELIVER_ID_COMMENT = MESSAGE_TYPE_COMMENT;
	/* 关注类型消息 */
	public static final int MESSAGE_DELIVER_ID_FLOWER = MESSAGE_TYPE_FLOWER;
	/* like类型消息 */
	public static final int MESSAGE_DELIVER_ID_LIKE = MESSAGE_TYPE_LIKE;
	/* 收藏类型消息 */
	public static final int MESSAGE_DELIVER_ID_FAVORITES = MESSAGE_TYPE_FAVORITIES;
	/* @类型消息 */
	public static final int MESSAGE_DELIVER_ID_AT = MESSAGE_TYPE_AT;

	/* 消息可读状态 */
	public static final short MESSAGE_READ = 0;
	/* 消息可读状态 */
	public static final short MESSAGE_UNREAD = 1;
	
	
	/* 操作记录重复状态 */
	public static final short ACTION_LOG_REPEAT = 0;
	/* 操作记录正常状态 */
	public static final short ACTION_LOG_NORMAL = 1;

	// ////////////////////上传常量////////////////////////////////

	/* 普通文件类型 */
	public static final short UPLOAD_FILE_TYPE_NORMAL = 0;
	/* 图片文件类型 */
	public static final short UPLOAD_FILE_TYPE_IMAGE = 1;
	/* 头像文件类型 */
	public static final short UPLOAD_FILE_TYPE_AVATAR = 2;

	/* 原始图片类型 */
	public static final String UPLOAD_IMAGE_SPEC_ORIGINAL = "";
	/* 大图片规格 */
	public static final String UPLOAD_IMAGE_SPEC_LARGE = "large";
	/* 中图片规格 */
	public static final String UPLOAD_IMAGE_SPEC_MEDIUM = "medium";
	/* 小图片规格 */
	public static final String UPLOAD_IMAGE_SPEC_SMALL = "small";

}
