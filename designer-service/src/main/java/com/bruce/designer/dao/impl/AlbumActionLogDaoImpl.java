package com.bruce.designer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.dao.IAlbumActionLogDao;
import com.bruce.designer.dao.mapper.AlbumActionLogMapper;
import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.model.AlbumActionLog;

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
		return log;
	}

	private AlbumActionLog initBlankLog(int albumId, int designerId) {
		return initBlankLog(albumId, designerId, 0);
	}

	private AlbumActionLog initBlankLog(int albumId) {
		return initBlankLog(albumId, 0);
	}

    @Override
    public List<CountCacheBean> queryBrowseList() {
    	//select album_id, sum(browse_num) total_num from tb_album_action_log where status=1 group by album_id;
    	return albumActionLogMapper.queryBrowseList();
    }


    @Override
    public List<CountCacheBean> queryCommentList() { 
    	//select album_id, sum(comment_num) total_num from tb_album_action_log where status=1 group by album_id;
    	return null;
    }
    
    @Override
    public List<CountCacheBean> realtimeDailyTopAlbums(int limit) { 
        return albumActionLogMapper.realtimeDailyTopAlbums(limit);
    }
    @Override
    public List<CountCacheBean> realtimeWeeklyTopAlbums(int limit) { 
        return albumActionLogMapper.realtimeWeeklyTopAlbums(limit);
    }
    
    @Override
    public List<CountCacheBean> realtimeMonthlyTopAlbums(int limit) { 
        return albumActionLogMapper.realtimeMonthlyTopAlbums(limit);
    }
    
    @Override
    public List<CountCacheBean> realtimeDailyTopDesigners(int limit) { 
        return albumActionLogMapper.realtimeDailyTopDesigners(limit);
    }
    
    @Override
    public List<CountCacheBean> realtimeWeeklyTopDesigners(int limit) { 
        return albumActionLogMapper.realtimeWeeklyTopDesigners(limit);
    }
    
    @Override
    public List<CountCacheBean> realtimeMonthlyTopDesigners(int limit) { 
        return albumActionLogMapper.realtimeMonthlyTopDesigners(limit);
    }

//  @Override
//  public List<CountCacheBean> queryLikeByAlbumId(int albumId) {
//      return null;
//  }
//
//  @Override
//  public List<CountCacheBean> queryFavoriteByAlbumId(int albumId) {
//      return null;
//  }
}
