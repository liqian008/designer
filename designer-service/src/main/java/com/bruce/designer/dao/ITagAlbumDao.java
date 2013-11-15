package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.TagAlbum;

public interface ITagAlbumDao extends IBaseDao<TagAlbum, Long>{ 
    
	public List<TagAlbum> fallLoadDataList(int tagId, int albumsTailId, int limit);

	public List<TagAlbum> queryTagIdsByAlbumId(int albumId);
    
} 
