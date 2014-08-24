package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.AlbumLike;
import com.bruce.designer.model.AlbumLikeCriteria;
import com.bruce.foundation.dao.IFoundationDao;

public interface IAlbumLikeDao extends IFoundationDao<AlbumLike, Integer, AlbumLikeCriteria>{ 
    
    public List<AlbumLike> getLikeListByAlbumId(int albumId, int maxCount); 

    public int deleteLike(int userId, int albumId);
    
    public int like(int userId, int albumId);
    
    public boolean isLike(int userId, int albumId);

    
} 
