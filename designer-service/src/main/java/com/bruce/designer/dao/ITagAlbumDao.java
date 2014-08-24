package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.TagAlbum;
import com.bruce.designer.model.TagAlbumCriteria;
import com.bruce.foundation.dao.IFoundationDao;

public interface ITagAlbumDao extends IFoundationDao<TagAlbum, Long, TagAlbumCriteria>{ 
    
	public List<TagAlbum> fallLoadDataList(int tagId, int albumsTailId, int limit);

	public List<TagAlbum> queryTagIdsByAlbumId(int albumId);

	public int deleteByAlbumId(int albumId);
    
} 
