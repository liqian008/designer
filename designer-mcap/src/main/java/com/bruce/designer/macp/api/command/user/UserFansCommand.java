/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.user;

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
import com.bruce.designer.model.User;
import com.bruce.designer.model.UserFan;
import com.bruce.designer.model.UserFan;
import com.bruce.designer.service.IUserGraphService;
import com.bruce.foundation.macp.api.command.AbstractApiCommand;
import com.bruce.foundation.macp.api.entity.ApiCommandContext;
import com.bruce.foundation.macp.api.utils.ResponseBuilderUtil;
import com.bruce.foundation.model.result.ApiResult;

/**
 * 用户的关注列表
 * @author liqian
 * 
 */
@Component
public class UserFansCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(UserFansCommand.class);
    
    @Autowired
    private IUserGraphService userGraphService;

    @Override
    public void afterPropertiesSet() throws Exception {
//        Assert.notNull(userGraphService, "userGraphService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	Map<String, Object> rt = new HashMap<String, Object>();
    	String queryUserIdStr = context.getStringParams().get("userId");
    	int queryUserId = NumberUtils.toInt(queryUserIdStr, 0);

    	if(logger.isDebugEnabled()){
            logger.debug("查询用户["+queryUserId+"]的关注列表");
        }
    	
        // TODO 获取关注列表
        int pageNo = 1;
		int pageSize = 20;
		
        List<UserFan> fanList = userGraphService.getFanListWithUser(queryUserId, pageNo, pageSize);
        
        if(fanList!=null&&fanList.size()>0){
        	for(int i=fanList.size()-1; i>=0;i--){
        		UserFan userFan = fanList.get(i);
        		//只取有效用户
        		if(userFan!=null&&userFan.getFanUser()!=null){
        			 //根据关注人员的名单，抽取关注人员Id以查询关注状态
//            			if(user!=null&&user.getId()>0){
//            				int fanId = userFan.getFanId();
//            				//默认状态为未关注
//            				fanMap.put(fanId, false);
//                        }
        		}else{
        			fanList.remove(i);
        		}
        	}
        }else{
            //无需处理
        }
        //构造返回数据
        rt.put("fanList", fanList);
        return ResponseBuilderUtil.buildSuccessResult(rt);
    }

	public IUserGraphService getUserGraphService() {
		return userGraphService;
	}

	public void setUserGraphService(IUserGraphService userGraphService) {
		this.userGraphService = userGraphService;
	}

}
