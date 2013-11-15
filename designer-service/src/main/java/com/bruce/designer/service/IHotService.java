package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.Album;
import com.bruce.designer.model.Tag;
import com.bruce.designer.model.User;

public interface IHotService{

	// 获取热门tag
	public List<Tag> getHotTags(int limit);

	// 计算热门tag
	public List<Tag> calcHotTags(int limit);

	// 获取热门作品
	public List<Album> fallLoadHotAlbums(int start, int limit);

	// 获取热门设计师
	public List<User> fallLoadHotDesigners(int start, int limit);

//	// 计算热门作品
//	public List<Album> calcHotAlbums(int limit);
//	// 计算热门设计师
//	public List<User> calcHotDesigners(int limit);

}
