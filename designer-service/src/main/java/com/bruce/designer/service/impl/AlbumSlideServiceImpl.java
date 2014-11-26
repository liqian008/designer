package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


	public int setCover(int userId, int albumId, int albumSlideId) {
		//清除cover标志
		albumSlideDao.clearCover(userId, albumId);
		//重新设置cover
		return albumSlideDao.setCover(userId, albumId, albumSlideId);
	}
	

	@Override
	public int updateByCriteria(AlbumSlide t, AlbumSlideCriteria criteria) {
		return albumSlideDao.updateByCriteria(t, criteria);
	}

	@Override
	public int deleteByCriteria(AlbumSlideCriteria criteria) {
		return albumSlideDao.deleteByCriteria(criteria);
	}

	@Override
	public List<AlbumSlide> queryAll(String orderByClause) {
		return albumSlideDao.queryAll(orderByClause);
	}

	@Override
	public List<AlbumSlide> queryByCriteria(AlbumSlideCriteria criteria) {
		return albumSlideDao.queryByCriteria(criteria);
	}

	
	@Override
	public int countByCriteria(AlbumSlideCriteria criteria) {
		return albumSlideDao.countByCriteria(criteria);
	}
	
	@Override
	public int deleteByAlbumId(int albumId) {
		return albumSlideDao.deleteByAlbumId(albumId);
	}
	

}
