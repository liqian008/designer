/**
 * $Id: FeedConvertor.java 125505 2012-12-21 07:52:18Z chuang.zhang@XIAONEI.OPI.COM $
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.convertor;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.renren.x2.feed.beans.AbstractUgcDTO;
import com.renren.x2.feed.beans.AlbumDTO;
import com.renren.x2.feed.beans.FeedDTO;
import com.renren.x2.feed.beans.LikeDTO;
import com.renren.x2.feed.utils.CommonJsonUtils;
import com.renren.x2.feed.utils.FeedCommonUtils;
import com.renren.x2.feed.utils.FeedJsonUtils;
import com.renren.x2.feed.utils.UgcStatusUtils;
import com.renren.x2.feed.utils.UgcTypeUtils;
import com.renren.x2.feedapi.model.AlbumMedia;
import com.renren.x2.feedapi.model.Feed;
import com.renren.x2.feedapi.model.Ugc;
import com.renren.x2.feedapi.model.UgcUser;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2012-11-27 下午6:48:42
 */
public class FeedConvertor {

    public static <T extends AbstractUgcDTO> T convertUgc2DTO(long feedId, Ugc ugc, Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
            BeanUtils.populate(t, ugc2Map(ugc));
            t.setFeedId(feedId);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }
    
    public static <T extends AbstractUgcDTO> Ugc convertDTO2Ugc(T t){
        String contentInfo = t.getContentInfo();
        Ugc ugc = null;
        if(StringUtils.isNotBlank(contentInfo)){
            ugc = FeedJsonUtils.convertJson2UGC(contentInfo);
        }else{
            ugc = new Ugc();
        }
        if(ugc.getUserProfile() == null){
            ugc.setUserProfile(new UgcUser());
        }
        ugc.setStatus(UgcStatusUtils.conver2Enum(t.getStatus()));
        ugc.setCreateTime(t.getCreateTime());
        ugc.setUpdateTime(t.getUpdateTime());
        ugc.getUserProfile().setUserId(t.getUserId());
        ugc.setUgcId(t.getUgcId());
        if(t instanceof AlbumDTO){
            AlbumDTO albumT = (AlbumDTO) t;
            AlbumMedia media = FeedCommonUtils.getUgcAlbumMedia(ugc);
            media.setIsCurrent(albumT.isCurrentPhoto());
        }
        if(t instanceof LikeDTO){
            LikeDTO likeT = (LikeDTO) t;
            UgcUser user = ugc.getUserProfile();
            user.setGender(likeT.getUserGender());
            user.setName(likeT.getUserName());
            user.setHeadUrl(likeT.getUserHeadUrl());
        }
        return ugc;
    }
    
    public static <T extends AbstractUgcDTO> List<Ugc> convertDTOs2Ugcs(List<T> ugcDTOs){
        List<Ugc> result = new ArrayList<Ugc>(ugcDTOs.size());
        for(T ugcDTO : ugcDTOs){
            result.add(convertDTO2Ugc(ugcDTO));
        }
        return result;
    }
    
    public static <T extends AbstractUgcDTO> Map<Long, Ugc> convertDTOs2Ugcs(Map<Long, T> ugcDTOs){
        if(ugcDTOs == null){
            return new HashMap<Long, Ugc>();
        }
        Map<Long, Ugc> result = new HashMap<Long, Ugc>(ugcDTOs.size());
        Iterator<Entry<Long, T>> itr = ugcDTOs.entrySet().iterator();
        while(itr.hasNext()){
            Entry<Long, T> entry = itr.next();
            result.put(entry.getKey(), convertDTO2Ugc(entry.getValue()));
        }
        return result;
    }
    
    private static Map<String, Object> ugc2Map(Ugc ugc) {
        Map<String, Object> result = new HashMap<String, Object>();
        long ugcId = ugc.getUgcId();
        int userId = ugc.getUserProfile().getUserId();
        int statue = UgcStatusUtils.conver2Int(ugc.getStatus());
        String contentInfo = CommonJsonUtils.toJson(ugc);
        long createTime = ugc.getCreateTime();
        long updateTime = ugc.getUpdateTime();
        result.put("ugcId", ugcId);
        result.put("userId", userId);
        result.put("statue", statue);
        result.put("createTime", createTime);
        result.put("updateTime", updateTime);
        result.put("contentInfo", contentInfo);
        //custom params
        AlbumMedia media = FeedCommonUtils.getUgcAlbumMedia(ugc);
        if (media != null && media instanceof AlbumMedia) {
            boolean currentPhoto = ((AlbumMedia) media).getIsCurrent();
            result.put("currentPhoto", currentPhoto);
        }
        String userName = ugc.getUserProfile().getName();
        int userGender = ugc.getUserProfile().getGender();
        String userHeadUrl = ugc.getUserProfile().getHeadUrl();
        result.put("userName", userName);
        result.put("userGender", userGender);
        result.put("userHeadUrl", userHeadUrl);
        return result;
    }


    public static FeedDTO convertFeed2DTO(int schoolId, Feed feed) {
        long feedId = feed.getFeedId();
        long nativeUgcId = feed.getNativeUgc().getUgcId();
        long createTime = feed.getCreateTime();
        long updateTime = feed.getUpdateTime();
        int mLikeCount = feed.getMLikesCount();
        int fLikeCount = feed.getFLikesCount();
        int commentCount = feed.getCommentsCount();
        int userId = feed.getNativeUgc().getUserProfile().getUserId();
        int nativeUgcType = UgcTypeUtils.convert2Int(feed.getNativeUgcType());
        FeedDTO feedDTO = new FeedDTO(0, userId, createTime, updateTime, feedId, schoolId, nativeUgcId, nativeUgcType, mLikeCount, fLikeCount, commentCount);
        return feedDTO;
    }

    public static Feed convertDTO2Feed(FeedDTO feedDTO) {
        Feed feed = new Feed();
        feed.setFeedId(feedDTO.getFeedId());
        feed.setMLikesCount(feedDTO.getmLikeCount());
        feed.setFLikesCount(feedDTO.getfLikeCount());
        feed.setCommentsCount(feedDTO.getCommentCount());
        feed.setCreateTime(feedDTO.getCreateTime());
        feed.setUpdateTime(feedDTO.getUpdateTime());
        feed.setNativeUgcType(UgcTypeUtils.convert2NativeType(feedDTO.getNativeUgcType()));
        Ugc ugc = new Ugc();
        ugc.setUgcId(feedDTO.getNativeUgcId());
        UgcUser user = new UgcUser();
        user.setUserId(feedDTO.getUserId());
        ugc.setUserProfile(user);
        feed.setNativeUgc(ugc);
        return feed;
    }
    
    public static List<Feed> convertDTOs2Feeds(List<FeedDTO> feedDTOs){
        List<Feed> result = new ArrayList<Feed>(feedDTOs.size());
        for(FeedDTO feedDTO : feedDTOs){
            result.add(convertDTO2Feed(feedDTO));
        }
        return result;
    }
    
    public static  Map<Long, Feed> convertDTOs2Feeds(Map<Long, FeedDTO> feedDTOs){
        Map<Long, Feed> result = new HashMap<Long, Feed>(feedDTOs.size());
        Iterator<Entry<Long, FeedDTO>> itr = feedDTOs.entrySet().iterator();
        while(itr.hasNext()){
            Entry<Long, FeedDTO> entry = itr.next();
            result.put(entry.getKey(), convertDTO2Feed(entry.getValue()));
        }
        return result;
    }
}
