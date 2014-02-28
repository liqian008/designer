package com.bruce.designer.service;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.bruce.designer.model.Album;
import com.bruce.designer.model.Tag;
import com.bruce.designer.model.User;
import com.bruce.designer.util.ConfigUtil;

public interface IHotService{
	
	public static final int HOURLY_FLAG = 0;
	public static final int DAILY_FLAG = 1;
	public static final int WEEKLY_FLAG = 2;
	public static final int MONTHLY_FLAG = 3;
    
    public static final int HOT_ALBUM_DAILY_LIMIT = NumberUtils.toInt(ConfigUtil.getString("hot_album_daily_limit"), 20);
    public static final int HOT_ALBUM_WEEKLY_LIMIT = NumberUtils.toInt(ConfigUtil.getString("hot_album_weekly_limit"), 20);
    public static final int HOT_ALBUM_MONTHLY_LIMIT = NumberUtils.toInt(ConfigUtil.getString("hot_album_monthly_limit"), 20);
    
    public static final int HOT_DESIGNER_DAILY_LIMIT = NumberUtils.toInt(ConfigUtil.getString("hot_designer_daily_limit"), 32);
    public static final int HOT_DESIGNER_WEEKLY_LIMIT = NumberUtils.toInt(ConfigUtil.getString("hot_designer_weekly_limit"), 32);
    public static final int HOT_DESIGNER_MONTHLY_LIMIT = NumberUtils.toInt(ConfigUtil.getString("hot_designer_monthly_limit"), 32);

	// 获取热门tag
	public List<Tag> getHotTags(int limit);

	// 计算热门tag
	public List<Tag> calcHotTags(int limit);

	// 获取热门作品
	public List<Album> fallLoadHotAlbums(int mode);

	// 获取热门设计师
	public List<User> fallLoadHotDesigners(int mode);
	
	
//	public List<User> fallLoadHotDesigners(int start, int limit);

//	// 计算热门作品
//	public List<Album> calcHotAlbums(int limit);
//	// 计算热门设计师
//	public List<User> calcHotDesigners(int limit);

}
