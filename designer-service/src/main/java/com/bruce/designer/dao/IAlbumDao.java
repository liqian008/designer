package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.data.PagingData;
import com.bruce.designer.model.Album;

public interface IAlbumDao extends IBaseDao<Album, Integer> {

	public List<Album> queryAlbumByIds(List<Integer> idList);

//	public List<Album> queryAlbumByStatus(short status);
	
	public List<Album> queryAlbumByUserId(int userId);
	
//	public List<Album> queryList(int start, int limit);

    public int deleteUserAlbum(int userId, int albumId);

//	public List<Album> fallLoadDesignerAlbums(int designerId, int albumsTailId, int limit);

	public List<Album> fallLoadDesignerAlbums(List<Integer> designerIdList, int albumsTailId, int limit);

	PagingData<Album> pagingQuery(int userId, short albumStatus, int pageNo, int pageSize);

	
//	public PagingData<Album> pagingQuery(short status, int pageNo, int pageSize);
	
}
