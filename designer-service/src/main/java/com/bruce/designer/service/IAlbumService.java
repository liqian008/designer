package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.data.PagingData;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.AlbumCriteria;
import com.bruce.foundation.service.IFoundationDao;

public interface IAlbumService extends IFoundationDao<Album, Integer, AlbumCriteria> {
	
	/*根据albumId获取album的Map，通常用于从cache中获取album*/
//	public Map<Integer, Album> getAlbumMap(List<Integer> albumIds);
	
	public Album loadById(Integer id, boolean loadCount, boolean loadTags);

	public List<Album> queryAlbumByIds(List<Integer> idList);

	public List<Album> queryAlbumByUserId(int userId);

	public int deleteUserAlbum(int userId, int albumId);

	public PagingData<Album> pagingQuery(int userId, short albumStatus, int pageNo, int pageSize);

	
	public List<Album> fallLoadAlbums(int albumsTailId, int limit, boolean isLoadCount, boolean isLoadTags);

	public List<Album> fallLoadDesignerAlbums(int designerId, int albumsTailId, int limit, boolean isLoadCount, boolean isLoadTags);

	public List<Album> fallLoadUserFollowAlbums(int userId, int albumsTailId, int limit);
	
	//根据tagName查询引用的album列表，瀑布流
	public List<Album> fallLoadAlbumsByTagName(String tagName, int albumsTailId, int limit);

	public void initAlbumsWithCount(List<Album> albumList);

	public void initAlbumWithCount(Album album);
	
	public void initAlbumInteractionStatus(Album album, int userId);

	public void initAlbumsWithTags(List<Album> albumList);

	public void initAlbumWithTags(Album album);

}
