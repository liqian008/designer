/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.bruce.designer.model.UserFan;
import com.bruce.designer.model.UserFollow;
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
public class UserFollowsCommand extends AbstractApiCommand implements InitializingBean {

    private static final Log logger = LogFactory.getLog(UserFollowsCommand.class);
    
    @Autowired
    private IUserGraphService userGraphService;

    @Override
    public void afterPropertiesSet() throws Exception {
//        Assert.notNull(userGraphService, "userGraphService is required!");
    }

    @Override
    public ApiResult onExecute(ApiCommandContext context) {
    	Map<String, Object> rt = new HashMap<String, Object>();
    	
    	int queryUserId = 0;
    	if(logger.isDebugEnabled()){
            logger.debug("查询用户["+queryUserId+"]的关注列表");
        }
    	
        // TODO 获取关注列表
        int pageNo = 1;
		int pageSize = 20;
		
        List<UserFollow> followList = userGraphService.getFollowListWithUser(queryUserId, pageNo, pageSize);
        
        if(followList!=null&&followList.size()>0){
        	for(int i=followList.size()-1; i>=0;i--){
        		UserFollow userFollow = followList.get(i);
        		//只取有效用户
        		if(userFollow!=null&&userFollow.getFollowUser()!=null){
        			 //根据关注人员的名单，抽取关注人员Id以查询关注状态
//            			if(user!=null&&user.getId()>0){
//            				int followId = userFollow.getFollowId();
//            				//默认状态为未关注
//            				followMap.put(followId, false);
//                        }
        		}else{
        			followList.remove(i);
        		}
        	}
        }else{
            //无需处理
        }
        //构造返回数据
        rt.put("followList", followList);
        return ResponseBuilderUtil.buildSuccessResult(rt);
    }

	public IUserGraphService getUserGraphService() {
		return userGraphService;
	}

	public void setUserGraphService(IUserGraphService userGraphService) {
		this.userGraphService = userGraphService;
	}

}
