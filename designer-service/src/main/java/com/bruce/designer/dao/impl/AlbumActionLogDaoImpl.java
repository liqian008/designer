package com.bruce.designer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.dao.IAlbumActionLogDao;
import com.bruce.designer.dao.mapper.AlbumActionLogMapper;
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
	public int logLike(int albumId, int designerId, int userId) {
		AlbumActionLog log = initBlankLog(albumId, designerId, userId);
		log.setBrowseNum((short) 1);
		return save(log);
	}

	/**
	 * 增加收藏
	 */
	@Override
	public int logFavorite(int albumId, int designerId, int userId) {
		AlbumActionLog log = initBlankLog(albumId, designerId, userId);
		log.setFavoriteNum((short) 1);
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

}
