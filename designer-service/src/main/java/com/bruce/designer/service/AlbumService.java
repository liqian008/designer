package com.bruce.designer.service;

import java.util.List;

import com.bruce.baseSkeleton.service.IBaseService;
import com.bruce.designer.bean.Album;

public interface AlbumService extends IBaseService<Album, Integer> {

	public List<Album> queryAlbumByStatus(short status);
	
	public List<Album> queryAlbumByUserId(int userId);

}
