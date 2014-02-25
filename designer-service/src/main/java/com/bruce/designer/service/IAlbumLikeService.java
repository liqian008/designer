package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.AlbumLike;

public interface IAlbumLikeService extends IBaseService<AlbumLike, Integer>{ 
    
    public List<AlbumLike> getLikeListByAlbumId(int userId);


    public List<AlbumLike> getLikeListByAlbumId(int userId, int page, int pageSize);

    public long getLikeCountByAlbumId(int userId);

    boolean isLike(int uid, int albumId);

    boolean like(int uid, int albumId, int designerId);

    boolean unlike(int uid, int albumId);  
    
} 
