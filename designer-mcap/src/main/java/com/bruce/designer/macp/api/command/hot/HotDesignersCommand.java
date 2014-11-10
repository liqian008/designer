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
import com.bruce.designer.model.User;
import com.bruce.designer.service.IHotService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.util.UploadUtil;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.model.result.ApiResult;

/**
 * 最新设计师
 * @author liqian
 * 
 */
@Component
public class HotDesignersCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(HotDesignersCommand.class);
    
    @Autowired
    private IUserService userService;
    @Autowired
	private IHotService hotService;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(userService, "userService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	Map<String, Object> rt = new HashMap<String, Object>();
    	
    	int hostId = context.getUserId();
    	
    	String modeStr = context.getStringParams().get("mode");
    	short mode = NumberUtils.toShort(modeStr, (short)0);
    	
		if(logger.isDebugEnabled()){
            logger.debug("MCS查询热门设计师，hostId: "+hostId + ", mode: "+mode);
        }
		int limit = 15;
		List<User> hotDesignerList = hotService.fallLoadHotDesigners(mode, limit);
		if (hotDesignerList == null || hotDesignerList.size() == 0) {
		    if(logger.isDebugEnabled()){
                logger.debug("无更多热门设计师");
            }
		}else{
			//构造album中的设计师资料 & slide列表
			for(User designer: hotDesignerList){
				String designerAvatar = UploadUtil.getAvatarUrl(designer.getId(), ConstService.UPLOAD_IMAGE_SPEC_MEDIUM);
				designer.setHeadImg(designerAvatar);
			}
		}
		rt.put("hotDesignerList", hotDesignerList);
		rt.put("mode", mode);
        return ResponseBuilderUtil.buildSuccessResult(rt);
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
