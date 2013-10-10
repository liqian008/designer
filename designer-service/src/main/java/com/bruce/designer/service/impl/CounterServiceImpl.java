/**
 * $Id$
 * Copyright 2013 Sparta. All rights reserved.
 */
package com.bruce.designer.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.bruce.designer.cache.counter.CounterCache;
import com.bruce.designer.service.ICounterService;

/**
 * Comments for CounterServiceImpl.java
 * 
 * @author <a href="mailto:jun.liu@opi-corp.com">刘军</a>
 * @createTime 2013-8-11 下午12:08:09
 */
@Service
public class CounterServiceImpl implements ICounterService, InitializingBean {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(CounterServiceImpl.class);

    @Autowired
    private CounterCache counterCache;

    @Override
    public long getCount(String key) {
        return counterCache.getCount(key);
    }
    
    @Override
    public Map<String, Long> mGetCounter(List<String> keyList) {
        return counterCache.mGetCounter(keyList);
    }

    @Override
    public long increase(String key) {
        return counterCache.increase(key);
    }

    @Override
    public long increaseNum(String key, int num) {
        return counterCache.increaseNum(key, num);
    }


    @Override
    public long reduce(String key) {
        return counterCache.reduce(key);
    }


    @Override
    public long reduceNum(String key, int num) {
        return counterCache.reduceNum(key, num);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(counterCache, "counterCache must not null!");
    }


}