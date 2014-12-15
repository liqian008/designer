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

import com.bruce.designer.exception.DesignerException;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.AlbumSlide;
import com.bruce.designer.service.IAlbumCounterService;
import com.bruce.designer.service.IAlbumService;
import com.bruce.designer.service.IAlbumSlideService;
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
public class AlbumInfoCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(AlbumInfoCommand.class);
    
    @Autowired
    private IAlbumService albumService;
    @Autowired
    private IAlbumSlideService albumSlideService;
    @Autowired
    private IAlbumCounterService albumCounterService;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(albumService, "albumService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	
    	int hostId = context.getUserId();
    	
    	Map<String, Object> rt = new HashMap<String, Object>();
    	
    	String albumIdStr = context.getStringParams().get("albumId");
    	int albumId = NumberUtils.toInt(albumIdStr, 0);
    	
    	if(logger.isDebugEnabled()){
            logger.debug("MCS浏览专辑["+albumId+ "]");
        }
		Album albumInfo = albumService.loadById(albumId, false, false, true);
		if (albumInfo != null) {
			// 读取作品列表
			List<AlbumSlide> slideList = albumSlideService.querySlidesByAlbumId(albumId);
			albumInfo.setSlideList(slideList);
			
			if(logger.isDebugEnabled()){
				logger.debug("MCS加载["+albumId+"]的与用户["+hostId+"]交互数据");
                if(!UserUtil.isGuest(hostId)){//游客无需加载交互数据
                	albumService.initAlbumInteractionStatus(albumInfo, hostId);
                }
			}
			
			// 增加浏览计数
			if(logger.isDebugEnabled()){
                logger.debug("MCS增加专辑["+albumId+"]浏览计数, 浏览人: " + hostId);
            }
			albumCounterService.incrBrowser(albumInfo.getUserId(), albumId, hostId);
			
			//加载专辑的计数信息
			if(logger.isDebugEnabled()){
                logger.debug("加载专辑["+albumId+"]浏览计数");
            }
			albumService.initAlbumWithCount(albumInfo);//TODO 可以考虑与loadInfo重构在一起
			
//			//加载专辑的标签，暂时删除
//			if(logger.isDebugEnabled()){
//                logger.debug("加载专辑["+albumId+"]的标签");
//            }
//			albumService.initAlbumWithTags(albumInfo);
			
			//构造微信分享的对象
			GenericSharedInfo genericSharedInfo = SharedInfoBuilder.buildGenericSharedInfo(albumInfo);
			albumInfo.setGenericSharedInfo(genericSharedInfo);
			
			rt.put("albumInfo", albumInfo);
	        return ResponseBuilderUtil.buildSuccessResult(rt);
		}else{
			if(logger.isErrorEnabled()){
				logger.error("加载作品集["+albumId+"]出错");
			}
			throw new DesignerException(ErrorCode.ALBUM_NOT_EXIST);
		}
    }

	public IAlbumService getAlbumService() {
		return albumService;
	}

	public void setAlbumService(IAlbumService albumService) {
		this.albumService = albumService;
	}

}
