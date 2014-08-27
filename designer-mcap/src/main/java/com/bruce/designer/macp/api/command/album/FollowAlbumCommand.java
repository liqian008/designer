/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.album;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.AlbumAuthorInfo;
import com.bruce.designer.model.AlbumSlide;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.IAlbumSlideService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.UploadUtil;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.model.result.ApiResult;

/**
 * 我关注的专辑
 * @author liqian
 * 
 */
@Component
public class FollowAlbumCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(FollowAlbumCommand.class);
    
    @Autowired
    private IUserService userService;
    @Autowired
    private IAlbumService albumService;
    @Autowired
    private IAlbumSlideService albumSlideService;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(albumService, "albumService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	Map<String, Object> rt = new HashMap<String, Object>();
    	
    	int hostId = context.getUserId();
    	String albumsTailIdStr = context.getStringParams().get("albumsTailId");
    	int fromTailId = NumberUtils.toInt(albumsTailIdStr, 0);
    	
		if(logger.isDebugEnabled()){
            logger.debug("ajax加载我的关注专辑，hostId："+hostId+"，fromTailId: "+fromTailId);
        }

		int limit = 20;
		//获取关注列表
		List<Album> albumList = albumService.fallLoadUserFollowAlbums(hostId, fromTailId, limit + 1);
		int newTailId = 0;

		if (albumList == null || albumList.size() == 0) {
		    if(logger.isDebugEnabled()){
                logger.debug("无更多专辑");
            }
		} else {
			if (albumList.size() > limit) {// 查询数据超过limit，含分页内容
				// 移除最后一个元素
				albumList.remove(limit);
				newTailId = albumList.get(limit - 1).getId();
				if(logger.isDebugEnabled()){
                    logger.debug("还有更多专辑，newTailId： "+newTailId);
                }
			}
			
			
			//构造albumList中的设计师资料
			Map<Integer, AlbumAuthorInfo> albumAuthorMap = new HashMap<Integer, AlbumAuthorInfo>();
			for(Album album: albumList){
				
				//构造专辑的slide列表
				int albumId = album.getId();
				List<AlbumSlide> slideList = albumSlideService.querySlidesByAlbumId(albumId);
				if(slideList!=null){
					album.setSlideList(slideList);
				}
				
				
				int designerId = album.getUserId();
				AlbumAuthorInfo authorInfo = null;
				if(!albumAuthorMap.containsKey(designerId)){//考虑到多个作品的设计师可能是同一个人，因此使用map缓存
					User designer = userService.loadById(designerId);
					String designerAvatar = UploadUtil.getAvatarUrl(designerId, ConstService.UPLOAD_IMAGE_SPEC_MEDIUM);
					String designerNickname = designer.getNickname();
					boolean followed = true;
					authorInfo = new AlbumAuthorInfo(designerAvatar, designerNickname, followed);
				}else{
					authorInfo = albumAuthorMap.get(designerId);
				}
				album.setAuthorInfo(authorInfo);
			}
		}
		rt.put("albumList", albumList);
		rt.put("fromTailId", String.valueOf(fromTailId));
		rt.put("newTailId", String.valueOf(newTailId));
		return ResponseBuilderUtil.buildSuccessResult(rt);
//		return ResponseBuilderUtil.buildErrorResult();
    }

	public IAlbumService getAlbumService() {
		return albumService;
	}

	public void setAlbumService(IAlbumService albumService) {
		this.albumService = albumService;
	}

}
