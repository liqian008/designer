package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.dao.IAlbumSlideDao;
import com.bruce.designer.model.AlbumSlide;
import com.bruce.designer.model.AlbumSlideCriteria;
import com.bruce.designer.service.IAlbumSlideService;

@Service
public class AlbumSlideServiceImpl implements IAlbumSlideService {
	
	@Autowired
	private IAlbumSlideDao albumSlideDao;

	public int save(AlbumSlide t) {
		return albumSlideDao.save(t);
	}

	public List<AlbumSlide> queryAll() {
		return albumSlideDao.queryAll();
	}

	public int updateById(AlbumSlide t) {
		return albumSlideDao.updateById(t);
	}

	public int deleteById(Integer id) {
		return albumSlideDao.deleteById(id);
	}

	public AlbumSlide loadById(Integer id) {
		return albumSlideDao.loadById(id);
	}

	public List<AlbumSlide> querySlidesByAlbumId(int albumId) {
		return albumSlideDao.querySlidesByAlbumId(albumId);
	}

}
