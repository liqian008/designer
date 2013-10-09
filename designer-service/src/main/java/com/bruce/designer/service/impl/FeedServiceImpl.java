package com.bruce.designer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.bruce.designer.model.Feed;
import com.bruce.designer.model.FeedDTO;
import com.bruce.designer.model.Tag;
import com.bruce.designer.dao.FeedDTOMapper;
import com.bruce.designer.dao.FeedLiveIndexMapper;
import com.bruce.designer.dao.FeedTagIndexMapper;
import com.bruce.designer.service.IFeedDispatcher;
import com.bruce.designer.service.IFeedService;

public class FeedServiceImpl implements IFeedService, InitializingBean {

    //@Autowired
    private FeedDTOMapper feedDTOMapper;

   // @Autowired
    private FeedLiveIndexMapper feedLiveIndexMapper;
    
    //@Autowired
    private FeedTagIndexMapper feedTagIndexMapper;

    private IFeedDispatcher liveFeedDispatcher;
    
    private IFeedDispatcher tagFeedDispatcher;

    @Override
    public long insert(Feed feed) {
        int id = feedDTOMapper.insert(feed);
        if (id > 0) {
            //TODO get followers
            List<Integer> followers = new ArrayList<Integer>();
            followers.add(56789);
            for (int follower : followers) {
                liveFeedDispatcher.dispatch(follower, feed.getId());
            }
            for(Tag tag : feed.getTags()){
                int tagId = tag.getId();
                if(tagId > 0){
                    tagFeedDispatcher.dispatch(tagId, feed.getId());
                }
            }
        }
        return id;
    }

    @Override
    public Feed get(long id) {
        return new Feed(feedDTOMapper.selectByPrimaryKey(id));
    }

    @Override
    public List<Feed> getLiveFeed(int userId, int offset, int limit) {
        List<Long> ids = feedLiveIndexMapper.getIndex(userId, offset, limit);
        return getFeedsByIds(ids);
    }
    
    @Override
    public List<Feed> getTagFeed(int tagId, int offset, int limit) {
        List<Long> ids = feedTagIndexMapper.getIndex(tagId, offset, limit);
        return getFeedsByIds(ids);
    }
    
    private List<Feed> getFeedsByIds(List<Long> ids){
        List<FeedDTO> feedDTOs = feedDTOMapper.getFeedsByIds(ids);
        return dtos2Feeds(feedDTOs);
    }

    private List<Feed> dtos2Feeds(List<FeedDTO> feedDTOs) {
        List<Feed> feeds = new ArrayList<Feed>();
        for (FeedDTO dto : feedDTOs) {
            feeds.add(new Feed(dto));
        }
        return feeds;
    }

    public void setFeedDTOMapper(FeedDTOMapper feedDTOMapper) {
        this.feedDTOMapper = feedDTOMapper;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(feedDTOMapper, "feedDTOMapper is required!");
        this.liveFeedDispatcher = new FeedDistatcherImpl(feedLiveIndexMapper);
        this.tagFeedDispatcher = new FeedDistatcherImpl(feedTagIndexMapper);
    }

    
    public void setFeedLiveIndexMapper(FeedLiveIndexMapper feedLiveIndexMapper) {
        this.feedLiveIndexMapper = feedLiveIndexMapper;
    }

    
    public void setFeedTagIndexMapper(FeedTagIndexMapper feedTagIndexMapper) {
        this.feedTagIndexMapper = feedTagIndexMapper;
    }

    
    public void setLiveFeedDispatcher(IFeedDispatcher liveFeedDispatcher) {
        this.liveFeedDispatcher = liveFeedDispatcher;
    }

    
    public void setTagFeedDispatcher(IFeedDispatcher tagFeedDispatcher) {
        this.tagFeedDispatcher = tagFeedDispatcher;
    }

}
