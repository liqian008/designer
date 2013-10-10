package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.UserFavorite;

public interface IUserFavoriteService{ 
//extends IBaseService<UserFavorite, Long>{ 
    
    public List<UserFavorite> getFavoriteList(int userId);

    public int deleteFavorite(int userId, int albumId);

    public List<UserFavorite> getFavoriteList(int userId, int page, int pageSize);

    public int getFavoriteCount(int userId);

    boolean isFavorite(int uid, int albumId);

    boolean favorite(int uid, int albumId);

    boolean unfavorite(int uid, int albumId);  
    
} 
