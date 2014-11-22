/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.album;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.data.GenericSharedInfo;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.AlbumAuthorInfo;
import com.bruce.designer.model.AlbumFavorite;
import com.bruce.designer.model.AlbumSlide;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IAlbumFavoriteService;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.IAlbumSlideService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.SharedInfoBuilder;
import com.bruce.designer.util.UserUtil;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.model.result.ApiResult;

/**
 * 我收藏的专辑列表
 * @author liqian
 * 
 */
@Component
public class FavoriteAlbumsCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(FavoriteAlbumsCommand.class);
    
    @Autowired
    private IAlbumFavoriteService albumFavoriteService;
    @Autowired
    private IUserService userService;
    @Autowired
	private IAlbumService albumService;
    @Autowired
    private IAlbumSlideService albumSlideService;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(albumFavoriteService, "albumFavoriteService is required!");
        Assert.notNull(userService, "userService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	Map<String, Object> rt = new HashMap<String, Object>();
    	
    	int hostId = context.getUserId();
    	String tailIdStr = context.getStringParams().get("tailId");
    	int fromTailId = NumberUtils.toInt(tailIdStr, 0);
    	
		if(logger.isDebugEnabled()){
            logger.debug("加载我的收藏专辑，hostId："+hostId+"，fromTailId: "+fromTailId);
        }

		int limit = 1;
		//获取关注列表
		List<AlbumFavorite> favoriteList = albumFavoriteService.fallLoadUserFavoriteAlbums(hostId, fromTailId, limit+1);
		int newTailId = 0;

		if (favoriteList == null || favoriteList.size() == 0) {
		    if(logger.isDebugEnabled()){
                logger.debug("无更多专辑");
            }
		} else {
			if (favoriteList.size() > limit) {// 查询数据超过limit，含分页内容
				// 移除最后一个元素
				favoriteList.remove(limit);
				newTailId = favoriteList.get(limit - 1).getId();
				if(logger.isDebugEnabled()){
                    logger.debug("还有更多专辑，newTailId： "+newTailId);
                }
			}
			
			//构造album中的设计师资料 & slide列表
			Map<Integer, AlbumAuthorInfo> albumAuthorMap = new HashMap<Integer, AlbumAuthorInfo>();
			for(AlbumFavorite favorite: favoriteList){
				Album album = favorite.getAlbum();
				if(album!=null&&album.getId()!=null){
					//构造专辑的slide列表
					int albumId = album.getId();
					List<AlbumSlide> slideList = albumSlideService.querySlidesByAlbumId(albumId);
					if(slideList!=null){
						album.setSlideList(slideList);
					}
					
					if(logger.isDebugEnabled()){
		                logger.debug("MCS加载["+albumId+"]的与用户["+hostId+"]交互数据");
		                if(!UserUtil.isGuest(hostId)){//游客无需加载交互数据
		                	albumService.initAlbumInteractionStatus(album, hostId);
		                }
					}
					
					//构造设计师信息
					int albumAuthorId = album.getUserId();
					AlbumAuthorInfo authorInfo = null;
					if(!albumAuthorMap.containsKey(albumAuthorId)){//考虑到多个作品的设计师可能是同一个人，因此使用map缓存
						User designer = userService.loadById(albumAuthorId);
						String designerAvatar = UserUtil.getAvatarUrl(designer, ConstService.UPLOAD_IMAGE_SPEC_MEDIUM);
						String designerNickname = designer.getNickname();
						boolean followed = false;//userGraphService.isFollow(hostId, albumAuthorId);
						authorInfo = new AlbumAuthorInfo(designerAvatar, designerNickname, followed);
					}else{
						authorInfo = albumAuthorMap.get(albumAuthorId);
					}
					album.setAuthorInfo(authorInfo);
					
					//构造微信分享的对象
					GenericSharedInfo genericSharedInfo = SharedInfoBuilder.buildGenericSharedInfo(album);
					album.setGenericSharedInfo(genericSharedInfo);
				}
			} 
			
			rt.put("favoriteList", favoriteList);
			rt.put("fromTailId", String.valueOf(fromTailId));
			rt.put("newTailId", String.valueOf(newTailId));
	        return ResponseBuilderUtil.buildSuccessResult(rt);
		}
		return ResponseBuilderUtil.buildErrorResult();
    }

	public IAlbumFavoriteService getAlbumFavoriteService() {
		return albumFavoriteService;
	}

	public void setAlbumFavoriteService(IAlbumFavoriteService albumFavoriteService) {
		this.albumFavoriteService = albumFavoriteService;
	}

	
}
