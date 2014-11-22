/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.album;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.bruce.designer.model.AlbumSlide;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IAlbumRecommendService;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.IAlbumSlideService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.SharedInfoBuilder;
import com.bruce.designer.util.UploadUtil;
import com.bruce.designer.util.UserUtil;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.model.result.ApiResult;

/**
 * 精选专辑
 * 
 * @author liqian
 * 
 */
@Component
public class RecommendAlbumCommand extends AbstractApiCommand implements InitializingBean {

	private static final Log logger = LogFactory.getLog(RecommendAlbumCommand.class);

	@Autowired
	private IUserService userService;
	@Autowired
	private IAlbumService albumService;
	@Autowired
	private IAlbumSlideService albumSlideService;
	@Autowired
	private IAlbumRecommendService albumRecommendService;

	@Override
	public void afterPropertiesSet() throws Exception {

		Assert.notNull(userService, "userService is required!");
		Assert.notNull(albumService, "albumService is required!");
		Assert.notNull(albumSlideService, "albumSlideService is required!");
		Assert.notNull(albumRecommendService, "albumRecommendService is required!");
	}

	@Override
	public ApiResult onExecute(ApiCommandContext context) {
		int hostId = context.getUserId();
		
		Map<String, Object> rt = new HashMap<String, Object>();
		
		int limit = 20;

		List<Album> albumList = albumRecommendService.queryRecommendAlbums(limit, true, false);
		if(albumList!=null){
			// 构造album中的设计师资料 & slide列表
			Map<Integer, AlbumAuthorInfo> albumAuthorMap = new HashMap<Integer, AlbumAuthorInfo>();
			for (Album album : albumList) {
				// 构造专辑的slide列表
				int albumId = album.getId();
				List<AlbumSlide> slideList = albumSlideService.querySlidesByAlbumId(albumId);
				if (slideList != null) {
					album.setSlideList(slideList);
				}
				
				if(logger.isDebugEnabled()){
	                logger.debug("MCS加载["+albumId+"]的与用户["+hostId+"]交互数据");
	                if(!UserUtil.isGuest(hostId)){//游客无需加载交互数据
	                	albumService.initAlbumInteractionStatus(album, hostId);
	                }
				}

				// 构造设计师信息
				int albumAuthorId = album.getUserId();
				AlbumAuthorInfo authorInfo = null;
				if (!albumAuthorMap.containsKey(albumAuthorId)) {// 考虑到多个作品的设计师可能是同一个人，因此使用map缓存
					User designer = userService.loadById(albumAuthorId);
					String designerAvatar = UploadUtil.getAvatarUrl(albumAuthorId, ConstService.UPLOAD_IMAGE_SPEC_MEDIUM);
					String designerNickname = designer.getNickname();
					boolean followed = false;// userGraphService.isFollow(hostId, albumAuthorId);
					authorInfo = new AlbumAuthorInfo(designerAvatar, designerNickname, followed);
				} else {
					authorInfo = albumAuthorMap.get(albumAuthorId);
				}
				album.setAuthorInfo(authorInfo);
				
				//构造微信分享的对象
				GenericSharedInfo genericSharedInfo = SharedInfoBuilder.buildGenericSharedInfo(album);
				album.setGenericSharedInfo(genericSharedInfo);
			}
		}
		rt.put("albumList", albumList);
		return ResponseBuilderUtil.buildSuccessResult(rt);
	}

	public IAlbumService getAlbumService() {
		return albumService;
	}

	public void setAlbumService(IAlbumService albumService) {
		this.albumService = albumService;
	}

}
