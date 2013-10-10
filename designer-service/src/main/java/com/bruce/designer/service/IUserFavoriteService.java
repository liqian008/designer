package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.UserFavorite;

public interface IUserFavoriteService extends IBaseService<UserFavorite, Long>{ 
    
    public List<UserFavorite> getFavoriteList(int userId, int maxCount);

    public int deleteFavorite(int userId, int albumId);
    
} 
