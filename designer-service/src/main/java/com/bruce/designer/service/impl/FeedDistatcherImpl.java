package com.bruce.designer.service.impl;

import com.bruce.designer.dao.FeedIndexMapper;
import com.bruce.designer.service.FeedDispatcher;


public class FeedDistatcherImpl implements FeedDispatcher {

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
