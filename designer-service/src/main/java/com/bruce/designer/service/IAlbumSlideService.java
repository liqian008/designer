package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.bean.AlbumSlide;

public interface IAlbumSlideService extends IBaseService<AlbumSlide, Integer> {

	public List<AlbumSlide> querySlidesByAlbumId(int albumId);

}