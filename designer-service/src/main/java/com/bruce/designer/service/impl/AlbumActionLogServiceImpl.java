package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.dao.IAlbumActionLogDao;
import com.bruce.designer.data.CountCacheBean;
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

	/**
	 * 浏览记录
	 */
	@Override
	public int logBrowse(int albumId, int designerId, int userId) {
		return albumActionLogDao.logBrowse(albumId, designerId, userId);
	}

	/**
	 * 赞记录
	 */
	@Override
	public int logLike(int albumId, int designerId, int userId) {
	    //如果like过，记做重复操作
		boolean everLiked = false;
		return albumActionLogDao.logLike(albumId, designerId, userId, everLiked);
	}

	/**
	 * 收藏记录
	 */
	@Override
	public int logFavorite(int albumId, int designerId, int userId) {
	    //如果favorite过，记做重复操作
		boolean everFavorited = false;
		return albumActionLogDao.logFavorite(albumId, designerId, userId, everFavorited);
	}

	/**
	 * 评论记录
	 */
	@Override
	public int logComment(int albumId, int designerId, int userId) {
		return albumActionLogDao.logComment(albumId, designerId, userId);
	}
	
	@Override
    public List<CountCacheBean> queryBrowseList() {
        return albumActionLogDao.queryBrowseList();
    }
	
	@Override
    public List<CountCacheBean> queryCommentList() { 
        return albumActionLogDao.queryCommentList(); 
    }
	
//	@Override
//    public List<CountCacheBean> queryLikeByAlbumId(int albumId) {
//        return albumActionLogDao.queryLikeByAlbumId(albumId);
//    }
//	
//	@Override
//    public List<CountCacheBean> queryFavoriteByAlbumId(int albumId) {
//        return albumActionLogDao.queryFavoriteByAlbumId(albumId);
//    }

}
