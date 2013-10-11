package com.bruce.designer.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.dao.IUserFavoriteDao;
import com.bruce.designer.dao.mapper.UserFavoriteMapper;
import com.bruce.designer.model.UserFavorite;
import com.bruce.designer.model.UserFavoriteCriteria;

@Repository
public class UserFavoriteDaoImpl implements IUserFavoriteDao, InitializingBean { 
    
    @Autowired
    private UserFavoriteMapper userFavoriteMapper;
    
    public int save(UserFavorite t) {
        return userFavoriteMapper.insertSelective(t);
    }

    public List<UserFavorite> queryAll() {
        return userFavoriteMapper.selectByExample(null);
    }

    public int updateById(UserFavorite t) {
        return userFavoriteMapper.updateByPrimaryKeySelective(t);
    }

    public int deleteById(Long id) {
        return userFavoriteMapper.deleteByPrimaryKey(id);
    }

    public UserFavorite loadById(Long id) {
        return userFavoriteMapper.selectByPrimaryKey(id);
    }

    
    @Override
    public List<UserFavorite> getFavoriteList(int userId, int maxCount) {
        UserFavoriteCriteria criteria = new UserFavoriteCriteria();
        criteria.createCriteria().andUserIdEqualTo(userId);
        List<UserFavorite> fansList = userFavoriteMapper.selectByExample(criteria);
        return fansList;
    }
    
    @Override
    public int favorite(int userId, int albumId) {
        Date currentTime = new Date(System.currentTimeMillis());
        //添加关注
        UserFavorite favorite = new UserFavorite();
        favorite.setUserId(userId);
        favorite.setFavoriteAlbumId(albumId);
        favorite.setCreateTime(currentTime);
        return save(favorite);
    }

    @Override
    public int deleteFavorite(int userId, int albumId) {
        UserFavoriteCriteria criteria = new UserFavoriteCriteria();
        criteria.createCriteria().andUserIdEqualTo(userId).andFavoriteAlbumIdEqualTo(albumId);
        return userFavoriteMapper.deleteByExample(criteria);
    }
    
    @Override
    public boolean isFavorite(int userId, int albumId) {
        UserFavoriteCriteria criteria = new UserFavoriteCriteria();
        criteria.createCriteria().andUserIdEqualTo(userId).andFavoriteAlbumIdEqualTo(albumId);
        List<UserFavorite> favoriteList = userFavoriteMapper.selectByExample(criteria);
        return favoriteList!=null&&favoriteList.size()>0;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        
    }
} 
