package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.AlbumFavorite;

public interface IAlbumFavoriteDao extends IBaseDao<AlbumFavorite, Integer>{ 
    
    
    public int deleteFavorite(int userId, int albumId);
    
    public int favorite(int userId, int albumId);
    
    public boolean isFavorite(int userId, int albumId);

    public List<AlbumFavorite> getFavoriteListByAlbumId(int albumId, int maxCount); 
    
	public List<AlbumFavorite> getFavoriteListByUserId(int userId, int favoriteTailId, int limit);
    
} 
