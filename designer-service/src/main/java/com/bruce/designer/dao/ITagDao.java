package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.Tag;
import com.bruce.designer.model.TagCriteria;
import com.bruce.foundation.dao.IFoundationDao;

public interface ITagDao extends IFoundationDao<Tag, Integer, TagCriteria>{ 
    
	public int getTagIdByName(String tagName);

	public String getTagNameById(int tagId);

	public List<Tag> calcHotTags(int limit);
	
} 
