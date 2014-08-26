package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.data.PagingData;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.AlbumCriteria;
import com.bruce.foundation.dao.IFoundationDao;

public interface IAlbumDao extends IFoundationDao<Album, Integer, AlbumCriteria> {

	public List<Album> fallLoadList(Integer tailId, int limit);
	
	
	public List<Album> queryAlbumByIds(List<Integer> idList);

//	public List<Album> queryAlbumByStatus(short status);
	
	public List<Album> queryAlbumByUserId(int userId);
	
//	public List<Album> queryList(int start, int limit);

    public int deleteUserAlbum(int userId, int albumId);

//	public List<Album> fallLoadDesignerAlbums(int designerId, int albumsTailId, int limit);

	public List<Album> fallLoadDesignerAlbums(List<Integer> designerIdList, int albumsTailId, int limit);

	PagingData<Album> pagingQuery(int userId, short albumStatus, int pageNo, int pageSize);

	/**
	 * 查询用户专辑数量
	 * @return 
	 */
	public List<CountCacheBean> queryUserAlbumCount(); 

	
//	public PagingData<Album> pagingQuery(short status, int pageNo, int pageSize);
	
}
