package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.Tag;

public interface ITagService extends IBaseService<Tag, Integer> {

	public List<String> getTagNamesByAlbumId(int albumId);
	
	//创建tag及文章关联
	public int tagAlbum(int albumId, List<String> tagNameList);
	
	//获取tag
//	public List<Tag> getHotTags(int limit);
//	
//	//计算热门tag
//	public List<Tag> calcHotTags(int limit);

	public int getTagIdByName(String tagName, boolean createNew);

	public String getTagNameById(int tagId);
	
	
	
}
