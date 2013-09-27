package com.bruce.designer.constants;

public interface ConstService{

	public static final short ALBUM_DELETE_STATUS = 0;
	
	public static final short ALBUM_OPEN_STATUS = 1;
	
	public static final short ALBUM_PRIVATE_STATUS = 2;
	
	//////////////////////用户状态常量////////////////////////////////
	/*正常用户状态*/
	public static final short USER_STATUS_OPEN = 1;
	/*设计师原始状态*/
	public static final short DESIGNER_APPLY_NONE = 0;
	/*设计师申请已发送*/
	public static final short DESIGNER_APPLY_SENT = 1;
	/*设计师审核通过*/
	public static final short DESIGNER_APPLY_PASSED = 2;
	/*设计师审核拒绝*/
	public static final short DESIGNER_APPLY_DENIED = 3;
	
	//////////////////////消息常量////////////////////////////////
	
	/*系统广播专用的fromId*/
	public static final int MESSAGE_SYSTEM_SOURCE_ID = 0;
	/*系统类型消息*/
	public static final short MESSAGE_TYPE_BROADCAST = 0;
	/*关注类型消息*/
	public static final short MESSAGE_TYPE_FLOWER = 1;
	/*评论类型消息*/
	public static final short MESSAGE_TYPE_COMMENT = 2;
	/*like类型消息*/
	public static final short MESSAGE_TYPE_LIKE = 3;
	/*收藏类型消息*/
	public static final short MESSAGE_TYPE_FAVORITIES = 4;
	/*@类型消息*/
    public static final short MESSAGE_TYPE_AT = 5;
	/*聊天类型消息（点到点）*/
    public static final short MESSAGE_TYPE_CHAT = 100;
	
	/*未读消息状态*/
	public static final short MESSAGE_STATUS_UNREAD = 0;
	/*已读消息状态*/
	public static final short MESSAGE_STATUS_READ = 1;
	
	
	//////////////////////上传常量////////////////////////////////
	
	/*普通文件类型*/
	public static final short UPLOAD_FILE_TYPE_NORMAL = 0;
	/*图片文件类型*/
	public static final short UPLOAD_FILE_TYPE_IMAGE = 1;
	/*头像文件类型*/
	public static final short UPLOAD_FILE_TYPE_AVATAR = 2;
	
	/*原始图片类型*/
	public static final String UPLOAD_IMAGE_SPEC_ORIGINAL = "";
	/*大图片规格*/
	public static final String UPLOAD_IMAGE_SPEC_LARGE = "large";
	/*中图片规格*/
	public static final String UPLOAD_IMAGE_SPEC_MEDIUM = "medium";
	/*小图片规格*/
    public static final String UPLOAD_IMAGE_SPEC_SMALL = "small";
	/*小图片规格*/
	public static final String UPLOAD_IMAGE_SPEC_TINY = "tiny";
	
	
	
}
