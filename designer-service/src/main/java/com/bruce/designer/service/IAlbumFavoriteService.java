package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.AlbumFavorite;
import com.bruce.designer.model.AlbumFavoriteCriteria;
import com.bruce.foundation.service.IFoundationDao;

public interface IAlbumFavoriteService extends IFoundationDao<AlbumFavorite, Integer, AlbumFavoriteCriteria>{ 
    
    public List<AlbumFavorite> getFavoriteListByAlbumId(int userId);
    /*返回favorite的简单对象*/
    public List<AlbumFavorite> getFavoriteListByUserId(int userId, int favoriteTailId, int limit);
    /*返回favorite+album的组合数据*/
    public List<AlbumFavorite> fallLoadUserFavoriteAlbums(int userId, int favoriteTailId, int limit);

//    public List<AlbumFavorite> getFavoriteListByAlbumId(int userId, int page, int pageSize);

    public long getFavoriteCountByAlbumId(int userId);

    boolean isFavorite(int uid, int albumId);

    boolean favorite(int uid, int albumId, int designerId);

    boolean unfavorite(int uid, int albumId);  
    
} 
