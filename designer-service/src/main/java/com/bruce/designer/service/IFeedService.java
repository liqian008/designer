package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.bean.Feed;

public interface IFeedService {

    public long insert(Feed feed);

    public Feed get(long id);

    public List<Feed> getLiveFeed(int userId, int offset, int limit);
    
    public List<Feed> getTagFeed(int tagId, int offset, int limit);

}
