package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.AlbumRecommend;
import com.bruce.designer.model.AlbumRecommendCriteria;
import com.bruce.foundation.dao.IFoundationDao;



public interface IAlbumRecommendDao extends IFoundationDao<AlbumRecommend, Integer, AlbumRecommendCriteria>{
	
	List<AlbumRecommend> queryAll(int limit);
	
} 
