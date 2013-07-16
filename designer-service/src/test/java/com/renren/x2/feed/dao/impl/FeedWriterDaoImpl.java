/**
 * $Id$
 * Copyright 2009-2010 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.dao.impl;

import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.renren.x2.feed.beans.AlbumDTO;
import com.renren.x2.feed.beans.CommentDTO;
import com.renren.x2.feed.beans.FeedDTO;
import com.renren.x2.feed.beans.LikeDTO;
import com.renren.x2.feed.beans.LoveStatusDTO;
import com.renren.x2.feed.beans.PersonalVoiceDTO;
import com.renren.x2.feed.beans.PostDTO;
import com.renren.x2.feed.beans.TopTenDTO;
import com.renren.x2.feed.convertor.FeedConvertor;
import com.renren.x2.feed.jade.dao.AlbumJadeDAO;
import com.renren.x2.feed.jade.dao.CommentJadeDAO;
import com.renren.x2.feed.jade.dao.FeedJadeDAO;
import com.renren.x2.feed.jade.dao.LikeJadeDAO;
import com.renren.x2.feed.jade.dao.LoveStatusJadeDAO;
import com.renren.x2.feed.jade.dao.PersonalVoiceJadeDAO;
import com.renren.x2.feed.jade.dao.PostJadeDAO;
import com.renren.x2.feed.jade.dao.TopTenJadeDAO;
import com.renren.x2.feed.storage.IFeedWriterStorage;
import com.renren.x2.feed.utils.UgcStatusUtils;
import com.renren.x2.feedapi.constants.ExtUgcType;
import com.renren.x2.feedapi.constants.NativeUgcType;
import com.renren.x2.feedapi.model.Feed;
import com.renren.x2.feedapi.model.Ugc;

/**
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2013-1-4 上午11:48:49
 */
public class FeedWriterDaoImpl implements IFeedWriterStorage {

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

private static final Logger logger = Logger.getLogger(FeedWriterDaoImpl.class);

    @Override
    public void saveFeed(int schoolId, Feed feed) {
        saveNativeUgc(feed.getFeedId(), feed.getNativeUgc(), feed.getNativeUgcType());
        FeedDTO feedDTO = FeedConvertor.convertFeed2DTO(schoolId, feed);
        long shardId = feed.getFeedId();
        feedJadeDAO.insertFeed(shardId, feedDTO);
    }

    protected void saveNativeUgc(long feedId, Ugc ugc, NativeUgcType ugcType) {
        int userId = ugc.getUserProfile().getUserId();
        switch (ugcType) {
            case Posts:
                PostDTO postDTO = FeedConvertor.convertUgc2DTO(feedId, ugc, PostDTO.class);
                postJadeDAO.insertPost(userId, postDTO);
                break;
            case Album:
                AlbumDTO albumDTO = FeedConvertor.convertUgc2DTO(feedId, ugc, AlbumDTO.class);
                albumJadeDAO.insertAlbum(userId, albumDTO);
                break;
            case LoveStatus:
                LoveStatusDTO loveStatusDTO = FeedConvertor.convertUgc2DTO(feedId, ugc,
                        LoveStatusDTO.class);
                loveStatusJadeDAO.insertLoveStatus(userId, loveStatusDTO);
                break;
            case PersonalVoice:
                PersonalVoiceDTO personalVoiceDTO = FeedConvertor.convertUgc2DTO(feedId, ugc,
                        PersonalVoiceDTO.class);
                personalVoiceJadeDAO.insertPersonalVoice(userId, personalVoiceDTO);
                break;
        }
    }

    @Override
    public void saveExtUgc(long feedId, Ugc ugc, ExtUgcType ugcType) {
        switch (ugcType) {
            case Comment:
                CommentDTO commentDTO = FeedConvertor.convertUgc2DTO(feedId, ugc, CommentDTO.class);
                commentJadeDAO.insertComment(feedId, commentDTO);
                break;
            case FLike:
                LikeDTO flikeDTO = FeedConvertor.convertUgc2DTO(feedId, ugc, LikeDTO.class);
                likeJadeDAO.insertFLike(feedId, flikeDTO);
                break;
            case MLike:
                LikeDTO mlikeDTO = FeedConvertor.convertUgc2DTO(feedId, ugc, LikeDTO.class);
                likeJadeDAO.insertMLike(feedId, mlikeDTO);
                break;
            default:
                break;
        }
    }

    @Override
    public void addSchoolFeedIndex(int schoolId, long feedId) {
        feedJadeDAO.insertSchoolFeedIndex(schoolId, feedId);
    }

    @Override
    public void addUserFeedIndex(int userId, long feedId) {
        //do nothing
    }

    @Override
    public void addUserAlbumFeedIndex(int userId, long feedId) {
        //do nothing
    }

    @Override
    public void addUserFollowerFeedIndex(int userId, long feedId) {
        //do nothing
    }

    @Override
    public void addUserFollowerFeedIndex(int userId, List<Long> feedIds) {
        //do nothing
    }

    @Override
    public void addUserLikeFeedIndex(int userId, long feedId, long ugcId) {
        //do nothing
    }

    @Override
    public void addFeedCommentIndex(long feedId, long ugcId) {
        //do nothing
    }

    @Override
    public void addFeedFLikeIndex(long feedId, long ugcId) {
        //do nothing
    }

    @Override
    public void addFeedMLikeIndex(long feedId, long ugcId) {
        //do nothing
    }

    @Override
    public void removeUserLikeFeedIndex(int userId, long feedId) {
        //do nothing
    }

    @Override
    public void removeUserFollowerFeedIndex(int userId, List<Long> feedIds) {
        //do nothing
    }

    @Override
    public void incrFeedFLike(long feedId) {
        feedJadeDAO.incrFeedFLike(feedId, System.currentTimeMillis());
    }

    @Override
    public void decrFeedFLike(long feedId) {
        feedJadeDAO.decrFeedFLike(feedId, System.currentTimeMillis());
    }

    @Override
    public void incrFeedMLike(long feedId) {
        feedJadeDAO.incrFeedMLike(feedId, System.currentTimeMillis());
    }

    @Override
    public void decrFeedMLike(long feedId) {
        feedJadeDAO.decrFeedMLike(feedId, System.currentTimeMillis());
    }

    @Override
    public void incrFeedComment(long feedId) {
        feedJadeDAO.incrFeedComment(feedId, System.currentTimeMillis());
    }

    @Override
    public void deleteFeed(long feedId) {
        FeedDTO feedDTO = feedJadeDAO.getFeed(feedId);
        if (feedDTO != null) {
            long ugcId = feedDTO.getNativeUgcId();
            int userId = feedDTO.getUserId();
            postJadeDAO.updatePost(userId, ugcId, UgcStatusUtils.STATUS_DELETE,
                    System.currentTimeMillis());
        }
    }

    @Override
    public void deleteExtUgc(long feedId, long ugcId, ExtUgcType ugcType) {
        switch (ugcType) {
            case Comment:
                commentJadeDAO.updateComment(feedId, ugcId, UgcStatusUtils.STATUS_DELETE,
                        System.currentTimeMillis());
                break;
            case FLike:
                likeJadeDAO.updateFLike(feedId, ugcId, UgcStatusUtils.STATUS_DELETE,
                        System.currentTimeMillis());
            case MLike:
                likeJadeDAO.updateMLike(feedId, ugcId, UgcStatusUtils.STATUS_DELETE,
                        System.currentTimeMillis());
            default:
                break;
        }
    }

    @Override
    public void updateFeed(Feed feed) {
        FeedDTO feedDTO = FeedConvertor.convertFeed2DTO(0, feed);
        switch (feed.getNativeUgcType()) {
            case Album:
                feedJadeDAO.updateFeed(feed.getFeedId(), feedDTO);
                AlbumDTO albumDTO = FeedConvertor.convertUgc2DTO(feed.getFeedId(),
                        feed.getNativeUgc(), AlbumDTO.class);
                albumJadeDAO.updateAlbum(albumDTO.getUserId(), albumDTO);
                break;
            default:
                break;
        }
    }

    @Override
    public void updateSchoolTopTen(int schoolId, List<Long> feedIdList, List<Integer> userIdList) {
        TopTenDTO topTenDTO = new TopTenDTO();
        topTenDTO.setSchoolId(schoolId);
        int[] userIds = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        long[] feedIds = { 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L };
        if ((null != feedIdList) && (null != userIdList)) {
	logger.debug("schoolId : " + schoolId + " feedId : " + feedIdList.get(0));
            int length = (feedIdList.size() > userIdList.size()) ? userIdList.size() : feedIdList
                    .size();
            if (length > 0) {
                if (length > 10) {
                    length = 10;
                }
                for (int i = 0; i < length; i++) {
                    userIds[i] = userIdList.get(i);
                    feedIds[i] = feedIdList.get(i);
                }
            }
        }
        topTenDTO.setTopOne(feedIds[0]);
        topTenDTO.setTopOneUser(userIds[0]);

        topTenDTO.setTopTwo(feedIds[1]);
        topTenDTO.setTopTwoUser(userIds[1]);

        topTenDTO.setTopThree(feedIds[2]);
        topTenDTO.setTopThreeUser(userIds[2]);

        topTenDTO.setTopFour(feedIds[3]);
        topTenDTO.setTopFourUser(userIds[3]);

        topTenDTO.setTopFive(feedIds[4]);
        topTenDTO.setTopFiveUser(userIds[4]);

        topTenDTO.setTopSix(feedIds[5]);
        topTenDTO.setTopSixUser(userIds[5]);

        topTenDTO.setTopSeven(feedIds[6]);
        topTenDTO.setTopSevenUser(userIds[6]);

        topTenDTO.setTopEight(feedIds[7]);
        topTenDTO.setTopEightUser(userIds[7]);

        topTenDTO.setTopNine(feedIds[8]);
        topTenDTO.setTopNineUser(userIds[8]);

        topTenDTO.setTopTen(feedIds[9]);
        topTenDTO.setTopTenUser(userIds[9]);

        long dateValue = (new Date()).getTime();

        if (schoolTopTenIsExist(schoolId)) {
            topTenDTO.setUpdateTime(dateValue);
logger.debug("update");
            topTenJadeDAO.updateTopTenRecent(schoolId, topTenDTO);
        } else {
	logger.debug("add");
            topTenDTO.setCreateTime(dateValue);
            topTenDTO.setUpdateTime(dateValue);
            topTenJadeDAO.addTopTenRecent(schoolId, topTenDTO);
        }

        topTenJadeDAO.addTopTenHistory(schoolId, topTenDTO);

    }

    public boolean schoolTopTenIsExist(int schoolId) {
        if (null == topTenJadeDAO.getTopTenRecent(schoolId)) {
            return false;
        } else {
            return true;
        }
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

    /**
     * @param topTenJadeDAO the topTenJadeDAO to set
     */
    public void setTopTenJadeDAO(TopTenJadeDAO topTenJadeDAO) {
        this.topTenJadeDAO = topTenJadeDAO;
    }

}
