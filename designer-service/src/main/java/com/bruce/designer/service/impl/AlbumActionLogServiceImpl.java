package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.dao.IAlbumActionLogDao;
import com.bruce.designer.model.AlbumActionLog;
import com.bruce.designer.service.IAlbumActionLogService;

@Service
public class AlbumActionLogServiceImpl implements IAlbumActionLogService {

	@Autowired
	private IAlbumActionLogDao albumActionLogDao;

	public int save(AlbumActionLog t) {
		return albumActionLogDao.save(t);
	}

	public int updateById(AlbumActionLog t) {
		return albumActionLogDao.updateById(t);
	}

	public int deleteById(Long id) {
		return albumActionLogDao.deleteById(id);
	}

	public AlbumActionLog loadById(Long id) {
		return albumActionLogDao.loadById(id);
	}

	@Override
	public List<AlbumActionLog> queryAll() {
		return albumActionLogDao.queryAll();
	}

	@Override
	public int logBrowse(int albumId, int designerId, int userId) {
		return albumActionLogDao.logBrowse(albumId, designerId, userId);
	}

	@Override
	public int logLike(int albumId, int designerId, int userId) {
		return albumActionLogDao.logLike(albumId, designerId, userId);
	}

	@Override
	public int logFavorite(int albumId, int designerId, int userId) {
		return albumActionLogDao.logFavorite(albumId, designerId, userId);
	}

	@Override
	public int logComment(int albumId, int designerId, int userId) {
		return albumActionLogDao.logComment(albumId, designerId, userId);
	}

}
