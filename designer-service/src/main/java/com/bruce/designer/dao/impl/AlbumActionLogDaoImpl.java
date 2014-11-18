package com.bruce.designer.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.dao.IAlbumActionLogDao;
import com.bruce.designer.dao.mapper.AlbumActionLogMapper;
import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.model.AlbumActionLog;
import com.bruce.designer.model.AlbumActionLogCriteria;
import com.bruce.designer.util.ConfigUtil;

@Repository
public class AlbumActionLogDaoImpl implements IAlbumActionLogDao, InitializingBean {

	/* album收藏积分 */
	public static final int ALBUM_FAVORITE_SCORE = NumberUtils.toInt(ConfigUtil.getString("album_favorite_score"), 100);
	/* album赞积分 */
	public static final int ALBUM_LIKE_SCORE = NumberUtils.toInt(ConfigUtil.getString("album_like_score"), 5);
	/* album浏览积分 */
	public static final int ALBUM_BROWSE_SCORE = NumberUtils.toInt(ConfigUtil.getString("album_browse_score"), 0);
	/* album评论积分 */
	public static final int ALBUM_COMMENT_SCORE = NumberUtils.toInt(ConfigUtil.getString("album_comment_score"), 2);

	/* designer收藏积分 */
	public static final int DESIGNER_FAVORITE_SCORE = NumberUtils.toInt(ConfigUtil.getString("designer_favorite_score"), 100);
	/* designer赞积分 */
	public static final int DESIGNER_LIKE_SCORE = NumberUtils.toInt(ConfigUtil.getString("designer_like_score"), 5);
	/* designer浏览积分 */
	public static final int DESIGNER_BROWSE_SCORE = NumberUtils.toInt(ConfigUtil.getString("designer_browse_score"), 0);
	/* designer评论积分 */
	public static final int DESIGNER_COMMENT_SCORE = NumberUtils.toInt(ConfigUtil.getString("designer_comment_score"), 2);

	@Autowired
	private AlbumActionLogMapper albumActionLogMapper;

	public int save(AlbumActionLog t) {
		return albumActionLogMapper.insertSelective(t);
	}

	public List<AlbumActionLog> queryAll() {
		return albumActionLogMapper.selectByExample(null);
	}

	@Override
	public List<AlbumActionLog> queryAll(String orderByClause) {
		AlbumActionLogCriteria criteria = new AlbumActionLogCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<AlbumActionLog> queryByCriteria(AlbumActionLogCriteria criteria) {
		return albumActionLogMapper.selectByExample(criteria);
	}

	public int updateById(AlbumActionLog t) {
		return albumActionLogMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(AlbumActionLog t, AlbumActionLogCriteria criteria) {
		return albumActionLogMapper.updateByExampleSelective(t, criteria);
	}

	public int deleteById(Long id) {
		return albumActionLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(AlbumActionLogCriteria criteria) {
		return albumActionLogMapper.deleteByExample(criteria);
	}

	public AlbumActionLog loadById(Long id) {
		return albumActionLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean existLikeLog(int albumId, int userId) {
		AlbumActionLogCriteria criteria = new AlbumActionLogCriteria();
		criteria.createCriteria().andAlbumIdEqualTo(albumId).andUserIdEqualTo(userId).andLikeNumEqualTo((short) 1);
		return albumActionLogMapper.countByExample(criteria) > 0;
	}

	@Override
	public boolean existFavoriteLog(int albumId, int userId) {
		AlbumActionLogCriteria criteria = new AlbumActionLogCriteria();
		criteria.createCriteria().andAlbumIdEqualTo(albumId).andUserIdEqualTo(userId).andFavoriteNumEqualTo((short) 1);
		return albumActionLogMapper.countByExample(criteria) > 0;
	}

	/**
	 * 增加浏览
	 */
	@Override
	public int logBrowse(int albumId, int designerId, int userId) {
		AlbumActionLog log = initBlankLog(albumId, designerId, userId);
		log.setBrowseNum((short) 1);
		return save(log);
	}

	/**
	 * 增加赞
	 */
	@Override
	public int logLike(int albumId, int designerId, int userId, boolean everLiked) {
		AlbumActionLog log = initBlankLog(albumId, designerId, userId);
		log.setLikeNum((short) 1);
		if (everLiked) {
			log.setStatus(ConstService.ACTION_LOG_REPEAT);
		} else {
			log.setStatus(ConstService.ACTION_LOG_NORMAL);
		}
		return save(log);
	}

	/**
	 * 增加收藏
	 */
	@Override
	public int logFavorite(int albumId, int designerId, int userId, boolean everFavorited) {
		AlbumActionLog log = initBlankLog(albumId, designerId, userId);
		log.setFavoriteNum((short) 1);
		if (everFavorited) {
			log.setStatus(ConstService.ACTION_LOG_REPEAT);
		} else {
			log.setStatus(ConstService.ACTION_LOG_NORMAL);
		}
		return save(log);
	}

	/**
	 * 增加评论
	 */
	@Override
	public int logComment(int albumId, int designerId, int userId) {
		AlbumActionLog log = initBlankLog(albumId, designerId, userId);
		log.setCommentNum((short) 1);
		return save(log);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
	}

	/**
	 * 初始化log
	 * 
	 * @param albumId
	 * @param designerId
	 * @param userId
	 * @return
	 */
	private AlbumActionLog initBlankLog(int albumId, int designerId, int userId) {
		AlbumActionLog log = new AlbumActionLog();
		log.setAlbumId(albumId);
		log.setDesignerId(designerId);
		log.setUserId(userId);
		log.setCreateTime(new Date());
		log.setUpdateTime(new Date());
		log.setUserId(userId);

		return log;
	}

	private AlbumActionLog initBlankLog(int albumId, int designerId) {
		return initBlankLog(albumId, designerId, 0);
	}

	private AlbumActionLog initBlankLog(int albumId) {
		return initBlankLog(albumId, 0);
	}

	/**
	 * 分组查询浏览数据，为重建索引提供数据
	 */
	@Override
	public List<CountCacheBean> queryBrowseStat() {
		// select album_id, sum(browse_num) total_num from tb_album_action_log
		// where status=1 group by album_id;
		return albumActionLogMapper.queryBrowseStat();
	}

	/**
	 * 实时每日热门作品
	 */
	@Override
	public List<CountCacheBean> realtimeDailyTopAlbums(int limit) {
		AlbumActionLogCriteria criteria = initAlbumScoreCriteria(limit);
		return albumActionLogMapper.realtimeDailyTopAlbums(criteria);
	}

	private AlbumActionLogCriteria initAlbumScoreCriteria(int limit) {
		AlbumActionLogCriteria criteria = new AlbumActionLogCriteria();
		criteria.setAlbumBrowseScore(ALBUM_BROWSE_SCORE);
		criteria.setAlbumLikeScore(ALBUM_LIKE_SCORE);
		criteria.setAlbumCommentScore(ALBUM_COMMENT_SCORE);
		criteria.setAlbumFavoriteScore(ALBUM_FAVORITE_SCORE);
		criteria.setLimitRows(limit);
		return criteria;
	}

	private AlbumActionLogCriteria initDesignerScoreCriteria(int limit) {
		AlbumActionLogCriteria criteria = new AlbumActionLogCriteria();
		criteria.setDesignerBrowseScore(DESIGNER_BROWSE_SCORE);
		criteria.setDesignerLikeScore(DESIGNER_LIKE_SCORE);
		criteria.setDesignerCommentScore(DESIGNER_COMMENT_SCORE);
		criteria.setDesignerFavoriteScore(DESIGNER_FAVORITE_SCORE);
		criteria.setLimitRows(limit);
		return criteria;
	}

	/**
	 * 实时每周热门作品
	 */
	@Override
	public List<CountCacheBean> realtimeWeeklyTopAlbums(int limit) {
		AlbumActionLogCriteria criteria = initAlbumScoreCriteria(limit);
		return albumActionLogMapper.realtimeWeeklyTopAlbums(criteria);
	}

	/**
	 * 实时每月热门作品
	 */
	@Override
	public List<CountCacheBean> realtimeMonthlyTopAlbums(int limit) {
		AlbumActionLogCriteria criteria = initAlbumScoreCriteria(limit);
		return albumActionLogMapper.realtimeMonthlyTopAlbums(criteria);
	}

	/**
	 * 实时年度热门作品
	 */
	@Override
	public List<CountCacheBean> realtimeYearlyTopAlbums(int limit) {
		AlbumActionLogCriteria criteria = initAlbumScoreCriteria(limit);
		return albumActionLogMapper.realtimeYearlyTopAlbums(criteria);
	}

	/**
	 * 实时每日热门设计师
	 */
	@Override
	public List<CountCacheBean> realtimeDailyTopDesigners(int limit) {
		AlbumActionLogCriteria criteria = initDesignerScoreCriteria(limit);
		return albumActionLogMapper.realtimeDailyTopDesigners(criteria);
	}

	/**
	 * 实时每周热门设计师
	 */
	@Override
	public List<CountCacheBean> realtimeWeeklyTopDesigners(int limit) {
		AlbumActionLogCriteria criteria = initDesignerScoreCriteria(limit);
		return albumActionLogMapper.realtimeWeeklyTopDesigners(criteria);
	}

	/**
	 * 实时每月热门设计师
	 */
	@Override
	public List<CountCacheBean> realtimeMonthlyTopDesigners(int limit) {
		AlbumActionLogCriteria criteria = initDesignerScoreCriteria(limit);
		return albumActionLogMapper.realtimeMonthlyTopDesigners(criteria);
	}

	/**
	 * 实时年度热门设计师
	 */
	@Override
	public List<CountCacheBean> realtimeYearlyTopDesigners(int limit) {
		AlbumActionLogCriteria criteria = initDesignerScoreCriteria(limit);
		return albumActionLogMapper.realtimeYearlyTopDesigners(criteria);
	}

}
