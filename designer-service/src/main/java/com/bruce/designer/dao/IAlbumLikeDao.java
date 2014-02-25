package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.AlbumLike;

public interface IAlbumLikeDao extends IBaseDao<AlbumLike, Integer>{ 
    
    public List<AlbumLike> getLikeListByAlbumId(int albumId, int maxCount); 

    public int deleteLike(int userId, int albumId);
    
    public int like(int userId, int albumId);
    
    public boolean isLike(int userId, int albumId);

    
} 
