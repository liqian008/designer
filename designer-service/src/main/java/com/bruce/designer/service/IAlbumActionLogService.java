package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.model.AlbumActionLog;
import com.bruce.designer.model.AlbumActionLogCriteria;
import com.bruce.foundation.service.IFoundationDao;

public interface IAlbumActionLogService extends IFoundationDao<AlbumActionLog, Long, AlbumActionLogCriteria> {

	public int logBrowse(int albumId, int designerId, int userId);

	public int logLike(int albumId, int designerId, int userId);

	public int logFavorite(int albumId, int designerId, int userId);

	public int logComment(int albumId, int designerId, int userId);

	public List<CountCacheBean> queryBrowseStat();

}
