package com.bruce.designer.macp.constants;

import org.apache.commons.lang3.math.NumberUtils;

import com.bruce.designer.util.ConfigUtil;

public interface ConstPaging {
	
	
	/*tab0 page limit*/
	public static final int ALBUM_LATEST_LIMIT = NumberUtils.toInt(ConfigUtil.getString("album_latest_limit"), 6);
	public static final int ALBUM_RECOMMEND_LIMIT = NumberUtils.toInt(ConfigUtil.getString("album_recommend_limit"), 6);
	public static final int ALBUM_MYVIEW_LIMIT = NumberUtils.toInt(ConfigUtil.getString("album_myview_limit"), 6);
	
	/*tab1 page limit*/
	public static final int ALBUM_HOT_WEEKLY_LIMIT = NumberUtils.toInt(ConfigUtil.getString("album_hot_weekly_limit"), 6);
	public static final int ALBUM_HOT_MONTHLY_LIMIT = NumberUtils.toInt(ConfigUtil.getString("album_hot_monthly_limit"), 6);
	public static final int ALBUM_HOT_YEARLY_LIMIT = NumberUtils.toInt(ConfigUtil.getString("album_hot_yearly_limit"), 6);
	
	/*设计师专辑limit*/
	public static final int ALBUM_USER_LIMIT = NumberUtils.toInt(ConfigUtil.getString("album_user_limit"), 6);
	/*我的收藏专辑limit*/
	public static final int ALBUM_FAVORITE_LIMIT = NumberUtils.toInt(ConfigUtil.getString("album_favorite_limit"), 6);
	
	/*评论 limit*/
	public static final int COMMENT_LIMIT = NumberUtils.toInt(ConfigUtil.getString("comment_limit"), 20);
	/*消息 limit*/
	public static final int MESSAGE_LIMIT = NumberUtils.toInt(ConfigUtil.getString("message_limit"), 10);
	
	/*粉丝 limit*/
	public static final int FANS_LIMIT = NumberUtils.toInt(ConfigUtil.getString("fans_limit"), 10);
	/*关注 limit*/
	public static final int FOLLOWS_LIMIT = NumberUtils.toInt(ConfigUtil.getString("follow_limit"), 10);
	
}
