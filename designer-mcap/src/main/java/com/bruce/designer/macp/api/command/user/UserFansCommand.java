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

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.macp.constants.ConstPaging;
import com.bruce.designer.model.UserFan;
import com.bruce.designer.service.IUserGraphService;
import com.bruce.designer.util.UserUtil;
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
    	
    	int hostId = context.getUserId();
    	
    	Map<String, Object> rt = new HashMap<String, Object>();
    	String queryUserIdStr = context.getStringParams().get("userId");
    	int queryUserId = NumberUtils.toInt(queryUserIdStr, 0);

    	if(logger.isDebugEnabled()){
            logger.debug("查询用户["+queryUserId+"]的关注列表");
        }
    	
        // TODO 获取关注列表
        int pageNo = 1;
        int pageSize = ConstPaging.FOLLOWS_LIMIT;
		
        List<UserFan> fanList = userGraphService.getFanListWithUser(queryUserId, pageNo, pageSize);
//        Map<Integer, Boolean> fanMap = new HashMap<Integer, Boolean>();
//        fanMap.put(queryUserId, false);
        
        if(fanList!=null&&fanList.size()>0){
        	for(int i=fanList.size()-1; i>=0;i--){
        		UserFan userFan = fanList.get(i);
        		//只取有效用户
        		if(userFan!=null&&userFan.getFanUser()!=null){
//    				int fanId = userFan.getFanId();
    				
        			//补全头像信息
                	String fanAvatarUrl = UserUtil.getAvatarUrl(userFan.getFanUser(), ConstService.UPLOAD_IMAGE_SPEC_MEDIUM);
                	userFan.getFanUser().setHeadImg(fanAvatarUrl);
        			
//        			根据粉丝人员的名单，抽取关注人员Id以查询关注状态
//    				//默认状态为未关注
//    				fanMap.put(fanId, false);
        		}else{
        			fanList.remove(i);
        		}
        	}
        	
        	//计算我与列表用户的关注状态
//        	for(Entry<Integer, Boolean> entry: fanMap.entrySet()){
//                int keyId = entry.getKey();
//                entry.setValue(userGraphService.isFollow(hostId, keyId));
//            }
        }else{
            //无需处理
        }
        //构造返回数据
        rt.put("fanList", fanList);
//        rt.put("fanMap", fanMap); 
        return ResponseBuilderUtil.buildSuccessResult(rt);
    }

	public IUserGraphService getUserGraphService() {
		return userGraphService;
	}

	public void setUserGraphService(IUserGraphService userGraphService) {
		this.userGraphService = userGraphService;
	}

}
