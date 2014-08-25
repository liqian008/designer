package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.AlbumLike;
import com.bruce.designer.model.AlbumLikeCriteria;
import com.bruce.foundation.service.IFoundationDao;

public interface IAlbumLikeService extends IFoundationDao<AlbumLike, Integer, AlbumLikeCriteria>{ 
    
    public List<AlbumLike> getLikeListByAlbumId(int albumId);

    public List<AlbumLike> getLikeListByAlbumId(int userId, int page, int pageSize);

    public long getLikeCountByAlbumId(int albumId);

    boolean isLike(int uid, int albumId);

    boolean like(int uid, int albumId, int designerId);

    boolean unlike(int uid, int albumId);  
    
} 
