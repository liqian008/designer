package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.AlbumSlide;
import com.bruce.designer.model.AlbumSlideCriteria;
import com.bruce.foundation.service.IFoundationDao;

public interface IAlbumSlideService extends IFoundationDao<AlbumSlide, Integer, AlbumSlideCriteria> {

	
	public int setCover(int userId, int albumId, int albumSlideId);
	 
	public List<AlbumSlide> querySlidesByAlbumId(int albumId);

}
