package com.bruce.designer.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.dao.IAlbumActionLogDao;
import com.bruce.designer.dao.mapper.AlbumActionLogMapper;
import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.model.AlbumActionLog;
import com.bruce.designer.model.AlbumActionLogCriteria;

@Repository
public class AlbumActionLogDaoImpl implements IAlbumActionLogDao, InitializingBean {

	@Autowired
	private AlbumActionLogMapper albumActionLogMapper;

	public int save(AlbumActionLog t) {
		return albumActionLogMapper.insertSelective(t);
	}

	public List<AlbumActionLog> queryAll() {
		return albumActionLogMapper.selectByExample(null);
	}

	public int updateById(AlbumActionLog t) {
		return albumActionLogMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Long id) {
		return albumActionLogMapper.deleteByPrimaryKey(id);
	}

	public AlbumActionLog loadById(Long id) {
		return albumActionLogMapper.selectByPrimaryKey(id);
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
		if(everLiked){
			log.setStatus(ConstService.ACTION_LOG_REPEAT);
		}else{
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
		if(everFavorited){
			log.setStatus(ConstService.ACTION_LOG_REPEAT);
		}else{
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
		// TODO Auto-generated method stub
	}

	@Override
	public List<AlbumActionLog> fallLoadList(Long tailId, int limit) {
		return null;
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
    	//select album_id, sum(browse_num) total_num from tb_album_action_log where status=1 group by album_id;
    	return albumActionLogMapper.queryBrowseStat();
    }

    
//    /**
//	 * 分组查询评论数据，为重建索引提供数据
//	 */
//    @Override
//    public List<CountCacheBean> queryCommentList() { 
//        //select album_id, count(album_id) total_num from tb_comment where status=1 group by album_id;
//        return albumActionLogMapper.queryCommentList();
//    }
    
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
    	criteria.setAlbumBrowseScore(1);
    	criteria.setAlbumLikeScore(1);
    	criteria.setAlbumCommentScore(1);
    	criteria.setAlbumFavoriteScore(1);
    	criteria.setLimit(limit);
    	return criteria;
	}
	
	private AlbumActionLogCriteria initDesignerScoreCriteria(int limit) {
		AlbumActionLogCriteria criteria = new AlbumActionLogCriteria();
    	criteria.setDesignerBrowseScore(1);
    	criteria.setDesignerLikeScore(1);
    	criteria.setDesignerCommentScore(1);
    	criteria.setDesignerFavoriteScore(1);
    	criteria.setLimit(limit);
    	return criteria;
	}
    
    /**
     * 实时每周热门作品
     */
    @Override
    public List<CountCacheBean> realtimeWeeklyTopAlbums(int limit) { 
//        return albumActionLogMapper.realtimeWeeklyTopAlbums(limit);
    	AlbumActionLogCriteria criteria = initAlbumScoreCriteria(limit);
        return albumActionLogMapper.realtimeWeeklyTopAlbums(criteria);
    }
    
    /**
     * 实时每月热门作品
     */
    @Override
    public List<CountCacheBean> realtimeMonthlyTopAlbums(int limit) { 
//        return albumActionLogMapper.realtimeMonthlyTopAlbums(limit);
    	AlbumActionLogCriteria criteria = initAlbumScoreCriteria(limit);
        return albumActionLogMapper.realtimeMonthlyTopAlbums(criteria);
    }
    
    /**
     * 实时每日热门设计师
     */
    @Override
    public List<CountCacheBean> realtimeDailyTopDesigners(int limit) { 
//        return albumActionLogMapper.realtimeDailyTopDesigners(limit);
    	AlbumActionLogCriteria criteria = initDesignerScoreCriteria(limit);
        return albumActionLogMapper.realtimeDailyTopDesigners(criteria);
    }
    
    /**
     * 实时每周热门设计师
     */
    @Override
    public List<CountCacheBean> realtimeWeeklyTopDesigners(int limit) { 
//        return albumActionLogMapper.realtimeWeeklyTopDesigners(limit);
    	AlbumActionLogCriteria criteria = initDesignerScoreCriteria(limit);
        return albumActionLogMapper.realtimeWeeklyTopDesigners(criteria);
    }
    
    /**
     * 实时每月热门设计师
     */
    @Override
    public List<CountCacheBean> realtimeMonthlyTopDesigners(int limit) { 
//        return albumActionLogMapper.realtimeMonthlyTopDesigners(limit);
    	AlbumActionLogCriteria criteria = initDesignerScoreCriteria(limit);
        return albumActionLogMapper.realtimeMonthlyTopDesigners(criteria);
    }
    
	@Override
	public boolean existLikeLog(int albumId, int userId) {
		return false;
	}

	@Override
	public boolean existFavoriteLog(int albumId, int userId) {
		return false;
	}
}
