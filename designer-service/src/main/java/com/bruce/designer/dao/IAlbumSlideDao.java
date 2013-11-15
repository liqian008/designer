package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.AlbumSlide;

public interface IAlbumSlideDao extends IBaseDao<AlbumSlide, Integer> {

	public AlbumSlide queryCoverSlide(int albumId);
	
	public List<AlbumSlide> querySlidesByAlbumId(int albumId);
}
