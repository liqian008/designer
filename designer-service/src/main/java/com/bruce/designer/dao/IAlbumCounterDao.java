package com.bruce.designer.dao;

import com.bruce.designer.model.AlbumCounter;


public interface IAlbumCounterDao extends IBaseDao<AlbumCounter, Integer> {
	
	/**
	 * 加载专辑统计数据信息
	 * @param albumId
	 * @return
	 */
	public AlbumCounter loadByAlbumId(int albumId);
	
}
