package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.Tag;

public interface ITagDao extends IBaseDao<Tag, Integer>{ 
    
	public int getTagIdByName(String tagName);

	public String getTagNameById(int tagId);

	public List<Tag> calcHotTags(int limit);
	
} 
