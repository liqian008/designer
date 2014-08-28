/**
 * $Id$
 * Copyright 2013 Sparta. All rights reserved.
 */
package com.bruce.designer.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.cache.counter.AlbumCounterCache;
import com.bruce.designer.cache.counter.UserAlbumCounterCache;
import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.exception.RedisKeyNotExistException;
import com.bruce.designer.service.IAlbumActionLogService;
import com.bruce.designer.service.IAlbumCommentService;
import com.bruce.designer.service.IAlbumCounterService;
import com.bruce.designer.service.IAlbumFavoriteService;
import com.bruce.designer.service.IAlbumLikeService;
import com.bruce.designer.service.IAlbumService;

/**
 * Comments for CounterServiceImpl.java
 * 
 * @author <a href="mailto:jun.liu@opi-corp.com">刘军</a>
 * @createTime 2013-8-11 下午12:08:09
 */
@Service
public class AlbumCounterServiceImpl implements IAlbumCounterService, InitializingBean { 

    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(AlbumCounterServiceImpl.class);

    @Autowired 
    private UserAlbumCounterCache userAlbumCounterCache;
    @Autowired 
    private AlbumCounterCache albumCounterCache;
    @Autowired
    private IAlbumActionLogService albumActionLogService;
    @Autowired
    private IAlbumLikeService albumLikeService;
    @Autowired
    private IAlbumFavoriteService albumFavoriteService;
    @Autowired
    private IAlbumCommentService commentService;
    @Autowired
    private IAlbumService albumService;
    
    
    /**
     * 获取用户的专辑数
     */
    @Override
    public int getUserAlbumCount(int userId) {
    	try {
    		return userAlbumCounterCache.getAlbumCount(userId);
    	} catch (RedisKeyNotExistException e) {
    		if(logger.isErrorEnabled()){
    			logger.error("getBrowseCount: "+userId, e);
    		}
			List<CountCacheBean> userAlbumsList = albumService.queryUserAlbumCount();//获取该album的like列表
            //重建缓存
            if(userAlbumsList!=null&&userAlbumsList.size()>0){
            	userAlbumCounterCache.setUserAlbumDataList(userAlbumsList);
            }
		}
    	return 0;
    }
    
    /**
     * 用户专辑数+1
     */
    @Override
    public int incrUserAlbum(int userId, int albumId) {
        int result = 0;
        try {
        	userAlbumCounterCache.incrAlbum(userId);
        } catch (RedisKeyNotExistException e) {
        	if(logger.isErrorEnabled()){
    			logger.error("incrUserAlbum: "+userId+", "+albumId, e);
    		}
        	
        	List<CountCacheBean> userAlbumsList = albumService.queryUserAlbumCount();//获取该album的like列表
            //重建缓存
            if(userAlbumsList!=null&&userAlbumsList.size()>0){
            	userAlbumCounterCache.setUserAlbumDataList(userAlbumsList);
            }
        }
        return result;
    }
    
    /**
     * 用户专辑数-1
     */
    /*设计师删除专辑*/
	public int reduceUserAlbum(int userId, int albumId){
		int result = 0;
        try {
        	userAlbumCounterCache.reduceAlbum(userId);
        } catch (RedisKeyNotExistException e) {
        	if(logger.isErrorEnabled()){
    			logger.error("incrUserAlbum: "+userId+", "+albumId, e);
    		}
        	List<CountCacheBean> userAlbumsList = albumService.queryUserAlbumCount();//获取该album的like列表
            //重建缓存
            if(userAlbumsList!=null&&userAlbumsList.size()>0){
            	userAlbumCounterCache.setUserAlbumDataList(userAlbumsList);
            }
        }
        return result;
	}
    
    
    
    
    
    @Override
    public long getBrowseCount(int albumId) {
    	try {
    		return albumCounterCache.getBrowseCount(albumId);
    	} catch (RedisKeyNotExistException e) {
    		if(logger.isErrorEnabled()){
    			logger.error("getBrowseCount: "+albumId, e);
    		}
    		
			List<CountCacheBean> browseList = albumActionLogService.queryBrowseStat();//获取该album的like列表
            //重建缓存
            if(browseList!=null&&browseList.size()>0){
                albumCounterCache.setBrowseDataList(browseList);
            }
		}
    	return 0;
    }

    @Override
    public long getCommentCount(int albumId) {
        try {
            return albumCounterCache.getCommentCount(albumId);
        } catch (RedisKeyNotExistException e) {
        	if(logger.isErrorEnabled()){
    			logger.error("getCommentCount: "+albumId, e);
    		}
        	
            List<CountCacheBean> commentList = commentService.queryCommentStat();//获取该album的comment列表
            //重建缓存
            if(commentList!=null&&commentList.size()>0){
                albumCounterCache.setCommentDataList(commentList);
            }
        }
        return 0;
    }
    
    @Override
    public long getLikeCount(int albumId) {
        return albumLikeService.getLikeCountByAlbumId(albumId);
    }

    @Override
    public long getFavoriteCount(int albumId) {
        return albumFavoriteService.getFavoriteCountByAlbumId(albumId);
    }
    
    @Override
    public long incrBrowser(int designerId, int albumId, int userId) {
        long result = 0;
        try {
            albumCounterCache.incrBrowse(albumId);
        } catch (RedisKeyNotExistException e) {
        	if(logger.isErrorEnabled()){
    			logger.error("incrBrowser: "+albumId+", "+designerId+", "+userId, e);
    		}
        	
            List<CountCacheBean> browseList = albumActionLogService.queryBrowseStat();//获取该album的like列表
            //重建缓存
            if(browseList!=null&&browseList.size()>0){
                albumCounterCache.setBrowseDataList(browseList);
            }
        }
        //增加浏览的actionLog
        albumActionLogService.logBrowse(albumId, designerId, userId);
        return result;
    }
    
    /**
     * 增加评论统计
     */
    @Override
    public long incrComment(int designerId, int albumId, int userId) {
        long result = 0;
        try {
            albumCounterCache.incrComment(albumId);
        } catch (RedisKeyNotExistException e) {
        	if(logger.isErrorEnabled()){
    			logger.error("incrComment: "+albumId+", "+designerId+", "+userId, e);
    		}
        	
        	List<CountCacheBean> commentList = commentService.queryCommentStat();//获取该album的评论数据
            //重建缓存
            if(commentList!=null&&commentList.size()>0){
                albumCounterCache.setCommentDataList(commentList);
            }
        }
        //增加评论的actionLog
        albumActionLogService.logComment(albumId, designerId, userId);
        return result;
    } 

    @Override
    public long incrLike(int designerId, int albumId, int userId) {
        long result = 0;
        if(albumLikeService.like(userId, albumId, designerId)){
            result = 1;
        }
        //增加actionLog
        albumActionLogService.logLike(albumId, designerId, userId);
        return result;
    }
    
    @Override
    public long incrFavorite(int designerId, int albumId, int userId) {
        long result = 0;
        if(albumFavoriteService.favorite(userId, albumId, designerId)){
            result = 1;
        }
        //增加actionLog
        albumActionLogService.logFavorite(albumId, designerId, userId);
        return result;
    }
    
    @Override
    public long reduceBrowser(int designerId, int albumId, int userId) {
        //无取消浏览操作
        return -1;
    }


    @Override
    public long reduceComment(int designerId, int albumId, int userId) {
        //TODO 删除评论计数
        //评论记录不需要删除actionLog
        return -1;
    }

    @Override
    public long reduceLike(int designerId, int albumId, int userId) {
        //取消赞
        if(albumLikeService.unlike(userId, albumId)){
            //不删除actionLog
            return 1;
        }
        return 0;
    }
    
    @Override
    public long reduceFavorite(int designerId, int albumId, int userId) {
        //取消收藏
        if(albumFavoriteService.unfavorite(userId, albumId)){
            //不删除actionLog
            return 1;
        }
        return 0;
    }
    
    @Override
    public boolean removeAlbum(int designer, int albumId) {
        // TODO 删除浏览、评论、like数值
        return true;
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
    }
    
}
