package com.bruce.designer.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.dao.IAlbumLikeDao;
import com.bruce.designer.dao.mapper.AlbumLikeMapper;
import com.bruce.designer.model.AlbumLike;
import com.bruce.designer.model.AlbumLikeCriteria;

@Repository
public class AlbumLikeDaoImpl implements IAlbumLikeDao, InitializingBean { 
    
    @Autowired
    private AlbumLikeMapper albumLikeMapper;
    
    public int save(AlbumLike t) {
        return albumLikeMapper.insertSelective(t);
    }

    public List<AlbumLike> queryAll() {
        return albumLikeMapper.selectByExample(null);
    }

    public int updateById(AlbumLike t) {
        return albumLikeMapper.updateByPrimaryKeySelective(t);
    }

    public int deleteById(Integer id) {
        return albumLikeMapper.deleteByPrimaryKey(id);
    }

    public AlbumLike loadById(Integer id) {
        return albumLikeMapper.selectByPrimaryKey(id);
    }

    
    @Override
    public List<AlbumLike> getLikeListByAlbumId(int albumId, int maxCount) {
        AlbumLikeCriteria criteria = new AlbumLikeCriteria();
        criteria.createCriteria().andAlbumIdEqualTo(albumId);
        List<AlbumLike> fansList = albumLikeMapper.selectByExample(criteria);
        return fansList;
    }
    
    @Override
    public int like(int userId, int albumId) {
        Date currentTime = new Date(System.currentTimeMillis());
        //添加赞
        AlbumLike albumLike = new AlbumLike();
        albumLike.setUserId(userId);
        albumLike.setAlbumId(albumId);
        albumLike.setCreateTime(currentTime);
        return save(albumLike);
    }

    @Override
    public int deleteLike(int userId, int albumId) {
        AlbumLikeCriteria criteria = new AlbumLikeCriteria();
        criteria.createCriteria().andUserIdEqualTo(userId).andAlbumIdEqualTo(albumId);
        return albumLikeMapper.deleteByExample(criteria);
    }
    
    @Override
    public boolean isLike(int userId, int albumId) {
        AlbumLikeCriteria criteria = new AlbumLikeCriteria();
        criteria.createCriteria().andUserIdEqualTo(userId).andAlbumIdEqualTo(albumId);
        List<AlbumLike> likeList = albumLikeMapper.selectByExample(criteria);
        return likeList!=null&&likeList.size()>0;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
	public int updateByCriteria(AlbumLike t, AlbumLikeCriteria criteria) {
		return albumLikeMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteByCriteria(AlbumLikeCriteria criteria) {
		return albumLikeMapper.deleteByExample(criteria);
	}

	@Override
	public List<AlbumLike> queryAll(String orderByClause) {
		AlbumLikeCriteria criteria = new AlbumLikeCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<AlbumLike> queryByCriteria(AlbumLikeCriteria criteria) {
		return albumLikeMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(AlbumLikeCriteria criteria) {
		return albumLikeMapper.countByExample(criteria);
	}
} 
