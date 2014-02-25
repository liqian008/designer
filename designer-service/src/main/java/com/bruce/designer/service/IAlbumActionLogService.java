package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.model.AlbumActionLog;

public interface IAlbumActionLogService extends
        IBaseService<AlbumActionLog, Long> {

    public int logBrowse(int albumId, int designerId, int userId);

    public int logLike(int albumId, int designerId, int userId);

    public int logFavorite(int albumId, int designerId, int userId);

    public int logComment(int albumId, int designerId, int userId);

    public List<CountCacheBean> queryBrowseByAlbumId(int albumId);

    public List<CountCacheBean> queryLikeByAlbumId(int albumId);

    public List<CountCacheBean> queryFavoriteByAlbumId(int albumId);

    public List<CountCacheBean> queryCommentByAlbumId(int albumId);

}
