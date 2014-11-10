/**
 * $Id $
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.bruce.designer.macp.api.command.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.model.UserFollow;
import com.bruce.designer.service.IUserGraphService;
import com.bruce.designer.util.UploadUtil;
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
    	
    	int hostId = context.getUserId();
    	
    	Map<String, Object> rt = new HashMap<String, Object>();
    	
    	String queryUserIdStr = context.getStringParams().get("userId");
    	int queryUserId = NumberUtils.toInt(queryUserIdStr, 0);
    	
    	String pageNoStr = context.getStringParams().get("pageNo");
    	int pageNo = NumberUtils.toInt(pageNoStr, 1);
    	int pageSize = 20;
    	
    	if(logger.isDebugEnabled()){
            logger.debug("查询用户["+queryUserId+"]的关注列表");
        }
    	
		
        List<UserFollow> followList = userGraphService.getFollowListWithUser(queryUserId, pageNo, pageSize);
        Map<Integer, Boolean> followMap = new HashMap<Integer, Boolean>();
        followMap.put(queryUserId, false);
        
        if(followList!=null&&followList.size()>0){
        	for(int i=followList.size()-1; i>=0;i--){
        		UserFollow userFollow = followList.get(i);
        		//只取有效用户
        		if(userFollow!=null&&userFollow.getFollowUser()!=null){
        			int followId = userFollow.getFollowId();
        			
        			//补全头像信息
                	String followAvatarUrl = UploadUtil.getAvatarUrl(followId, ConstService.UPLOAD_IMAGE_SPEC_MEDIUM);
                	userFollow.getFollowUser().setHeadImg(followAvatarUrl);
        			
        			//根据关注人员的名单，抽取关注人员Id以查询关注状态
					// 默认状态为未关注
					followMap.put(followId, false);
        		}else{
        			followList.remove(i);
        		}
        	}
        	
        	//计算我与列表用户的关注状态
        	for(Entry<Integer, Boolean> entry: followMap.entrySet()){
                int keyId = entry.getKey();
                entry.setValue(userGraphService.isFollow(hostId, keyId));
            }
        }else{
            //无需处理
        }
        //构造返回数据
        rt.put("followList", followList);
        rt.put("followMap", followMap);
        return ResponseBuilderUtil.buildSuccessResult(rt);
    }

	public IUserGraphService getUserGraphService() {
		return userGraphService;
	}

	public void setUserGraphService(IUserGraphService userGraphService) {
		this.userGraphService = userGraphService;
	}

}
