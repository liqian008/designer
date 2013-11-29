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

//    private FavoriteCache favoriteCache;
    
    @Override
    public boolean favorite(int userId, int albumId) {
        //检测是否favorite
        boolean isFavorite = isFavorite(userId, albumId);
        if (isFavorite) {
            return false;
        } else {
            if (userFavoriteDao.favorite(userId, albumId) > 0) {
            	
            } else {
                
            }
            // TODO 增加favorite计数
            counterService.incrFavorite(userId, albumId);
            return true;
        }
    }
    
    @Override
    public boolean unfavorite(int userId, int albumId) {
        try {
            //删除关注
            if (userFavoriteDao.deleteFavorite(userId, albumId) >= 0) {
                //删cache减少计数
//                favoriteCache.removeFavorite(userId, albumId);
//                counterService.reduce(ConstRedis.COUNTER_KEY_FAVORITE + userId);
            	// TODO 优化
//            	counterService.reduceFavorite(userId, albumId);
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
    
    @Override
    public boolean isFavorite(int userId, int albumId) {
//        boolean isFavorite = false;
//        try {
//            isFavorite = favoriteCache.isFavorite(userId, albumId);
//        } catch (Exception e) {
//            List<UserFavorite> favoriteList = getFavoriteList(userId);
//            favoriteCache.setFavoriteList(userId, favoriteList);
//            if (favoriteList != null) {
//                for (UserFavorite favorite : favoriteList) {
//                    if (favorite.getFavoriteAlbumId() == albumId) {
//                        isFavorite = true;
//                        break;
//                    }
//                }
//            }
//        }
//        return isFavorite;
        return userFavoriteDao.isFavorite(userId, albumId);
    }
    
    @Override
    public List<UserFavorite> getFavoriteList(int userId) {
        List<UserFavorite> favoriteList = userFavoriteDao.getFavoriteList(userId, FAVORITE_CACHE_MAX_COUNT);
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
    public int getFavoriteCount(int userId) {
    	// TODO 收藏数
//        return (int)counterService.getCount(ConstRedis.COUNTER_KEY_FAVORITE + userId);
//    	return counterService.getFavoriteCount(albumId)
    	return 0;
    }
    
} 
