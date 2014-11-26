package com.bruce.designer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.cache.counter.AlbumLikeCache;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.dao.IAlbumLikeDao;
import com.bruce.designer.exception.RedisKeyNotExistException;
import com.bruce.designer.model.AlbumLike;
import com.bruce.designer.model.AlbumLikeCriteria;
import com.bruce.designer.service.IAlbumLikeService;
import com.bruce.designer.service.IMessageService;

@Service
public class AlbumLikeServiceImpl implements IAlbumLikeService {

	private static final int LIKE_CACHE_MAX_COUNT = 5000;

	@Autowired
	private IAlbumLikeDao albumLikeDao;
	@Autowired
	private AlbumLikeCache albumLikeCache;
	@Autowired
	private IMessageService messageService;
	
	private Logger logger = LoggerFactory.getLogger(AlbumFavoriteServiceImpl.class);

	public int save(AlbumLike t) {
		return albumLikeDao.save(t);
	}

	public int updateById(AlbumLike t) {
		return albumLikeDao.updateById(t);
	}

	public int deleteById(Integer id) {
		return albumLikeDao.deleteById(id);
	}

	public AlbumLike loadById(Integer id) {
		return albumLikeDao.loadById(id);
	}

	@Override
	public List<AlbumLike> queryAll() {
		return albumLikeDao.queryAll();
	}

	@Override
	public boolean like(int userId, int albumId, int designerId) {
		// 检测是否like
		boolean isLike = isLike(userId, albumId);
		if (isLike) {
			return false;
		} else {
			if (albumLikeDao.like(userId, albumId) > 0) {
				try {
					albumLikeCache.like(userId, albumId);
				} catch (RedisKeyNotExistException e) {
					if(logger.isErrorEnabled()){
		    			logger.error("like: "+albumId+", "+designerId+", "+userId, e);
		    		}
					
					List<AlbumLike> likeList = getLikeListByAlbumId(albumId);//获取该album的like列表
					if(likeList!=null&&likeList.size()>0){
					    albumLikeCache.setLikeList(albumId, likeList);
					}
				}
				//发送赞消息
				messageService.sendMessage(albumId, userId, designerId, "", ConstService.MESSAGE_TYPE_LIKE);
				return true;
			} else {
				
			}
			return false;
		}
	}
	
	@Override
	public boolean unlike(int userId, int albumId) {
		try {
			// 删除赞
			if (albumLikeDao.deleteLike(userId, albumId) >= 0) {
				//TODO 删除缓存
				albumLikeCache.unlike(userId, albumId);
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public boolean isLike(int userId, int albumId) {
		 boolean isLike = false;
		 try {
			 isLike = albumLikeCache.isLike(userId, albumId);
		 } catch (RedisKeyNotExistException e) {
			 List<AlbumLike> likeList = getLikeListByAlbumId(albumId);
			 if(likeList!=null&&likeList.size()>0){
			     albumLikeCache.setLikeList(albumId, likeList);
			 }
			 if (likeList != null) {
                for (AlbumLike albumLike : likeList) {
                    if (albumLike.getUserId() == userId) {
                    	isLike = true;
                        break;
                    }
                }
            }
		 }
		 return isLike;
	}

	@Override
	public List<AlbumLike> getLikeListByAlbumId(int albumId) {
		List<AlbumLike> likeList = albumLikeDao.getLikeListByAlbumId(albumId, LIKE_CACHE_MAX_COUNT);
		return likeList;
	}

	@Override
	public List<AlbumLike> getLikeListByAlbumId(int userId, int page, int pageSize) {
		List<AlbumLike> likeList = getLikeListByAlbumId(userId);
		if (likeList != null && likeList.size() > 0) {
			int size = likeList.size();
			int fromIndex = page * pageSize;
			int toIndex = (page + 1) * pageSize;
			if (size > fromIndex) {
				toIndex = toIndex > size ? size : toIndex;
				return likeList.subList(fromIndex, toIndex);
			}
		}
		return new ArrayList<AlbumLike>();
	}

	@Override
	public long getLikeCountByAlbumId(int albumId) { 
		try {
			return albumLikeCache.getLikeCount(albumId);
		} catch (RedisKeyNotExistException e) {
			//DB加载数据，重建cache
			List<AlbumLike> likeList = getLikeListByAlbumId(albumId);
			if(likeList!=null&&likeList.size()>0){
			    albumLikeCache.setLikeList(albumId, likeList);
			}
            return likeList.size();
		}
	}

	
	
	
	@Override
	public int updateByCriteria(AlbumLike t, AlbumLikeCriteria criteria) {
		return albumLikeDao.updateByCriteria(t, criteria);
	}

	@Override
	public int deleteByCriteria(AlbumLikeCriteria criteria) {
		return albumLikeDao.deleteByCriteria(criteria);
	}

	@Override
	public List<AlbumLike> queryAll(String orderByClause) {
		return albumLikeDao.queryAll(orderByClause);
	}

	@Override
	public List<AlbumLike> queryByCriteria(AlbumLikeCriteria criteria) {
		return albumLikeDao.queryByCriteria(criteria);
	}
	
	@Override
	public int countByCriteria(AlbumLikeCriteria criteria) {
		return albumLikeDao.countByCriteria(criteria);
	}
}
