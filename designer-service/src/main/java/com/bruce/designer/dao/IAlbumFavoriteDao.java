package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.AlbumFavorite;
import com.bruce.designer.model.AlbumFavoriteCriteria;
import com.bruce.foundation.dao.IFoundationDao;

public interface IAlbumFavoriteDao extends IFoundationDao<AlbumFavorite, Integer, AlbumFavoriteCriteria> {

	public int favorite(int userId, int albumId);

	public int deleteFavorite(int userId, int albumId);

	public boolean isFavorite(int userId, int albumId);

	public List<AlbumFavorite> getFavoriteListByUserId(int userId, int favoriteTailId, int limit);

	public List<AlbumFavorite> getFavoriteListByAlbumId(int albumId, int maxCount);
}
