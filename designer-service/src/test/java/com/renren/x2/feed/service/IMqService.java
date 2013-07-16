package com.renren.x2.feed.service;

import com.renren.x2.feed.exception.FeedException;
import com.renren.x2.feedapi.model.Feed;
import com.renren.x2.feedapi.model.Ugc;


public interface IMqService {
    public void postPosts(int userId, int schoolId, Ugc ugc, int publishRenn) throws FeedException;
    
    public void postAlbum(int userId, Ugc ugc) throws FeedException;
    
    public void postComment(int userId, long feedId, Ugc ugc) throws FeedException;
    
    public void postLike(int userId, long feedId) throws FeedException;
    
    public void deletePosts(int userId, long feedId, long ugcId) throws FeedException;
    
    public void deleteAlbum(int userId, long feedId, long ugcId) throws FeedException;
    
    public void deleteComment(int userId, long feedId, long ugcId) throws FeedException;
    
    public void deleteLike(int userId, long feedId) throws FeedException;
    
    public void generateFeed(int userId, int schoolId, Feed feed) throws FeedException;
    
    public void generateComment(int userId, int ownerId, long feedId, Ugc ugc) throws FeedException;
    
    public void generateLike(int userId, int ownerId, long feedId, Ugc ugc) throws FeedException;

    public void publishRenn(int userId, int schoolId, Feed feed) throws FeedException;
}
