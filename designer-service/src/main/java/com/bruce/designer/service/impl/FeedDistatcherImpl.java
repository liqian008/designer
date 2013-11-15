package com.bruce.designer.service.impl;

import com.bruce.designer.dao.mapper.zc.FeedIndexMapper;
import com.bruce.designer.service.IFeedDispatcher;


public class FeedDistatcherImpl implements IFeedDispatcher {

    private FeedIndexMapper indexMapper;
    

    public FeedDistatcherImpl(FeedIndexMapper indexMapper) {
        super();
        this.indexMapper = indexMapper;
    }

    @Override
    public void dispatch(int targetId, long feedId) {
        indexMapper.insert(targetId, feedId);
    }

}
