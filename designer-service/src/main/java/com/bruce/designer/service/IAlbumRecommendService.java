package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.Album;
import com.bruce.designer.model.AlbumRecommend;
import com.bruce.designer.model.AlbumRecommendCriteria;
import com.bruce.foundation.service.IFoundationDao;


public interface IAlbumRecommendService extends IFoundationDao<AlbumRecommend, Integer, AlbumRecommendCriteria> {

	
	public List<Album> queryRecommendAlbums(int limit);
	
}
