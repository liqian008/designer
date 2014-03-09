package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.Album;
import com.bruce.designer.data.PagingData;

public interface IAlbumService extends IBaseService<Album, Integer> {
	
	public Album loadById(Integer id, boolean loadCount, boolean loadTags);

	public List<Album> queryAlbumByIds(List<Integer> idList);

//	public List<Album> queryAlbumByStatus(short status);

	public List<Album> queryAlbumByUserId(int userId);

	public int deleteUserAlbum(int userId, int albumId);

	public PagingData<Album> pagingQuery(int userId, short albumStatus, int pageNo, int pageSize);

//	public long increceBroswer(int albumId, int albumSlideId);
//
//	public long increceComment(int albumId, int albumSlideId);
//
//	public long increceLike(int albumId, int albumSlideId);
	
	public List<Album> fallLoadAlbums(int albumsTailId, int limit, boolean isLoadCount, boolean isLoadTags);

	public List<Album> fallLoadDesignerAlbums(int designerId, int albumsTailId, int limit, boolean isLoadCount, boolean fallLoadAlbums);

	public List<Album> fallLoadUserFollowAlbums(int userId, int albumsTailId, int limit);
	
	public List<Album> fallLoadUserFavoriteAlbums(int userId, int favoriteTailId, int limit);
	
	//根据tagName查询引用的album列表，瀑布流
	public List<Album> fallLoadAlbumsByTagName(String tagName, int albumsTailId, int limit);

	public void initAlbumsWithCount(List<Album> albumList);

	public void initAlbumWithCount(Album album);
	
	public void initAlbumInteractionStatus(Album album, int userId);

	public void initAlbumsWithTags(List<Album> albumList);

	public void initAlbumWithTags(Album album);

}
