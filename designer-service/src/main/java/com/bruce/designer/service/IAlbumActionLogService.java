package com.bruce.designer.service;

import com.bruce.designer.model.AlbumActionLog;

public interface IAlbumActionLogService extends IBaseService<AlbumActionLog, Long> {

	public int logBrowse(int albumId, int designerId, int userId);

	public int logLike(int albumId, int designerId, int userId);

	public int logFavorite(int albumId, int designerId, int userId);

	public int logComment(int albumId, int designerId, int userId);

}
