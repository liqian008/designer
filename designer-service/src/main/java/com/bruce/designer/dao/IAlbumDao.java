package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.Album;

public interface IAlbumDao extends IBaseDao<Album, Integer> {

	public List<Album> queryAlbumByStatus(short status);
	
	public List<Album> queryAlbumByUserId(int userId);
	
//	public PagingData<Album> pagingQuery(short status, int pageNo, int pageSize);
	
}
