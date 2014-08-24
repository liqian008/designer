package com.bruce.designer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.cache.counter.AlbumFavoriteCache;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.dao.IAlbumFavoriteDao;
import com.bruce.designer.exception.RedisKeyNotExistException;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.AlbumFavorite;
import com.bruce.designer.model.AlbumFavorite;
import com.bruce.designer.model.AlbumFavoriteCriteria;
import com.bruce.designer.service.IAlbumFavoriteService;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.IMessageService;

@Service
public class AlbumFavoriteServiceImpl implements IAlbumFavoriteService {

	private static final int FAVORITE_CACHE_MAX_COUNT = 5000;

	@Autowired
	private IAlbumFavoriteDao albumFavoriteDao;
	@Autowired
	private IAlbumService albumService;
	@Autowired
	private AlbumFavoriteCache albumFavoriteCache;
	@Autowired
	private IMessageService messageService;
	
	private Logger logger = LoggerFactory.getLogger(AlbumFavoriteServiceImpl.class);

	public int save(AlbumFavorite t) {
		return albumFavoriteDao.save(t);
	}

	public int updateById(AlbumFavorite t) {
		return albumFavoriteDao.updateById(t);
	}

	public int deleteById(Integer id) {
		return albumFavoriteDao.deleteById(id);
	}

	public AlbumFavorite loadById(Integer id) {
		return albumFavoriteDao.loadById(id);
	}

	@Override
	public List<AlbumFavorite> queryAll() {
		return albumFavoriteDao.queryAll();
	}

	@Override
	public boolean favorite(int userId, int albumId, int designerId) {
		// 检测是否favorite
		boolean isFavorite = isFavorite(userId, albumId);
		if (isFavorite) {
			return false;
		} else {
			if (albumFavoriteDao.favorite(userId, albumId) > 0) {
				try {
					albumFavoriteCache.favorite(userId, albumId);
				} catch (RedisKeyNotExistException e) {
					if (logger.isErrorEnabled()) {
						logger.error("favorite: " + albumId + ", " + designerId + ", " + userId, e);
					}
					
					//TODO 
					List<AlbumFavorite> favoriteList = getFavoriteListByAlbumId(albumId);////获取该album的like列表
					if(favoriteList!=null&&favoriteList.size()>0){
					    albumFavoriteCache.setFavoriteList(albumId, favoriteList);
					}
				}
				//发送赞消息
				messageService.sendMessage(albumId, userId, designerId, "", ConstService.MESSAGE_TYPE_FAVORITIES);
				return true;
			} else {
				
			}
			return false;
		}
	}
	
	@Override
	public boolean unfavorite(int userId, int albumId) {
		try {
			// 删除关注
			if (albumFavoriteDao.deleteFavorite(userId, albumId) >= 0) {
				//TODO 删除缓存
				albumFavoriteCache.unfavorite(userId, albumId);
				return true;
			}
		} catch (Exception e) {
			if(logger.isErrorEnabled()){
    			logger.error("unfavorite: "+albumId+", "+userId, e);
    		}
		}
		return false;
	}

	@Override
	public boolean isFavorite(int userId, int albumId) {
		 boolean isFavorite = false;
		 try {
			isFavorite = albumFavoriteCache.isFavorite(userId, albumId);
		 } catch (RedisKeyNotExistException e) {
			if(logger.isErrorEnabled()){
    			logger.error("isFavorite: "+albumId+", "+userId, e);
    		}
			List<AlbumFavorite> favoriteList = getFavoriteListByAlbumId(albumId);
			if(favoriteList!=null&&favoriteList.size()>0){
			    albumFavoriteCache.setFavoriteList(albumId, favoriteList);
			}
            if (favoriteList != null) {
                for (AlbumFavorite albumFavorite : favoriteList) {
                    if (albumFavorite.getUserId() == userId) {
                    	isFavorite = true;
                        break;
                    }
                }
            }
		 }
		 return isFavorite;
	}

	@Override
	public List<AlbumFavorite> getFavoriteListByAlbumId(int albumId) {
		List<AlbumFavorite> favoriteList = albumFavoriteDao.getFavoriteListByAlbumId(albumId, FAVORITE_CACHE_MAX_COUNT);
		return favoriteList;
	}
	
	/**
	 * 我的收藏
	 */
	@Override
	public List<AlbumFavorite> getFavoriteListByUserId(int userId, int favoriteTailId, int limit) {
		List<AlbumFavorite> favoriteList = albumFavoriteDao.getFavoriteListByUserId(userId, favoriteTailId, limit);
		return favoriteList;
	}
	
	
	/**
	 * 我的收藏数据（包含album的详细数据）
	 */
	@Override
	public List<AlbumFavorite> fallLoadUserFavoriteAlbums(int userId, int favoriteTailId, int limit) {
		List<AlbumFavorite> favoriteList = getFavoriteListByUserId(userId, favoriteTailId, limit);
		if (favoriteList != null && favoriteList.size() > 0) {
			List<Integer> albumIdList = new ArrayList<Integer>();
			for (AlbumFavorite albumFavorite : favoriteList) {
				albumIdList.add(albumFavorite.getAlbumId());
			}
			//根据albumId列表加载详情
			List<Album> albumList = albumService.queryAlbumByIds(albumIdList); //albumDao.fallLoadDesignerAlbums(albumIdList, albumsTailId, limit);
			
			if(albumList!=null&&albumList.size()>0){
				albumService.initAlbumsWithCount(albumList);
				albumService.initAlbumsWithTags(albumList);
				
				//将album写入favorite
				for(Album album: albumList){
					for(AlbumFavorite favorite: favoriteList){
						if(favorite.getAlbumId().equals(album.getId())){
							favorite.setAlbum(album);
							continue;
						}
					}
				}
			}
			return favoriteList;
		}
		return null;
	}
	


//	@Override
//	public List<AlbumFavorite> getFavoriteListByAlbumId(int albumId, int page, int pageSize) {
//		List<AlbumFavorite> favoriteList = getFavoriteListByAlbumId(albumId);
//		if (favoriteList != null && favoriteList.size() > 0) {
//			int size = favoriteList.size();
//			int fromIndex = page * pageSize;
//			int toIndex = (page + 1) * pageSize;
//			if (size > fromIndex) {
//				toIndex = toIndex > size ? size : toIndex;
//				return favoriteList.subList(fromIndex, toIndex);
//			}
//		}
//		return new ArrayList<AlbumFavorite>();
//	}

	@Override
	public long getFavoriteCountByAlbumId(int albumId) { 
		try {
			return albumFavoriteCache.getFavoriteCount(albumId);
		} catch (RedisKeyNotExistException e) {
			if(logger.isErrorEnabled()){
    			logger.error("getFavoriteCountByAlbumId: "+albumId, e);
    		}
			
			//DB加载数据，重建cache
			List<AlbumFavorite> favoriteList = getFavoriteListByAlbumId(albumId);
			if(favoriteList!=null&&favoriteList.size()>0){
			    albumFavoriteCache.setFavoriteList(albumId, favoriteList);
			}
            return favoriteList.size();
		}
	}
	
//	/**
//	 * 分页查询作品集
//	 */
//	public PagingData<Album> pagingQuery(int userId, short albumStatus, int pageNo, int pageSize) {
//		pageNo = pageNo < 1 ? 1 : pageNo;
//		PagingData<Album> pagingData = albumFavoriteDao.pagingQuery(userId, albumStatus, pageNo, pageSize);
//		if(pagingData!=null){
//			List<Album> albumList = pagingData.getPagingData();
////			initAlbumsWithCount(albumList);
////			initAlbumsWithTags(albumList);
//		}
//		return pagingData;
//		return null;
//	}

	
	
	
	
	
	
	
	
	

	
	@Override
	public int updateByCriteria(AlbumFavorite t, AlbumFavoriteCriteria criteria) {
		return albumFavoriteDao.updateByCriteria(t, criteria);
	}

	@Override
	public int deleteByCriteria(AlbumFavoriteCriteria criteria) {
		return albumFavoriteDao.deleteByCriteria(criteria);
	}

	@Override
	public List<AlbumFavorite> queryAll(String orderByClause) {
		return albumFavoriteDao.queryAll(orderByClause);
	}

	@Override
	public List<AlbumFavorite> queryByCriteria(AlbumFavoriteCriteria criteria) {
		return albumFavoriteDao.queryByCriteria(criteria);
	}
	
	
}
