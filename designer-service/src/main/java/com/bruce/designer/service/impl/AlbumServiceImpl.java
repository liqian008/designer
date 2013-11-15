package com.bruce.designer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.constants.ConstRedis;
import com.bruce.designer.dao.IAlbumDao;
import com.bruce.designer.data.PagingData;
import com.bruce.designer.exception.DesignerException;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.UserFollow;
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
		Album album = albumDao.loadById(id);
		initAlbumWithTags(album);
		return album;
	}

	@Override
	public List<Album> queryAlbumByIds(List<Integer> idList) {
		List<Album> albumList = albumDao.queryAlbumByIds(idList);
		return albumList;
	}

	public int deleteUserAlbum(int userId, int albumId) {
		return albumDao.deleteUserAlbum(userId, albumId);
	}

	public List<Album> queryAll() {
		return albumDao.queryAll();
	}

	public PagingData<Album> pagingQuery(short status, int pageNo, int pageSize) {
		pageNo = pageNo < 1 ? 1 : pageNo;
		List<Album> albumList = albumDao.queryList((pageNo - 1) * pageSize, pageSize);
		int totalCount = -1;//
		PagingData<Album> pagingData = new PagingData<Album>(albumList, totalCount, pageNo, pageSize);
		return pagingData;
	}

	public List<Album> fallLoadAlbums(int albumsTailId, int limit, boolean isLoadTags) {
		List<Album> albumList = albumDao.fallLoadList(albumsTailId, limit);
		if (isLoadTags) {
			initAlbumsWithTags(albumList);
		}
		return albumList;
	}

	@Override
	public List<Album> fallLoadDesignerAlbums(int designerId, int albumsTailId, int limit, boolean isLoadTags) {
		List<Integer> designerIdList = new ArrayList<Integer>();
		designerIdList.add(designerId);
		List<Album> albumList = albumDao.fallLoadDesignerAlbums(designerIdList, albumsTailId, limit);
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
		List<UserFollow> followList = userGraphService.getFollowList(userId);
		if (followList != null && followList.size() > 0) {
			List<Integer> designerIdList = new ArrayList<Integer>();
			for (UserFollow userFollow : followList) {
				designerIdList.add(userFollow.getFollowId());
			}
			List<Album> albumList = albumDao.fallLoadDesignerAlbums(designerIdList, albumsTailId, limit);
			initAlbumsWithTags(albumList);
			return albumList;
		} else {
			throw new DesignerException(ErrorCode.GRAPH_HAS_NOT_FOLLOW);
		}
	}
	
	/**
	 * 瀑布流方式加载标签作品
	 */
	@Override
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

	public List<Album> queryAlbumByStatus(short status) {
		return albumDao.queryAlbumByStatus(status);
	}

	public List<Album> queryAlbumByUserId(int userId) {
		return albumDao.queryAlbumByUserId(userId);
	}

//	public long increceBroswer(int albumId, int albumSlideId) {
//		// 浏览计数
//		counterService.increase(ConstRedis.COUNTER_KEY_ALBUM_BROWSE + albumId);
//		long albumSlideLikeCount = counterService.increase(ConstRedis.COUNTER_KEY_ALBUMSLIDE_BROWSE + albumSlideId);
//		return albumSlideLikeCount;
//	}
//
//	public long increceComment(int albumId, int albumSlideId) {
//		// 评论计数
//		counterService.increase(ConstRedis.COUNTER_KEY_ALBUM_COMMENT + albumId);
//		long albumSlideCommentCount = counterService.increase(ConstRedis.COUNTER_KEY_ALBUMSLIDE_COMMENT + albumSlideId);
//		return albumSlideCommentCount;
//	}
//
//	public long increceLike(int albumId, int albumSlideId) {
//		// like计数
//		counterService.increase(ConstRedis.COUNTER_KEY_ALBUM_LIKE + albumId);
//		long albumSlideLikeCount = counterService.increase(ConstRedis.COUNTER_KEY_ALBUMSLIDE_LIKE + albumSlideId);
//		return albumSlideLikeCount;
//	}

	/**
	 * 加载专辑的tag标签
	 * 
	 * @param album
	 */
	private void initAlbumsWithTags(List<Album> albumList) {
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
	private void initAlbumWithTags(Album album) {
		if (album != null) {
			// 获取tag
			album.setTagList(tagService.getTagNamesByAlbumId(album.getId()));
		}
	}

}
