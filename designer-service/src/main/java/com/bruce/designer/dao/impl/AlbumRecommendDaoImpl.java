package com.bruce.designer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.dao.IAlbumRecommendDao;
import com.bruce.designer.dao.mapper.AlbumRecommendMapper;
import com.bruce.designer.model.AlbumRecommend;
import com.bruce.designer.model.AlbumRecommendCriteria;

@Repository
public class AlbumRecommendDaoImpl implements IAlbumRecommendDao{

	@Autowired
	private AlbumRecommendMapper albumRecommendMapper;

	public int save(AlbumRecommend t) {
	    return albumRecommendMapper.insertSelective(t);
	}

	public int updateById(AlbumRecommend t) {
		return albumRecommendMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Integer id) {
		return albumRecommendMapper.deleteByPrimaryKey(id);
	}

	public AlbumRecommend loadById(Integer id) {
	    return albumRecommendMapper.selectByPrimaryKey(id);
	}

	public List<AlbumRecommend> queryAll() {
		return albumRecommendMapper.selectByExample(null);
	}
	
	@Override
	public List<AlbumRecommend> queryAll(int limit) {
		AlbumRecommendCriteria criteria = new AlbumRecommendCriteria();
		criteria.createCriteria();
		criteria.setLimit(limit);
		criteria.setOrderByClause("sort desc");
		return albumRecommendMapper.selectByExample(criteria);
	}
	
	@Override
	public List<AlbumRecommend> fallLoadList(Integer tailId, int limit) {
		return null;
	}

}