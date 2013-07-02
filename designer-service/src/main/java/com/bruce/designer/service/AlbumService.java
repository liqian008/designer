package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.bean.TbAlbum;

public interface AlbumService extends BaseService<TbAlbum, Integer> {

	public List<TbAlbum> queryAlbumByStatus(short status);

}
