package com.renren.x2.feed.beans;

public class FeedUserTuple {

    private long feedId;

    public FeedUserTuple(long feedId, int userId) {
        super();
        this.feedId = feedId;
        this.userId = userId;
    }

    private int userId;

    public long getFeedId() {
        return feedId;
    }

    public void setFeedId(long feedId) {
        this.feedId = feedId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
