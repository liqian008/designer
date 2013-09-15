package com.bruce.designer.constants;

public interface ConstService{

	public static final short ALBUM_DELETE_STATUS = 0;
	
	public static final short ALBUM_OPEN_STATUS = 1;
	
	public static final short ALBUM_PRIVATE_STATUS = 2;
	
	
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
	
	/*系统用户UID*/
	public static final int SYSTEM_USER_ID = 0;
	/*系统类型消息*/
	public static final short MESSAGE_TYPE_SYSTEM = 0;
	/*聊天类型消息*/
	public static final short MESSAGE_TYPE_CHAT = 1;
	/*@类型消息*/
	public static final short MESSAGE_TYPE_AT = 2;
	/*关注类型消息*/
	public static final short MESSAGE_TYPE_FLOWER = 3;
	/*评论类型消息*/
	public static final short MESSAGE_TYPE_COMMENT = 4;
	/*like类型消息*/
	public static final short MESSAGE_TYPE_LIKE = 5;
	/*收藏类型消息*/
	public static final short MESSAGE_TYPE_FAVORITIES = 6;
	
	
	/*未读消息状态*/
	public static final short MESSAGE_STATUS_UNREAD = 0;
	/*已读消息状态*/
	public static final short MESSAGE_STATUS_READ = 1;
	
}
