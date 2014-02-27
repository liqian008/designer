package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.data.CountCacheBean;
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

	/**
	 * 判断用户之前是否做过赞操作
	 * @param albumId
	 * @param userId
	 * @return
	 */
	public boolean existLikeLog(int albumId, int userId);
	/**
	 * 判断用户之前是否做过收藏操作
	 * @param albumId
	 * @param userId
	 * @return
	 */
	public boolean existFavoriteLog(int albumId, int userId);
	
    public int logBrowse(int albumId, int designerId, int userId);

    public int logLike(int albumId, int designerId, int userId, boolean everLiked);

    public int logFavorite(int albumId, int designerId, int userId, boolean everFavorited);

    public int logComment(int albumId, int designerId, int userId);

    public List<CountCacheBean> queryBrowseStat();

    public List<CountCacheBean> realtimeDailyTopAlbums(int limit);

    public List<CountCacheBean> realtimeWeeklyTopAlbums(int limit);

    public List<CountCacheBean> realtimeMonthlyTopAlbums(int limit);

    public List<CountCacheBean> realtimeDailyTopDesigners(int limit);

    public List<CountCacheBean> realtimeWeeklyTopDesigners(int limit);

    public List<CountCacheBean> realtimeMonthlyTopDesigners(int limit);

}
