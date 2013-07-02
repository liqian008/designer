package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.bean.TbAlbumSlide;

public interface AlbumSlideService extends BaseService<TbAlbumSlide, Integer> {

	public List<TbAlbumSlide> querySlidesByAlbumId(int albumId);

}
