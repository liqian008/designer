package com.bruce.designer.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.dao.IAlbumFavoriteDao;
import com.bruce.designer.dao.mapper.AlbumFavoriteMapper;
import com.bruce.designer.model.AlbumCriteria;
import com.bruce.designer.model.AlbumFavorite;
import com.bruce.designer.model.AlbumFavoriteCriteria;

@Repository
public class AlbumFavoriteDaoImpl implements IAlbumFavoriteDao, InitializingBean { 
    
    @Autowired
    private AlbumFavoriteMapper albumFavoriteMapper;
    
    public int save(AlbumFavorite t) {
        return albumFavoriteMapper.insertSelective(t);
    }

    public List<AlbumFavorite> queryAll() {
        return albumFavoriteMapper.selectByExample(null);
    }

    public int updateById(AlbumFavorite t) {
        return albumFavoriteMapper.updateByPrimaryKeySelective(t);
    }

    public int deleteById(Integer id) {
        return albumFavoriteMapper.deleteByPrimaryKey(id);
    }

    public AlbumFavorite loadById(Integer id) {
        return albumFavoriteMapper.selectByPrimaryKey(id);
    }

    
    @Override
    public List<AlbumFavorite> getFavoriteListByAlbumId(int albumId, int maxCount) {
        AlbumFavoriteCriteria criteria = new AlbumFavoriteCriteria();
        criteria.createCriteria().andAlbumIdEqualTo(albumId);
        List<AlbumFavorite> fansList = albumFavoriteMapper.selectByExample(criteria);
        return fansList;
    }
    
    @Override
    public List<AlbumFavorite> getFavoriteListByUserId(int userId, int favoriteTailId, int limit) {
        AlbumFavoriteCriteria criteria = new AlbumFavoriteCriteria();
        AlbumFavoriteCriteria.Criteria subCriteria = criteria.createCriteria();
        subCriteria.andUserIdEqualTo(userId);
        
        if(favoriteTailId>0){
			subCriteria.andIdLessThan(favoriteTailId);
		}
		criteria.setLimit(limit);
	    criteria.setOrderByClause("id desc");
        List<AlbumFavorite> fansList = albumFavoriteMapper.selectByExample(criteria);
        return fansList;
    }
    
    @Override
    public int favorite(int userId, int albumId) {
        Date currentTime = new Date(System.currentTimeMillis());
        //添加关注
        AlbumFavorite favorite = new AlbumFavorite();
        favorite.setUserId(userId);
        favorite.setAlbumId(albumId);
        favorite.setCreateTime(currentTime);
        return save(favorite);
    }

    @Override
    public int deleteFavorite(int userId, int albumId) {
        AlbumFavoriteCriteria criteria = new AlbumFavoriteCriteria();
        criteria.createCriteria().andUserIdEqualTo(userId).andAlbumIdEqualTo(albumId);
        return albumFavoriteMapper.deleteByExample(criteria);
    }
    
    @Override
    public boolean isFavorite(int userId, int albumId) {
        AlbumFavoriteCriteria criteria = new AlbumFavoriteCriteria();
        criteria.createCriteria().andUserIdEqualTo(userId).andAlbumIdEqualTo(albumId);
        List<AlbumFavorite> favoriteList = albumFavoriteMapper.selectByExample(criteria);
        return favoriteList!=null&&favoriteList.size()>0;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        
    }

	@Override
	public List<AlbumFavorite> fallLoadList(Integer tailId, int limit) {
		// TODO Auto-generated method stub
		return null;
	}
} 
