/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.dao;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2012-12-29 下午4:44:47
 */
public interface ISequenceDao {

    public long getNextUgcId(int userId);

    public long getNextFeedId(int userId);
}
