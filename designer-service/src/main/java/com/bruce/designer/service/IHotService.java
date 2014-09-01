package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.Album;
import com.bruce.designer.model.Tag;
import com.bruce.designer.model.User;

public interface IHotService {

	public static final short HOURLY_FLAG = 0;
	public static final short DAILY_FLAG = 1;
	public static final short WEEKLY_FLAG = 2;
	public static final short MONTHLY_FLAG = 3;
	public static final short YEARLY_FLAG = 4;

	// 获取热门tag
	public List<Tag> getHotTags(int limit);

	// 计算热门tag
	public List<Tag> calcHotTags(int limit);

	// 获取热门作品
	public List<Album> fallLoadHotAlbums(short mode, int limit);

	// 获取热门设计师
	public List<User> fallLoadHotDesigners(short mode, int limit);

	// public List<User> fallLoadHotDesigners(int start, int limit);

	// // 计算热门作品
	// public List<Album> calcHotAlbums(int limit);
	// // 计算热门设计师
	// public List<User> calcHotDesigners(int limit);

}
