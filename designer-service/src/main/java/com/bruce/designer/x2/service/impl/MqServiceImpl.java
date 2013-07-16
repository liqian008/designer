package com.renren.x2.feed.service.impl;

import com.renren.x2.feed.adapter.MqAdapter;
import com.renren.x2.feed.exception.FeedException;
import com.renren.x2.feed.message.DeleteUgcMessage;
import com.renren.x2.feed.message.FeedMessageType;
import com.renren.x2.feed.message.NewFeedMessage;
import com.renren.x2.feed.message.NewThirdPartyFeedMessage;
import com.renren.x2.feed.message.PostExtUgcMessage;
import com.renren.x2.feed.message.PostNativeUgcMessage;
import com.renren.x2.feed.service.IMqService;
import com.renren.x2.feedapi.model.Feed;
import com.renren.x2.feedapi.model.Ugc;

public class MqServiceImpl implements IMqService {

    private MqAdapter mqAdapter;

    @Override
    public void postPosts(int userId, int schoolId, Ugc ugc, int publishRenn) throws FeedException {
        PostNativeUgcMessage message = new PostNativeUgcMessage(FeedMessageType.MESSAGE_POST, userId, schoolId, publishRenn, ugc);
        mqAdapter.sendMq(FeedMessageType.MESSAGE_POST, message.toJson());
    }
    
    @Override
    public void postAlbum(int userId, Ugc ugc) throws FeedException {
        PostNativeUgcMessage message = new PostNativeUgcMessage(FeedMessageType.MESSAGE_ABLUM, userId, 0, 0, ugc);
        mqAdapter.sendMq(FeedMessageType.MESSAGE_ABLUM, message.toJson());
    }

    @Override
    public void generateFeed(int userId, int schoolId, Feed feed) throws FeedException {
        int mid = FeedMessageType.MESSAGE_NEW_FEED;
        NewFeedMessage message = new NewFeedMessage(mid, userId, schoolId, feed);
        mqAdapter.sendMq(mid, message.toJson());
    }

    @Override
    public void publishRenn(int userId, int schoolId, Feed feed) throws FeedException {
        int mid = FeedMessageType.MESSAGE_THIRTPARTY_FEED;
        NewThirdPartyFeedMessage message = new NewThirdPartyFeedMessage(mid, userId, schoolId, feed, 1);
        mqAdapter.sendMq(mid, message.toJson());
    }

    @Override
    public void generateComment(int userId, int ownerId, long feedId, Ugc ugc) throws FeedException {
        int mid = FeedMessageType.MESSAGE_NEW_COMMENT;
        PostExtUgcMessage message = new PostExtUgcMessage(mid, userId, ownerId, feedId, ugc);
        mqAdapter.sendMq(mid, message.toJson());
    }

    @Override
    public void generateLike(int userId, int ownerId, long feedId, Ugc ugc) throws FeedException {
        int mid = FeedMessageType.MESSAGE_NEW_LIKE;
        PostExtUgcMessage message = new PostExtUgcMessage(mid, userId, ownerId, feedId, ugc);
        mqAdapter.sendMq(mid, message.toJson());
    }

    public void setMqAdapter(MqAdapter mqAdapter) {
        this.mqAdapter = mqAdapter;
    }

    @Override
    public void postComment(int userId, long feedId, Ugc ugc) throws FeedException {
        PostExtUgcMessage message = new PostExtUgcMessage(FeedMessageType.MESSAGE_COMMENT, userId, 0, feedId, ugc);
        mqAdapter.sendMq(FeedMessageType.MESSAGE_COMMENT, message.toJson());
    }

    @Override
    public void postLike(int userId, long feedId) throws FeedException {
        PostExtUgcMessage message = new PostExtUgcMessage(FeedMessageType.MESSAGE_LIKE, userId, 0, feedId, null);
        mqAdapter.sendMq(FeedMessageType.MESSAGE_LIKE, message.toJson());
    }

    @Override
    public void deletePosts(int userId, long feedId, long ugcId) throws FeedException {
        int mid = FeedMessageType.MESSAGE_DELETE_POST;
        DeleteUgcMessage message = new DeleteUgcMessage(mid, userId, 0, feedId, ugcId);
        mqAdapter.sendMq(mid, message.toJson());
    }

    @Override
    public void deleteAlbum(int userId, long feedId, long ugcId) throws FeedException {
        int mid = FeedMessageType.MESSAGE_DELETE_ABLUM;
        DeleteUgcMessage message = new DeleteUgcMessage(mid, userId, 0, feedId, ugcId);
        mqAdapter.sendMq(mid, message.toJson());
    }

    @Override
    public void deleteComment(int userId, long feedId, long ugcId) throws FeedException {
        int mid = FeedMessageType.MESSAGE_DELETE_COMMENT;
        DeleteUgcMessage message = new DeleteUgcMessage(mid, userId, 0, feedId, ugcId);
        mqAdapter.sendMq(mid, message.toJson());
    }

    @Override
    public void deleteLike(int userId, long feedId) throws FeedException {
        int mid = FeedMessageType.MESSAGE_DELETE_LIKE;
        DeleteUgcMessage message = new DeleteUgcMessage(mid, userId, 0, feedId, 0);
        mqAdapter.sendMq(mid, message.toJson());
    }

}
