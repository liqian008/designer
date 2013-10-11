package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.UserFavorite;

public interface IUserFavoriteDao extends IBaseDao<UserFavorite, Long>{ 
    
    public List<UserFavorite> getFavoriteList(int userId, int maxCount); 

    public int deleteFavorite(int userId, int albumId);
    
    public int favorite(int userId, int albumId);
    
    public boolean isFavorite(int userId, int albumId);

    
} 
