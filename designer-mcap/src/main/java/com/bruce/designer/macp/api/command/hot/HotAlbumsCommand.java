/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.hot;

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
import com.bruce.designer.macp.constants.ConstPaging;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.AlbumAuthorInfo;
import com.bruce.designer.model.AlbumSlide;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.IAlbumSlideService;
import com.bruce.designer.service.IHotService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.SharedInfoBuilder;
import com.bruce.designer.util.UserUtil;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.model.result.ApiResult;
import com.bruce.foundation.model.share.GenericSharedInfo;

/**
 * 最新专辑
 * @author liqian
 * 
 */
@Component
public class HotAlbumsCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(HotAlbumsCommand.class);
    
    @Autowired
    private IAlbumService albumService;
    @Autowired
    private IAlbumSlideService albumSlideService;
    @Autowired
    private IUserService userService;
    @Autowired
	private IHotService hotService;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(albumService, "albumService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	Map<String, Object> rt = new HashMap<String, Object>();
    	
    	int hostId = context.getUserId();
    	
    	String modeStr = context.getStringParams().get("mode");
    	short mode = NumberUtils.toShort(modeStr, (short)0);
    	
		if(logger.isDebugEnabled()){
            logger.debug("MCS查询热门专辑，mode: "+mode);
        }
	    if(logger.isDebugEnabled()){
            logger.debug("MCS查询热门专辑列表");
        }
		int limit = ConstPaging.ALBUM_HOT_WEEKLY_LIMIT;
		
		List<Album> hotAlbumList = hotService.fallLoadHotAlbums(mode, limit);
		if (hotAlbumList == null || hotAlbumList.size() == 0) {
		    if(logger.isDebugEnabled()){
                logger.debug("无更多热门专辑");
            }
		}else{
			//构造album中的设计师资料 & slide列表
			Map<Integer, AlbumAuthorInfo> albumAuthorMap = new HashMap<Integer, AlbumAuthorInfo>();
			for(Album album: hotAlbumList){
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
				int designerId = album.getUserId();
				AlbumAuthorInfo authorInfo = null;
				if(!albumAuthorMap.containsKey(designerId)){//考虑到多个作品的设计师可能是同一个人，因此使用map缓存
					User designer = userService.loadById(designerId);
					String designerAvatar = UserUtil.getAvatarUrl(designer, ConstService.UPLOAD_IMAGE_SPEC_MEDIUM);
					String designerNickname = designer.getNickname();
					boolean followed = false;//userGraphService.isFollow(hostId, albumAuthorId);
					authorInfo = new AlbumAuthorInfo(designerAvatar, designerNickname, followed);
				}else{
					authorInfo = albumAuthorMap.get(designerId);
				} 
				album.setAuthorInfo(authorInfo);
				
				//构造微信分享的对象
				GenericSharedInfo genericSharedInfo = SharedInfoBuilder.buildGenericSharedInfo(album);
				album.setGenericSharedInfo(genericSharedInfo);
			}
		}
		rt.put("hotAlbumList", hotAlbumList);
		rt.put("mode", mode);
        return ResponseBuilderUtil.buildSuccessResult(rt);
    }

	public IAlbumService getAlbumService() {
		return albumService;
	}

	public void setAlbumService(IAlbumService albumService) {
		this.albumService = albumService;
	}

	public IAlbumSlideService getAlbumSlideService() {
		return albumSlideService;
	}

	public void setAlbumSlideService(IAlbumSlideService albumSlideService) {
		this.albumSlideService = albumSlideService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IHotService getHotService() {
		return hotService;
	}

	public void setHotService(IHotService hotService) {
		this.hotService = hotService;
	}
	
}