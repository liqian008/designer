package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.Album;
import com.bruce.designer.model.AlbumRecommend;


public interface IAlbumRecommendService extends IBaseService<AlbumRecommend, Integer> {

	
	public List<Album> queryRecommendAlbums(int limit);
	
}
