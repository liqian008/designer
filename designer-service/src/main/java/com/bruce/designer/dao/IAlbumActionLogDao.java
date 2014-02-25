package com.bruce.designer.dao;


import com.bruce.designer.model.AlbumActionLog;

public interface IAlbumActionLogDao extends IBaseDao<AlbumActionLog, Long> {

	// public List<AlbumActionLog> getLikeListByAlbumId(int albumId, int
	// maxCount);
	//
	// public int deleteLike(int userId, int albumId);
	//
	// public int like(int userId, int albumId);
	//
	// public boolean isLike(int userId, int albumId);

	public int logBrowse(int albumId, int designerId, int userId);

	public int logLike(int albumId, int designerId, int userId);

	public int logFavorite(int albumId, int designerId, int userId);

	public int logComment(int albumId, int designerId, int userId);

}
