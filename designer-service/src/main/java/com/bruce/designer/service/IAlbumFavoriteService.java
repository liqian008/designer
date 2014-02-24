package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.AlbumFavorite;

public interface IAlbumFavoriteService extends IBaseService<AlbumFavorite, Integer>{ 
    
    public List<AlbumFavorite> getFavoriteListByAlbumId(int userId);
    
    public List<AlbumFavorite> getFavoriteListByUserId(int userId, int favoriteTailId, int limit);

//    public List<AlbumFavorite> getFavoriteListByAlbumId(int userId, int page, int pageSize);

    public long getFavoriteCountByAlbumId(int userId);

    boolean isFavorite(int uid, int albumId);

    boolean favorite(int uid, int albumId, int designerId);

    boolean unfavorite(int uid, int albumId);  
    
} 
