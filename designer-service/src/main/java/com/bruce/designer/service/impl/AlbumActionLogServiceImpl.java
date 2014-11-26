package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.dao.IAlbumActionLogDao;
import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.model.AlbumActionLog;
import com.bruce.designer.model.AlbumActionLogCriteria;
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

	@Override
	public int updateByCriteria(AlbumActionLog t, AlbumActionLogCriteria criteria) {
		return albumActionLogDao.updateByCriteria(t, criteria);
	}

	public int deleteById(Long id) {
		return albumActionLogDao.deleteById(id);
	}

	@Override
	public int deleteByCriteria(AlbumActionLogCriteria criteria) {
		return albumActionLogDao.deleteByCriteria(criteria);
	}

	public AlbumActionLog loadById(Long id) {
		return albumActionLogDao.loadById(id);
	}

	@Override
	public List<AlbumActionLog> queryAll() {
		return albumActionLogDao.queryAll();
	}

	@Override
	public List<AlbumActionLog> queryAll(String orderByClause) {
		return albumActionLogDao.queryAll(orderByClause);
	}

	@Override
	public List<AlbumActionLog> queryByCriteria(AlbumActionLogCriteria criteria) {
		return albumActionLogDao.queryByCriteria(criteria);
	}
	
	@Override
	public int countByCriteria(AlbumActionLogCriteria criteria) {
		return albumActionLogDao.countByCriteria(criteria);
	}


	/**
	 * 浏览记录
	 */
	@Override
	public int logBrowse(int albumId, int designerId, int userId) {
		return albumActionLogDao.logBrowse(albumId, designerId, userId);
	}

	/**
	 * 查询之前是否有赞操作
	 */
	@Override
	public int logLike(int albumId, int designerId, int userId) {
		// 如果之前like过，重复操作不做记录
		boolean everLiked = albumActionLogDao.existLikeLog(albumId, userId);// TODO
																			// 从缓存查询
		if (everLiked) {
			return 0;
		} else {
			return albumActionLogDao.logLike(albumId, designerId, userId, everLiked);
		}
	}

	/**
	 * 查询之前是否有收藏操作
	 */
	@Override
	public int logFavorite(int albumId, int designerId, int userId) {
		// 如果之前favorite过，重复操作不做记录
		boolean everFavorited = albumActionLogDao.existFavoriteLog(albumId, userId);
		if (everFavorited) {//
			return 0;
		} else {
			return albumActionLogDao.logFavorite(albumId, designerId, userId, everFavorited);
		}
	}

	/**
	 * 评论记录
	 */
	@Override
	public int logComment(int albumId, int designerId, int userId) {
		return albumActionLogDao.logComment(albumId, designerId, userId);
	}
	
	@Override
	public List<CountCacheBean> queryBrowseStat() {
		return albumActionLogDao.queryBrowseStat();
	}

}
