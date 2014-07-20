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
import org.springframework.util.Assert;

import com.bruce.designer.model.Album;
import com.bruce.designer.service.IAlbumService;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.model.result.ApiResult;

/**
 * 最新专辑
 * @author liqian
 * 
 */
public class LatestAlbumCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(LatestAlbumCommand.class);
    
    @Autowired
    private IAlbumService albumService;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(albumService, "albumService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	Map<String, Object> rt = new HashMap<String, Object>();
    	
    	List<Album> albumList = albumService.fallLoadAlbums(0, 20, true, false);
    	
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
