package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.bean.Album;
import com.bruce.designer.data.PagingData;

public interface IAlbumService extends IBaseService<Album, Integer> {

	public List<Album> queryAlbumByStatus(short status);
	
	public List<Album> queryAlbumByUserId(int userId);
	
	public PagingData<Album> pagingQuery(short status, int offset, int limit);
}