package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.AlbumRecommend;



public interface IAlbumRecommendDao extends IBaseDao<AlbumRecommend, Integer>{
	
	List<AlbumRecommend> queryAll(int limit);
	
} 
