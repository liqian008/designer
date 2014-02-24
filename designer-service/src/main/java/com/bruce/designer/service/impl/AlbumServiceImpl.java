package com.bruce.designer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.dao.IAlbumDao;
import com.bruce.designer.data.PagingData;
import com.bruce.designer.exception.DesignerException;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.AlbumFavorite;
import com.bruce.designer.model.UserFollow;
import com.bruce.designer.service.IAlbumFavoriteService;
import com.bruce.designer.service.IAlbumLikeService;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.ICounterService;
import com.bruce.designer.service.ITagAlbumService;
import com.bruce.designer.service.ITagService;
import com.bruce.designer.service.IUserGraphService;

@Service
public class AlbumServiceImpl implements IAlbumService {

	@Autowired
	private IAlbumDao albumDao;
	@Autowired
	private ICounterService counterService;
	@Autowired
	private IAlbumLikeService albumLikeService;
	@Autowired
	private IAlbumFavoriteService albumFavoriteService;
//	@Autowired
//	private ICounterService counterService;
	
	@Autowired
	private IUserGraphService userGraphService;
	@Autowired
	private ITagService tagService;
	@Autowired
	private ITagAlbumService tagAlbumService;

	public int save(Album t) {
		return albumDao.save(t);
	}

	public int updateById(Album t) {
		return albumDao.updateById(t);
	}

	public int deleteById(Integer id) {
		return albumDao.deleteById(id);
	}

	public Album loadById(Integer id) {
		return loadById(id, false, false); 
	}
	
	public Album loadById(Integer id, boolean loadCount, boolean loadTags) {
		Album album = albumDao.loadById(id);
		if(album!=null){
			checkAlbumStatus(album);//检查封禁状态
			if(loadCount){
				//加载作品访问量
				initAlbumWithCount(album);
			}
			if(loadTags){
				//加载tag标签
				initAlbumWithTags(album);
			}
		}
		return album;
	}
	
	public List<Album> queryAll() {
		return albumDao.queryAll();
	}
	
	/**
	 * 返回根据idList排序的List<Album>
	 */
	@Override
	public List<Album> queryAlbumByIds(List<Integer> idList) {
		if(idList!=null&&idList.size()>0){
			List<Album> albumList = albumDao.queryAlbumByIds(idList);
			if(albumList!=null&&albumList.size()>0){
				//按idList排序
				List<Album> sortedAlbumList = new ArrayList<Album>();
				for(int albumId: idList){
					for(Album album: albumList){
						if(albumId == album.getId()){
							sortedAlbumList.add(album);
							break;
						}
					}
				}
				return sortedAlbumList;
			}
		}
		return null;
	}

	public int deleteUserAlbum(int userId, int albumId) {
		int result = albumDao.deleteUserAlbum(userId, albumId);
		if(result>0){
			//删除tag关联
			tagAlbumService.deleteByAlbumId(albumId);
			// TODO 删除相关计数(作品计数)，可能影响热门排行
			counterService.removeAlbum(userId, albumId);
			
			return result;
		}
		return 0;
	}

	

	/**
	 * 分页查询作品集
	 */
	@Override
	public PagingData<Album> pagingQuery(int userId, short albumStatus, int pageNo, int pageSize) {
		pageNo = pageNo < 1 ? 1 : pageNo;
//		List<Album> albumList = albumDao.queryList((pageNo - 1) * pageSize, pageSize);
//		PagingData<Album> pagingData = new PagingData<Album>(albumList, totalCount, pageNo, pageSize);
		PagingData<Album> pagingData = albumDao.pagingQuery(userId, albumStatus, pageNo, pageSize);
		if(pagingData!=null){
			List<Album> albumList = pagingData.getPagingData();
			initAlbumsWithCount(albumList);
			initAlbumsWithTags(albumList);
		}
		return pagingData;
	}
	
	@Override
	public List<Album> fallLoadAlbums(int albumsTailId, int limit, boolean isLoadCount, boolean isLoadTags) {
		List<Album> albumList = albumDao.fallLoadList(albumsTailId, limit);
		//加载访问计数
		if(isLoadCount){
			initAlbumsWithCount(albumList);
		}
		//加载作品tags
		if (isLoadTags) {
			initAlbumsWithTags(albumList);
		}
		return albumList;
	}

	@Override
	public List<Album> fallLoadDesignerAlbums(int designerId, int albumsTailId, int limit, boolean isLoadCount, boolean isLoadTags) {
		List<Integer> designerIdList = new ArrayList<Integer>();
		designerIdList.add(designerId);
		List<Album> albumList = albumDao.fallLoadDesignerAlbums(designerIdList, albumsTailId, limit);
		//加载访问计数
		if(isLoadCount){
			initAlbumsWithCount(albumList);
		}
		//加载作品tags
		if (isLoadTags) {
			initAlbumsWithTags(albumList);
		}
		return albumList;
	}

	/**
	 * 关注视图的作品列表
	 * 
	 * @param userId
	 * @param albumsTailId
	 * @param limit
	 * @return
	 */
	@Override
	public List<Album> fallLoadUserFollowAlbums(int userId, int albumsTailId, int limit) {
		//获取关注id列表
		List<UserFollow> followList = userGraphService.getFollowList(userId);
		if (followList != null && followList.size() > 0) {
			List<Integer> designerIdList = new ArrayList<Integer>();
			for (UserFollow userFollow : followList) {
				designerIdList.add(userFollow.getFollowId());
			}
			//根据关注id列表瀑布流加载专辑
			List<Album> albumList = albumDao.fallLoadDesignerAlbums(designerIdList, albumsTailId, limit);
			initAlbumsWithCount(albumList);
			initAlbumsWithTags(albumList);
			return albumList;
		}
		return null;
	}
	
	/**
	 * 我的收藏
	 */
	@Override
	public List<Album> fallLoadUserFavoriteAlbums(int userId, int favoriteTailId, int limit) {
//		List<AlbumFavorite> favoriteList = albumFavoriteService.getFavoriteListByAlbumId(userId);
		List<AlbumFavorite> favoriteList = albumFavoriteService.getFavoriteListByUserId(userId, favoriteTailId, limit);
		if (favoriteList != null && favoriteList.size() > 0) {
			List<Integer> albumIdList = new ArrayList<Integer>();
			for (AlbumFavorite albumFavorite : favoriteList) {
				albumIdList.add(albumFavorite.getAlbumId());
			}
			//根据albumId列表加载详情
			List<Album> albumList = queryAlbumByIds(albumIdList); //albumDao.fallLoadDesignerAlbums(albumIdList, albumsTailId, limit);
			initAlbumsWithCount(albumList);
			initAlbumsWithTags(albumList);
			return albumList;
		}
		return null;
	}
	
	/**
	 * 获取推荐专辑列表
	 */
	public List<Album> queryRecommendAlbums(int limit) {
		List<Integer> albumIdList = null;
		List<Album> albumList = queryAlbumByIds(albumIdList);
		return albumList;
	}
	
	/**
	 * 瀑布流方式加载标签作品
	 */
	@Override
	// TODO 修改接口，去掉tagName，改为tagId
	public List<Album> fallLoadAlbumsByTagName(String tagName, int albumsTailId, int limit) {
		int tagId = tagService.getTagIdByName(tagName, false);
		if(tagId>0){
			List<Album> albumList = null;
			// 根据tagname获取tagId，瀑布流方式加载
			List<Integer> albumIdList = tagAlbumService.fallLoadAlbumIdList(tagId, albumsTailId, limit); 
			if (albumIdList != null && albumIdList.size() > 0) {
				albumList = queryAlbumByIds(albumIdList);
				initAlbumsWithTags(albumList);
			}
			return albumList;
		}
		return null;
	}

//	public List<Album> queryAlbumByStatus(short status) {
//		return albumDao.queryAlbumByStatus(status);
//	}

	public List<Album> queryAlbumByUserId(int userId) {
		return albumDao.queryAlbumByUserId(userId);
	}

	/**
	 * 加载专辑的tag标签
	 * 
	 * @param album
	 */
	@Override
	public void initAlbumsWithTags(List<Album> albumList) {
		if (albumList != null && albumList.size() > 0) {
			for (Album album : albumList) {
				initAlbumWithTags(album);
			}
		}
	}

	/**
	 * 加载专辑的tag标签
	 * 
	 * @param album
	 */
	@Override
	public void initAlbumWithTags(Album album) {
		if (album != null) {
			// 获取tag
			album.setTagList(tagService.getTagNamesByAlbumId(album.getId()));
		}
	}
	
	
	/**
	 * 批量初始化专辑计数
	 * 
	 * @param albumList
	 */
	@Override
	public void initAlbumsWithCount(List<Album> albumList) {
		if(albumList!=null&&albumList.size()>0){
			for (Album loopAlbum : albumList) {
				initAlbumWithCount(loopAlbum);
			}
		}
	}

	/**
	 * 初始化专辑计数
	 * 
	 * @param album
	 */
	@Override
	public void initAlbumWithCount(Album album) {
		if (album != null) {
			int albumId = album.getId();
			album.setBrowseCount(counterService.getBrowseCount(albumId));
			album.setCommentCount(counterService.getCommentCount(albumId));
//			album.setLikeCount(counterService.getLikeCount(albumId));
//			album.setFavoriteCount(counterService.getFavoriteCount(albumId));
			album.setLikeCount(albumLikeService.getLikeCountByAlbumId(albumId));
			album.setFavoriteCount(albumFavoriteService.getFavoriteCountByAlbumId(albumId));
		}
	}
	
	/**
	 * 初始化专辑交互状态（是否赞，是否收藏）
	 * @param album
	 * @param userId
	 */
	@Override
	public void initAlbumInteractionStatus(Album album, int userId) {
		if (album != null) {
			if(userId>0){
				//初始化专辑交互状态（是否赞，是否收藏）
				boolean isLike = albumLikeService.isLike(userId, album.getId());
				album.setLike(isLike);
				boolean isFavorite = albumFavoriteService.isFavorite(userId, album.getId());
				album.setFavorite(isFavorite);
			}
		}
	}
	
	
//	/**
//	 * 初始化专辑交互状态（是否赞，是否收藏）
//	 * 
//	 * @param album
//	 */
//	@Override
//	public void initAlbumStatus(int userId, Album album) {
//		
//	}
	
	private void checkAlbumStatus(Album album){
		if(album.getStatus()==ConstService.ALBUM_DELETE_STATUS){
	    	throw new DesignerException(ErrorCode.ALBUM_FORBIDDEN);
	    }
	}

}
