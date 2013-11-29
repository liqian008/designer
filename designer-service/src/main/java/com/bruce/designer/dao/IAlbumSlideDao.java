package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.AlbumSlide;

public interface IAlbumSlideDao extends IBaseDao<AlbumSlide, Integer> {

	// public AlbumSlide queryCoverSlide(int albumId);

	public List<AlbumSlide> querySlidesByAlbumId(int albumId);

	/**
	 * 清除封面
	 * @param userId
	 * @param albumId
	 * @return
	 */
	public int clearCover(int userId, int albumId);

	/**
	 * 设置封面
	 * @param albumId
	 * @param albumSlideId
	 * @return
	 */
	public int setCover(int userId, int albumId, int albumSlideId);
}
