package com.bruce.designer.service;

import java.util.List;
import com.bruce.baseService.IBaseService;

import com.bruce.designer.bean.AlbumSlide;

public interface AlbumSlideService extends IBaseService<AlbumSlide, Integer> {

	public List<AlbumSlide> querySlidesByAlbumId(int albumId);

}
