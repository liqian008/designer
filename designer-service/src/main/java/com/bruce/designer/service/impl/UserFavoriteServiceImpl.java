package com.bruce.designer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.cache.user.FavoriteCache;
import com.bruce.designer.constants.ConstRedis;
import com.bruce.designer.dao.IUserFavoriteDao;
import com.bruce.designer.exception.RedisKeyNotExistException;
import com.bruce.designer.model.UserFavorite;
import com.bruce.designer.service.ICounterService;
import com.bruce.designer.service.IUserFavoriteService;

@Service
public class UserFavoriteServiceImpl implements IUserFavoriteService{ 
    
    private static final int FAVORITE_CACHE_MAX_COUNT = 5000;
    
    @Autowired
    private IUserFavoriteDao userFavoriteDao;
    @Autowired
    private ICounterService counterService;

    private FavoriteCache favoriteCache;
    
    @Override
    public boolean favorite(int uid, int albumId) {
        //检测是否favorite
        boolean isFavorite = isFavorite(uid, albumId);
        if (isFavorite) {
            return false;
        } else {
            Date currentTime = new Date(System.currentTimeMillis());
            //添加关注
            UserFavorite favorite = new UserFavorite();
            favorite.setUserId(uid);
            favorite.setFavoriteAlbumId(albumId);
            favorite.setCreateTime(currentTime);

            if (userFavoriteDao.save(favorite) > 0) {
                try {
                    favoriteCache.addFavorite(favorite);
                } catch (RedisKeyNotExistException e) {
                    List<UserFavorite> favoriteList = userFavoriteDao.getFavoriteList(uid, FAVORITE_CACHE_MAX_COUNT);
                    favoriteCache.setFavoriteList(uid, favoriteList);
                }
                //增加favorite计数
                counterService.increase(ConstRedis.COUNTER_KEY_FAVORITE + uid);
            } else {
                //TODO 添加失败队列修复
            }

            return true;
        }
    }
    
    @Override
    public boolean unfavorite(int uid, int albumId) {
        boolean isFavorite = isFavorite(uid, albumId);
        if (isFavorite) {
            try {
                //删除关注
                if (userFavoriteDao.deleteFavorite(uid, albumId) >= 0) {
                    //删cache减少计数
                    favoriteCache.removeFavorite(uid, albumId);
                    counterService.reduce(ConstRedis.COUNTER_KEY_FAVORITE + uid);
                } else {
                    //TODO 队列修复
                } 
                return true;
            } catch (Exception e) {
                
            }

        } else {
            return false;
        }
        return false;
    }
    
    @Override
    public boolean isFavorite(int uid, int albumId) {
        boolean isFavorite = false;
        try {
            isFavorite = favoriteCache.isFavorite(uid, albumId);
        } catch (Exception e) {
            List<UserFavorite> favoriteList = getFavoriteList(uid);
            favoriteCache.setFavoriteList(uid, favoriteList);
            if (favoriteList != null) {
                for (UserFavorite favorite : favoriteList) {
                    if (favorite.getFavoriteAlbumId() == albumId) {
                        isFavorite = true;
                        break;
                    }
                }
            }
        }
        return isFavorite;
    }
    
    @Override
    public List<UserFavorite> getFavoriteList(int userId) {
        List<UserFavorite> favoriteList;
        try {
            favoriteList = favoriteCache.getAllFavoriteList(userId);
        } catch (Exception e) {
            favoriteList = userFavoriteDao.getFavoriteList(userId, FAVORITE_CACHE_MAX_COUNT);
            favoriteCache.setFavoriteList(userId, favoriteList);
        }
        return favoriteList;
    }
    
    @Override
    public List<UserFavorite> getFavoriteList(int userId, int page, int pageSize) {
        List<UserFavorite> favoriteList = getFavoriteList(userId);
        if (favoriteList != null && favoriteList.size() > 0) {
            int size = favoriteList.size();
            int fromIndex = page * pageSize;
            int toIndex = (page + 1) * pageSize;
            if (size > fromIndex) {
                toIndex = toIndex > size ? size : toIndex;
                return favoriteList.subList(fromIndex, toIndex);
            }
        }
        return new ArrayList<UserFavorite>();
    }

    @Override
    public int deleteFavorite(int userId, int albumId) {
        return userFavoriteDao.deleteFavorite(userId, albumId);
    }
    
    @Override
    public int getFavoriteCount(int userId) {
        return (int)counterService.getCount(ConstRedis.COUNTER_KEY_FAVORITE + userId);
    }
    
} 
