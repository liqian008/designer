/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.renren.x2.feed.beans.AlbumDTO;
import com.renren.x2.feed.beans.CommentDTO;
import com.renren.x2.feed.beans.FeedDTO;
import com.renren.x2.feed.beans.LikeDTO;
import com.renren.x2.feed.beans.LoveStatusDTO;
import com.renren.x2.feed.beans.PersonalVoiceDTO;
import com.renren.x2.feed.beans.PostDTO;
import com.renren.x2.feed.beans.TopTenDTO;
import com.renren.x2.feed.constants.FeedParamConstants;
import com.renren.x2.feed.convertor.FeedConvertor;
import com.renren.x2.feed.exception.FeedException;
import com.renren.x2.feed.jade.dao.AlbumJadeDAO;
import com.renren.x2.feed.jade.dao.CommentJadeDAO;
import com.renren.x2.feed.jade.dao.FeedJadeDAO;
import com.renren.x2.feed.jade.dao.LikeJadeDAO;
import com.renren.x2.feed.jade.dao.LoveStatusJadeDAO;
import com.renren.x2.feed.jade.dao.PersonalVoiceJadeDAO;
import com.renren.x2.feed.jade.dao.PostJadeDAO;
import com.renren.x2.feed.jade.dao.TopTenJadeDAO;
import com.renren.x2.feed.service.impl.AbstractFeedHelper;
import com.renren.x2.feed.storage.IFeedReaderStorage;
import com.renren.x2.feed.utils.FeedCommonUtils;
import com.renren.x2.feed.utils.UgcTypeUtils;
import com.renren.x2.feedapi.constants.ExtUgcType;
import com.renren.x2.feedapi.constants.NativeUgcType;
import com.renren.x2.feedapi.model.Feed;
import com.renren.x2.feedapi.model.Ugc;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2013-1-4 下午3:05:43
 */
public class FeedReaderDaoImpl extends AbstractFeedHelper implements IFeedReaderStorage {

    @Autowired
    private FeedJadeDAO feedJadeDAO;

    @Autowired
    private PostJadeDAO postJadeDAO;

    @Autowired
    private AlbumJadeDAO albumJadeDAO;

    @Autowired
    private LoveStatusJadeDAO loveStatusJadeDAO;

    @Autowired
    private PersonalVoiceJadeDAO personalVoiceJadeDAO;

    @Autowired
    private CommentJadeDAO commentJadeDAO;

    @Autowired
    private LikeJadeDAO likeJadeDAO;

    @Autowired
    private TopTenJadeDAO topTenJadeDAO;

    //P-post A-album L-loveStatus V-personalVoice
    private static final List<Integer> NUT_PALV = new ArrayList<Integer>();

    private static final List<Integer> NUT_P = new ArrayList<Integer>();

    private static final List<Integer> NUT_A = new ArrayList<Integer>();

    private static final FeedIdComparator feedIdCommarator = new FeedIdComparator();

    private static final int DEFAULT_ALBUM_SIZE = 200;

    private static final int DEFAULT_LIKE_SIZE = 500;

    static {
        NUT_PALV.add(UgcTypeUtils.TYPE_POST);
        NUT_PALV.add(UgcTypeUtils.TYPE_ALBUM);
        NUT_PALV.add(UgcTypeUtils.TYPE_LOVESTATUS);
        NUT_PALV.add(UgcTypeUtils.TYPE_PERSONALVOICE);

        NUT_P.add(UgcTypeUtils.TYPE_POST);

        NUT_A.add(UgcTypeUtils.TYPE_ALBUM);
    }

    @Override
    public List<Feed> getSchoolFeeds(int schoolId, long feedId, boolean forward, int limit,
            int offset) {
        List<Long> feedIds = null;
        if (feedId <= 0) {
            feedIds = feedJadeDAO.getSchoolFeeds(schoolId, limit);
        } else {
            feedIds = feedJadeDAO.getSchoolAfterFeeds(schoolId, feedId, limit);
        }
        return getFeedsFromIds(feedIds);
    }

    protected List<Feed> getFeedsFromIds(List<Long> feedIds) {
        Map<Long, Feed> feedsMap = getFeeds(feedIds);
        List<Feed> feeds = FeedCommonUtils.fillFeedFromMap(feedIds, feedsMap);
        fillNativeUgc(feeds);
        return feeds;
    }

    @Override
    public List<Feed> getUserFeeds(int userId, long feedId, boolean forward, int size, int offset) {
        return getUserFeeds(userId, feedId, forward, size, offset, NUT_PALV);
    }

    protected List<Feed> getUserFeeds(int userId, long feedId, boolean forward, int size,
            int offset, List<Integer> nativeUgcTypes) {
        List<FeedDTO> feedDTOs = null;
        if (feedId <= 0) {
            feedDTOs = feedJadeDAO.getFeedsByUser(userId, size, nativeUgcTypes);
        } else {
            feedDTOs = feedJadeDAO.getFeedsByUser(userId, feedId, size, nativeUgcTypes);
        }
        List<Feed> feeds = FeedConvertor.convertDTOs2Feeds(feedDTOs);
        fillNativeUgc(feeds);
        return feeds;
    }

    @Override
    public List<Feed> getUserFollowerFeeds(int userId, long feedId, boolean forward, int size,
            int offset) throws FeedException {
        List<Feed> feeds = new ArrayList<Feed>();
        List<Integer> followingUserIds = getFollowingIds(userId);
        for (int followingUserId : followingUserIds) {
            List<Feed> userFeeds = getUserFeeds(followingUserId, feedId, forward, size, offset,
                    NUT_PALV);
            feeds.addAll(userFeeds);
        }
        Collections.sort(feeds, feedIdCommarator);
        return feeds.subList(0, Math.min(size, feeds.size()));
    }

    @Override
    public List<Feed> getUserAlbumFeeds(int userId) {
        return getUserFeeds(userId, 0, true, DEFAULT_ALBUM_SIZE, 0, NUT_A);
    }

    @Override
    public Feed getFeedById(long feedId) {
        List<Long> feedIds = new ArrayList<Long>();
        feedIds.add(feedId);
        List<Feed> feeds = getFeedsFromIds(feedIds);
        if (CollectionUtils.isNotEmpty(feeds)) {
            return feeds.get(0);
        }
        return null;
    }

    protected void fillNativeUgc(List<Feed> feeds) {
        Map<Long, NativeUgcType> ugcIdTypeMap = new HashMap<Long, NativeUgcType>();
        for (Feed feed : feeds) {
            ugcIdTypeMap.put(feed.getNativeUgc().getUgcId(), feed.getNativeUgcType());
        }
        Map<Long, Ugc> ugcsMap = getNativeUgcs(ugcIdTypeMap);
        for (Feed feed : feeds) {
            Ugc ugc = ugcsMap.get(feed.getNativeUgc().getUgcId());
            if (ugc != null) {
                feed.setNativeUgc(ugc);
            }
        }
    }

    @Override
    public Boolean isUserLiked(int userId, long feedId, ExtUgcType likeType) {
        Ugc ugc = getUserLike(userId, feedId, likeType);
        return ugc != null;
    }

    @Override
    public Map<Long, Boolean> isUserLiked(int userId, List<Long> feedIds, ExtUgcType likeType) {
        Map<Long, Ugc> ugcs = getUserLikes(userId, feedIds, likeType);
        Map<Long, Boolean> isUserLikedMap = new HashMap<Long, Boolean>(feedIds.size());
        for (Long feedId : feedIds) {
            if (ugcs.containsKey(feedId)) {
                isUserLikedMap.put(feedId, true);
            } else {
                isUserLikedMap.put(feedId, false);
            }
        }
        return isUserLikedMap;
    }

    @Override
    public Ugc getUserLike(int userId, long feedId, ExtUgcType likeType) {
        List<LikeDTO> ugcDTOs = null;
        Ugc ugc = null;
        if (likeType == ExtUgcType.FLike) {
            ugcDTOs = likeJadeDAO.getFUserLike(feedId, userId);
        } else {
            ugcDTOs = likeJadeDAO.getMUserLike(feedId, userId);
        }
        if (CollectionUtils.isNotEmpty(ugcDTOs)) {
            ugc = FeedConvertor.convertDTO2Ugc(ugcDTOs.get(0));
        }
        return ugc;
    }

    public Map<Long, Ugc> getUserLikes(int userId, List<Long> feedIds, ExtUgcType likeType) {
        //对feedId规类
        Map<Long, List<Long>> map = new HashMap<Long, List<Long>>();
        Map<Long, LikeDTO> ugcDTOs = new HashMap<Long, LikeDTO>();
        for (long feedId : feedIds) {
            long shardId = feedId % FeedParamConstants.SHARDING_NUM;
            List<Long> shardList = map.get(shardId);
            if (shardList == null) {
                shardList = new ArrayList<Long>();
                map.put(shardId, shardList);
            }
            shardList.add(feedId);
        }
        Iterator<Entry<Long, List<Long>>> itr = map.entrySet().iterator();
        while (itr.hasNext()) {
            Entry<Long, List<Long>> entry = itr.next();
            if (likeType == ExtUgcType.FLike) {
                ugcDTOs.putAll(likeJadeDAO.getFUserLikes(entry.getKey(), entry.getValue(), userId));
            } else {
                ugcDTOs.putAll(likeJadeDAO.getMUserLikes(entry.getKey(), entry.getValue(), userId));
            }
        }
        Map<Long, Ugc> ugcs = FeedConvertor.convertDTOs2Ugcs(ugcDTOs);
        return ugcs;
    }
    
    @Override
    public Map<Long, Ugc> getUserLikeFeeds(int userId, ExtUgcType likeType){
        List<LikeDTO> ugcDTOs = new ArrayList<LikeDTO>();
        if(likeType == ExtUgcType.FLike){
            for(long shardId=0; shardId<FeedParamConstants.SHARDING_NUM ; shardId++){
                ugcDTOs.addAll(likeJadeDAO.getFUserLikeByShardId(shardId, userId));
            }
        }else{
            for(long shardId=0; shardId<FeedParamConstants.SHARDING_NUM; shardId++){
                ugcDTOs.addAll(likeJadeDAO.getMUserLikeByShardId(shardId, userId));
            }
        }
        Map<Long, Ugc> result = new HashMap<Long, Ugc>();
        for(LikeDTO ugcDTO : ugcDTOs){
            result.put(ugcDTO.getFeedId(), FeedConvertor.convertDTO2Ugc(ugcDTO));
        }
        return result;
    }
    
    @Override
    public List<Ugc> getFeedComments(long feedId, long ugcId, boolean forward, int size, int offset) {
        List<CommentDTO> ugcDTOs = null;
        if (ugcId <= 0) {
            ugcDTOs = commentJadeDAO.getComments(feedId, size);
        } else {
            ugcDTOs = commentJadeDAO.getComments(feedId, ugcId, size);
        }
        List<Ugc> ugcs = FeedConvertor.convertDTOs2Ugcs(ugcDTOs);
        return ugcs;
    }

    @Override
    public List<Ugc> getFeedFLikes(long feedId) {
        List<LikeDTO> ugcDTOs = likeJadeDAO.getFLikes(feedId, DEFAULT_LIKE_SIZE);
        List<Ugc> ugcs = FeedConvertor.convertDTOs2Ugcs(ugcDTOs);
        return ugcs;
    }

    @Override
    public List<Ugc> getFeedMLikes(long feedId) {
        List<LikeDTO> ugcDTOs = likeJadeDAO.getMLikes(feedId, DEFAULT_LIKE_SIZE);
        List<Ugc> ugcs = FeedConvertor.convertDTOs2Ugcs(ugcDTOs);
        return ugcs;
    }

    @Override
    public Map<Long, Ugc> getExtUgcs(long feedId, List<Long> ugcIds, ExtUgcType ugcType) {
        switch (ugcType) {
            case Comment:
                Map<Long, CommentDTO> commentDTOs = commentJadeDAO.getComments(feedId, ugcIds);
                return FeedConvertor.convertDTOs2Ugcs(commentDTOs);
            case FLike:
                Map<Long, LikeDTO> flikeDTOs = likeJadeDAO.getFLikes(feedId, ugcIds);
                return FeedConvertor.convertDTOs2Ugcs(flikeDTOs);
            case MLike:
                Map<Long, LikeDTO> mlikeDTOs = likeJadeDAO.getMLikes(feedId, ugcIds);
                return FeedConvertor.convertDTOs2Ugcs(mlikeDTOs);
            default:
                break;
        }
        return new HashMap<Long, Ugc>();
    }

    protected Map<Long, Ugc> getNativeUgcs(Map<Long, NativeUgcType> ugcIdTypeMap) {
        //根据nativeUgcType和shardId归类
        Map<NativeUgcType, Map<Long, List<Long>>> map = new HashMap<NativeUgcType, Map<Long, List<Long>>>();
        Map<Long, Ugc> result = new HashMap<Long, Ugc>();
        Iterator<Entry<Long, NativeUgcType>> itr = ugcIdTypeMap.entrySet().iterator();
        while (itr.hasNext()) {
            //nativeUgcType
            Entry<Long, NativeUgcType> entry = itr.next();
            long ugcId = entry.getKey();
            long shardId = ugcId % FeedParamConstants.SHARDING_NUM;
            NativeUgcType ugcType = entry.getValue();

            Map<Long, List<Long>> shardIdsMap = map.get(ugcType);
            if (shardIdsMap == null) {
                shardIdsMap = new HashMap<Long, List<Long>>();
                map.put(ugcType, shardIdsMap);
            }
            //shardId
            List<Long> ugcIds = shardIdsMap.get(shardId);
            if (ugcIds == null) {
                ugcIds = new ArrayList<Long>();
                shardIdsMap.put(shardId, ugcIds);
            }
            ugcIds.add(ugcId);
        }
        //根据分类去取
        Iterator<Entry<NativeUgcType, Map<Long, List<Long>>>> itr2 = map.entrySet().iterator();
        while (itr2.hasNext()) {
            Entry<NativeUgcType, Map<Long, List<Long>>> entry2 = itr2.next();
            NativeUgcType nativeUgcType = entry2.getKey();
            Iterator<Entry<Long, List<Long>>> itr3 = entry2.getValue().entrySet().iterator();
            while (itr3.hasNext()) {
                Entry<Long, List<Long>> entry3 = itr3.next();
                long shardId = entry3.getKey();
                List<Long> ugcIds = entry3.getValue();
                result.putAll(getNativeUgcs(shardId, ugcIds, nativeUgcType));
            }
        }
        return result;
    }

    public Map<Long, Ugc> getNativeUgcs(long shardId, List<Long> ugcIds, NativeUgcType nativeUgcType) {
        switch (nativeUgcType) {
            case Posts:
                Map<Long, PostDTO> postDTOs = postJadeDAO.getPosts(shardId, ugcIds);
                return FeedConvertor.convertDTOs2Ugcs(postDTOs);
            case Album:
                Map<Long, AlbumDTO> albumDTOs = albumJadeDAO.getAlbums(shardId, ugcIds);
                return FeedConvertor.convertDTOs2Ugcs(albumDTOs);
            case LoveStatus:
                Map<Long, LoveStatusDTO> loveStatusDTOs = loveStatusJadeDAO.getLoveStatus(shardId,
                        ugcIds);
                return FeedConvertor.convertDTOs2Ugcs(loveStatusDTOs);
            case PersonalVoice:
                Map<Long, PersonalVoiceDTO> personalVoiceDTOs = personalVoiceJadeDAO
                        .getPersonalVoice(shardId, ugcIds);
                return FeedConvertor.convertDTOs2Ugcs(personalVoiceDTOs);
        }
        return new HashMap<Long, Ugc>();
    }

    @Override
    public Map<Long, Feed> getFeeds(List<Long> feedIds) {
        //按照shardId归类
        Map<Long, Feed> result = new HashMap<Long, Feed>();
        Map<Long, List<Long>> map = new HashMap<Long, List<Long>>();
        for (long feedId : feedIds) {
            long shardId = feedId % FeedParamConstants.SHARDING_NUM;
            List<Long> shardFeedIds = map.get(shardId);
            if (shardFeedIds == null) {
                shardFeedIds = new ArrayList<Long>();
                map.put(shardId, shardFeedIds);
            }
            shardFeedIds.add(feedId);
        }
        Iterator<Entry<Long, List<Long>>> itr = map.entrySet().iterator();
        while (itr.hasNext()) {
            Entry<Long, List<Long>> entry = itr.next();
            Map<Long, FeedDTO> feedDTOs = feedJadeDAO.getFeedsByIds(entry.getKey(),
                    entry.getValue());
            result.putAll(FeedConvertor.convertDTOs2Feeds(feedDTOs));
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * @see com.renren.x2.feed.storage.IFeedReaderStorage#getTopTenCurrent(int)
     */
    @Override
    public List<Feed> getTopTenCurrent(int schoolId) {
        List<Feed> topTen = new ArrayList<Feed>();
        TopTenDTO topTenDTO = topTenJadeDAO.getTopTenRecent(schoolId);

        if (null != topTenDTO) {

            int[] userIds = { topTenDTO.getTopOneUser(), topTenDTO.getTopTwoUser(),
                    topTenDTO.getTopThreeUser(), topTenDTO.getTopFourUser(),
                    topTenDTO.getTopFiveUser(), topTenDTO.getTopSixUser(),
                    topTenDTO.getTopSevenUser(), topTenDTO.getTopEightUser(),
                    topTenDTO.getTopNineUser(), topTenDTO.getTopTenUser() };
            long[] feedIds = { topTenDTO.getTopOne(), topTenDTO.getTopTwo(),
                    topTenDTO.getTopThree(), topTenDTO.getTopFour(), topTenDTO.getTopFive(),
                    topTenDTO.getTopSix(), topTenDTO.getTopSeven(), topTenDTO.getTopEight(),
                    topTenDTO.getTopNine(), topTenDTO.getTopTen() };

            for (int i = 0; i < 10; i++) {
                if ((0 < userIds[i]) && (0 < feedIds[i])) {
                    FeedDTO feedDTO = feedJadeDAO.getFeed(feedIds[i]);
                    if (null != feedDTO) {
                        topTen.add(FeedConvertor.convertDTO2Feed(feedDTO));
                    }
                }
            }
        }

        return topTen;

    }

    /*
     * (non-Javadoc)
     * @see com.renren.x2.feed.storage.IFeedReaderStorage#getTopTenHistory(int, int, int)
     */
    @Override
    public List<List<Feed>> getTopTenHistory(int schoolId, int offset, int limit) {
        List<List<Feed>> topTenList = new ArrayList<List<Feed>>();
        List<TopTenDTO> topTenDTOList = topTenJadeDAO.getTopTenHistory(schoolId, offset, limit);
        if ((null != topTenDTOList) && (topTenDTOList.size() > 0)) {
            for (TopTenDTO topTenDTO : topTenDTOList) {
                List<Feed> topTen = new ArrayList<Feed>();
                if (null != topTenDTO) {

                    int[] userIds = { topTenDTO.getTopOneUser(), topTenDTO.getTopTwoUser(),
                            topTenDTO.getTopThreeUser(), topTenDTO.getTopFourUser(),
                            topTenDTO.getTopFiveUser(), topTenDTO.getTopSixUser(),
                            topTenDTO.getTopSevenUser(), topTenDTO.getTopEightUser(),
                            topTenDTO.getTopNineUser(), topTenDTO.getTopTenUser() };
                    long[] feedIds = { topTenDTO.getTopOne(), topTenDTO.getTopTwo(),
                            topTenDTO.getTopThree(), topTenDTO.getTopFour(),
                            topTenDTO.getTopFive(), topTenDTO.getTopSix(), topTenDTO.getTopSeven(),
                            topTenDTO.getTopEight(), topTenDTO.getTopNine(), topTenDTO.getTopTen() };

                    for (int i = 0; i < 10; i++) {
                        if ((0 < userIds[i]) && (0 < feedIds[i])) {
                            FeedDTO feedDTO = feedJadeDAO.getFeed(feedIds[i]);
                            if (null != feedDTO) {
                                topTen.add(FeedConvertor.convertDTO2Feed(feedDTO));
                            }
                        }
                    }
                }
                if (topTen.size() > 0) {
                    topTenList.add(topTen);
                }
            }
        }
        return topTenList;
    }

    /*
     * (non-Javadoc)
     * @see com.renren.x2.feed.storage.IFeedReaderStorage#getHistoryHot(int, int)
     */
    @Override
    public List<Feed> getHistoryHotPost(int schoolId, int offset, int limit) {
        List<TopTenDTO> topTenDTOList = topTenJadeDAO.getTopTenHistory(schoolId, offset, limit);
        Map<Long, Integer> feedIdList = new HashMap<Long, Integer>();
        List<Feed> feedList = new ArrayList<Feed>();

        if ((null != topTenDTOList) && (topTenDTOList.size() > 0)) {
            for (TopTenDTO topTenDTO : topTenDTOList) {
                long[] feedIds = { topTenDTO.getTopOne(), topTenDTO.getTopTwo(),
                        topTenDTO.getTopThree(), topTenDTO.getTopFour(), topTenDTO.getTopFive(),
                        topTenDTO.getTopSix(), topTenDTO.getTopSeven(), topTenDTO.getTopEight(),
                        topTenDTO.getTopNine(), topTenDTO.getTopTen() };
                int[] userIds = { topTenDTO.getTopOneUser(), topTenDTO.getTopTwoUser(),
                        topTenDTO.getTopThreeUser(), topTenDTO.getTopFourUser(),
                        topTenDTO.getTopFiveUser(), topTenDTO.getTopSixUser(),
                        topTenDTO.getTopSevenUser(), topTenDTO.getTopEightUser(),
                        topTenDTO.getTopNineUser(), topTenDTO.getTopTenUser() };
                for (int i = 0; i < 10; i++) {
                    if ((0 < userIds[i]) && (0 < feedIds[i])) {
                        if (feedIdList.containsKey(feedIds[i])) {
                            feedIdList.put(feedIds[i], feedIdList.get(feedIds[i]) + 1);
                        } else {
                            feedIdList.put(feedIds[i], 1);
                        }
                    }
                }
            }
        }
        List<Map.Entry<Long, Integer>> mappingList = new ArrayList<Map.Entry<Long, Integer>>(
                feedIdList.entrySet());
        if (null != mappingList) {
            int mappingListLength = mappingList.size();
            if (0 < mappingListLength) {
                Collections.sort(mappingList, new Comparator<Map.Entry<Long, Integer>>() {

                    @Override
                    public int compare(Entry<Long, Integer> arg0, Entry<Long, Integer> arg1) {
                        int result = 0;
                        if (arg0.getValue() > arg1.getValue()) {
                            result = -1;
                        } else if (arg0.getValue() < arg1.getValue()) {
                            result = 1;
                        }
                        return result;
                    }
                });
            }
            for (int i = 0; i < mappingListLength; i++) {
                FeedDTO feedDTO = feedJadeDAO.getFeed(mappingList.get(i).getKey());
                if (null != feedDTO) {
                    feedList.add(FeedConvertor.convertDTO2Feed(feedDTO));
                }
            }
        }

        return feedList;

    }

    /*
     * (non-Javadoc)
     * @see com.renren.x2.feed.storage.IFeedReaderStorage#getHistoryHotUser(int, int, int)
     */
    @Override
    public Map<Integer, Integer> getHistoryHotUser(int schoolId, int offset, int limit) {
        Map<Integer, Integer> userScoreMap = new HashMap<Integer, Integer>();
        List<TopTenDTO> topTenDTOList = topTenJadeDAO.getTopTenHistory(schoolId, offset, limit);

        if ((null != topTenDTOList) && (topTenDTOList.size() > 0)) {
            for (TopTenDTO topTenDTO : topTenDTOList) {
                long[] feedIds = { topTenDTO.getTopOne(), topTenDTO.getTopTwo(),
                        topTenDTO.getTopThree(), topTenDTO.getTopFour(), topTenDTO.getTopFive(),
                        topTenDTO.getTopSix(), topTenDTO.getTopSeven(), topTenDTO.getTopEight(),
                        topTenDTO.getTopNine(), topTenDTO.getTopTen() };
                int[] userIds = { topTenDTO.getTopOneUser(), topTenDTO.getTopTwoUser(),
                        topTenDTO.getTopThreeUser(), topTenDTO.getTopFourUser(),
                        topTenDTO.getTopFiveUser(), topTenDTO.getTopSixUser(),
                        topTenDTO.getTopSevenUser(), topTenDTO.getTopEightUser(),
                        topTenDTO.getTopNineUser(), topTenDTO.getTopTenUser() };
                for (int i = 0; i < 10; i++) {
                    if ((0 < userIds[i]) && (0 < feedIds[i])) {
                        if (userScoreMap.containsKey(feedIds[i])) {
                            userScoreMap.put(userIds[i], userScoreMap.get(feedIds[i]) + 1);
                        } else {
                            userScoreMap.put(userIds[i], 1);
                        }
                    }
                }
            }
        }

        return userScoreMap;
    }

    /*
     * (non-Javadoc)
     * @see com.renren.x2.feed.storage.IFeedReaderStorage#getSchoolList()
     */
    @Override
    public List<Integer> getSchoolList() {
        return topTenJadeDAO.getSchoolIdList();
    }

    /**
     * @param feedJadeDAO the feedJadeDAO to set
     */
    public void setFeedJadeDAO(FeedJadeDAO feedJadeDAO) {
        this.feedJadeDAO = feedJadeDAO;
    }

    /**
     * @param postJadeDAO the postJadeDAO to set
     */
    public void setPostJadeDAO(PostJadeDAO postJadeDAO) {
        this.postJadeDAO = postJadeDAO;
    }

    /**
     * @param albumJadeDAO the albumJadeDAO to set
     */
    public void setAlbumJadeDAO(AlbumJadeDAO albumJadeDAO) {
        this.albumJadeDAO = albumJadeDAO;
    }

    /**
     * @param loveStatusJadeDAO the loveStatusJadeDAO to set
     */
    public void setLoveStatusJadeDAO(LoveStatusJadeDAO loveStatusJadeDAO) {
        this.loveStatusJadeDAO = loveStatusJadeDAO;
    }

    /**
     * @param personalVoiceJadeDAO the personalVoiceJadeDAO to set
     */
    public void setPersonalVoiceJadeDAO(PersonalVoiceJadeDAO personalVoiceJadeDAO) {
        this.personalVoiceJadeDAO = personalVoiceJadeDAO;
    }

    /**
     * @param commentJadeDAO the commentJadeDAO to set
     */
    public void setCommentJadeDAO(CommentJadeDAO commentJadeDAO) {
        this.commentJadeDAO = commentJadeDAO;
    }

    /**
     * @param likeJadeDAO the likeJadeDAO to set
     */
    public void setLikeJadeDAO(LikeJadeDAO likeJadeDAO) {
        this.likeJadeDAO = likeJadeDAO;
    }

    @Override
    public IFeedReaderStorage getFeedReaderStorage() {
        return this;
    }

    /**
     * @param topTenJadeDAO the topTenJadeDAO to set
     */
    public void setTopTenJadeDAO(TopTenJadeDAO topTenJadeDAO) {
        this.topTenJadeDAO = topTenJadeDAO;
    }

}

class FeedIdComparator implements Comparator<Feed> {

    @Override
    public int compare(Feed o1, Feed o2) {
        return (int) -(o1.getFeedId() - o2.getFeedId());
    }

}
