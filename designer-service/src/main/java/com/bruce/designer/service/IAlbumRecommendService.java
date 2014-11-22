package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.Album;
import com.bruce.designer.model.AlbumRecommend;
import com.bruce.designer.model.AlbumRecommendCriteria;
import com.bruce.foundation.service.IFoundationService;


public interface IAlbumRecommendService extends IFoundationService<AlbumRecommend, Integer, AlbumRecommendCriteria> {

	
	public List<Album> queryRecommendAlbums(int limit, boolean isLoadCount, boolean isLoadTags);
	
}
