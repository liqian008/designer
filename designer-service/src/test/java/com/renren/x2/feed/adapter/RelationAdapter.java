package com.renren.x2.feed.adapter;

import org.apache.log4j.Logger;
import org.perf4j.aop.Profiled;

import com.renren.x2.feed.exception.FeedException;
import com.renren.x2.feed.utils.LogUtils;
import com.renren.x2.user.SimpleUserInfosResult;
import com.renren.x2.user.UserIdsResult;
import com.renren.x2.user.service.IRelationService;


public class RelationAdapter {
    private static final Logger logger = Logger.getLogger(RelationAdapter.class);
    
    private IRelationService relationService;

    @Profiled(tag="Adapter:getFollowers")
    public UserIdsResult getFollowers(int userId) throws FeedException {
        try {
            return relationService.getFollowers(userId);
        } catch (Exception e) {
            throw LogUtils.logAndGetIceException(logger, e);
        }
    }
    
    @Profiled(tag="Adapter:getFollowings")
    public SimpleUserInfosResult getFollowings(int userId) throws FeedException {
        try {
            return relationService.getFollowings(userId);
        } catch (Exception e) {
            throw LogUtils.logAndGetIceException(logger, e);
        }
    }
    
    public void setRelationService(IRelationService relationService) {
        this.relationService = relationService;
    }
}
