package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.AlbumSlide;

public interface IAlbumSlideService extends IBaseService<AlbumSlide, Integer> {

	
	public AlbumSlide queryCoverSlide(int albumId);
	
	public List<AlbumSlide> querySlidesByAlbumId(int albumId);

}
