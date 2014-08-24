package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.model.AlbumActionLog;
import com.bruce.designer.model.AlbumActionLogCriteria;
import com.bruce.foundation.dao.IFoundationDao;

public interface IAlbumActionLogDao extends IFoundationDao<AlbumActionLog, Long, AlbumActionLogCriteria> {

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
    
    /**查询浏览数*/
    public List<CountCacheBean> queryBrowseStat();
    
    /*以下是查询热门专辑接口*/
    public List<CountCacheBean> realtimeDailyTopAlbums(int limit);

    public List<CountCacheBean> realtimeWeeklyTopAlbums(int limit);

    public List<CountCacheBean> realtimeMonthlyTopAlbums(int limit);
    
    public List<CountCacheBean> realtimeYearlyTopAlbums(int limit);

    /*以下是查询热门设计师接口*/
    public List<CountCacheBean> realtimeDailyTopDesigners(int limit);

    public List<CountCacheBean> realtimeWeeklyTopDesigners(int limit);

    public List<CountCacheBean> realtimeMonthlyTopDesigners(int limit);
    
    public List<CountCacheBean> realtimeYearlyTopDesigners(int limit);

}
