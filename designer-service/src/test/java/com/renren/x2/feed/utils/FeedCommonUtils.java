/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.renren.x2.feedapi.constants.ExtUgcType;
import com.renren.x2.feedapi.constants.NativeUgcType;
import com.renren.x2.feedapi.constants.UgcStatus;
import com.renren.x2.feedapi.model.AlbumMedia;
import com.renren.x2.feedapi.model.Feed;
import com.renren.x2.feedapi.model.TextMedia;
import com.renren.x2.feedapi.model.Ugc;
import com.renren.x2.feedapi.model.UgcContent;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2012-12-29 下午5:22:55
 */
public class FeedCommonUtils {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(FeedCommonUtils.class);

    public static AlbumMedia getFeedAlbumMedia(Feed feed) {
        if (feed != null && feed.getNativeUgcType() == NativeUgcType.Album) {
            return getUgcAlbumMedia(feed.getNativeUgc());
        }
        return null;
    }
    
    public static AlbumMedia getUgcAlbumMedia(Ugc ugc){
        if (ugc != null && ugc.getContent() != null) {
            List<UgcContent> contentList = ugc.getContent();
            for (UgcContent content : contentList) {
                if (content.getContent() instanceof AlbumMedia) {
                   return (AlbumMedia) content.getContent();
                }
            }
        }
        return null;
    }
    
    public static List<Feed> fillFeedFromMap(List<Long> feedIds, Map<Long, Feed> feedsMap){
        List<Feed> feedList = new ArrayList<Feed>(feedIds.size());
        for(Long feedId : feedIds){
            Feed feed = feedsMap.get(feedId);
            if(feed!= null){
                feedList.add(feed);
            }
        }
        return feedList;
    }
    
    public static List<Ugc> fillUgcFromMap(List<Long> ugcIds, Map<Long, Ugc> ugcsMap){
        List<Ugc> ugcList = new ArrayList<Ugc>(ugcIds.size());
        for(Long ugcId : ugcIds){
            Ugc ugc = ugcsMap.get(ugcId);
            if(ugc!= null){
                ugcList.add(ugc);
            }
        }
        return ugcList;
    }
    
    public static List<Long> getFeedIdsFromFeeds(List<Feed> feeds){
        List<Long> feedIds = new ArrayList<Long>();
        if(CollectionUtils.isEmpty(feeds)){
            return feedIds;
        }
        for(Feed feed : feeds){
            feedIds.add(feed.getFeedId());
        }
        return feedIds;
    }
    

    public static List<Long> getUgcIdsFromUgcs(List<Ugc> ugcs){
        List<Long> ugcIds = new ArrayList<Long>();
        if(CollectionUtils.isEmpty(ugcs)){
            return ugcIds;
        }
        for(Ugc ugc : ugcs){
            ugcIds.add(ugc.getUgcId());
        }
        return ugcIds;
    }
    
    public static void filterDeletedFeed(List<Feed> feeds) {
        Iterator<Feed> itr = feeds.iterator();
        while (itr.hasNext()) {
            Feed feed = itr.next();
            filterDeletedFeed(feed);
        }
    }

    public static void filterDeletedFeed(Feed feed) {
        if (feed.getNativeUgc().getStatus() == UgcStatus.Deleted
                || feed.getNativeUgc().getStatus() == UgcStatus.AdminDeleted) {
            if (feed.getNativeUgcType() == NativeUgcType.Posts) {
                feed.getNativeUgc().getContent().clear();
                feed.getNativeUgc().getContent().add(deletedPosts);
            }
        }
    }
    
    public static void removeDeletedFeed(List<Feed> feeds) {
        Iterator<Feed> itr = feeds.iterator();
        while (itr.hasNext()) {
            Feed feed = itr.next();
            if (feed.getNativeUgc().getStatus() == UgcStatus.Deleted
                    || feed.getNativeUgc().getStatus() == UgcStatus.AdminDeleted) {
                itr.remove();
            }
        }
    }
    
    public static void filterDeletedUgc(List<Ugc> ugcs, ExtUgcType extUgcType) {
        Iterator<Ugc> itr = ugcs.iterator();
        while (itr.hasNext()) {
            Ugc ugc = itr.next();
            if (ugc.getStatus() == UgcStatus.Deleted || ugc.getStatus() == UgcStatus.AdminDeleted) {
                if (extUgcType == ExtUgcType.FLike || extUgcType == ExtUgcType.MLike) {
                    itr.remove();
                } else if (extUgcType == ExtUgcType.Comment) {
                    ugc.getContent().clear();
                    ugc.getContent().add(deletedComment);
                }
            }
        }
    }
    
    private static final UgcContent deletedPosts = new UgcContent("text",
            new TextMedia("该条动态已被删除！"));

    private static final UgcContent deletedComment = new UgcContent("text", new TextMedia(
            "该条评论已被删除！"));
    
    private static final int DEFAULT_COMMENT_SIZE = 50;
    
    private static final int DEFAUTL_FEED_SIZE = 50;
    
    public static int getDefaultFeedSize(int size){
        if(size <= 0){
            return DEFAUTL_FEED_SIZE;
        }
        return size;
    }
    
    public static int getDefaultCommentSize(int size){
        if(size <= 0){
            return DEFAULT_COMMENT_SIZE;
        }
        return size;
    }
    
    public static int getDefaultCommentSize(){
        return getDefaultCommentSize(0);
    }
    
}
