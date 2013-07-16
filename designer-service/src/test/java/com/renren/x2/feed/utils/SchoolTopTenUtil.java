package com.renren.x2.feed.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.renren.x2.feedapi.model.Feed;

/**
 * 
 * @author <a href="mailto:wei.cui@renren-inc.com">崔巍</a>
 * @version 2012-12-6 下午4:20:37
 */
public class SchoolTopTenUtil {

    //long型时间中60000表示一分钟时间长度
    private static long ONE_MINUTE = 60000;

    //时间递减函数中的指数
    private static double POWER_IN_MINUTE = 1.2;

    //评论阈值
    private static int COMMENT_MIN = 5;

    //喜欢阈值
    private static int LIKE_MIN = 5;

    //小时尺度
    private static long ONE_HOUR = 60 * ONE_MINUTE;

    //时间递减函数中的指数
    private static double POWER = 2.26482;

    //喜欢与评论的比值
    private static double LIKE_VALUE_TO_COMMENT = 2;

    private static final Logger logger = Logger.getLogger(SchoolTopTenUtil.class);

    /**
     * 混合老的十大和最近的Feed，计算分数，排序，输出得分前10的Feed，如果Feed不足，全部输出
     * 
     * @param oldTopTen 上一期的十大Feed
     * @param recentFeeds 最近的Feed
     * @return 新十大Feed
     */
    public static List<Feed> getSchoolTopTen(List<Feed> oldTopTen, List<Feed> recentFeeds) {

        List<Feed> topTen = new ArrayList<Feed>();
        Map<Long, Feed> feedMap = new HashMap<Long, Feed>();
        Map<Long, Double> scoreMap = new HashMap<Long, Double>();
        Date now = new Date();
        long nowValue = now.getTime();

        if (null != oldTopTen) {
            for (Feed feed : oldTopTen) {
                if (null != feed) {
                    if (null == scoreMap.get(feed.getFeedId())) {
                        double score = getFeedScore(feed, nowValue);
                        scoreMap.put(feed.getFeedId(), score);
                        feedMap.put(feed.getFeedId(), feed);
                    }
                }
            }
        }

        if (null != recentFeeds) {
            for (Feed feed : recentFeeds) {
                if (null != feed) {
                    if (null == scoreMap.get(feed.getFeedId())) {
                        if ((COMMENT_MIN <= feed.getCommentsCount())
                                || (LIKE_MIN <= (feed.getFLikesCount() + feed.getMLikesCount()))) {
                            double score = getFeedScore(feed, nowValue);
                            scoreMap.put(feed.getFeedId(), score);
                            feedMap.put(feed.getFeedId(), feed);
                        }
                    }
                }
            }
        }

        List<Map.Entry<Long, Double>> mappingList = new ArrayList<Map.Entry<Long, Double>>(
                scoreMap.entrySet());

        if (null != mappingList) {

            int mappingListLength = mappingList.size();
            if (0 < mappingListLength) {
                Collections.sort(mappingList, new Comparator<Map.Entry<Long, Double>>() {

                    @Override
                    public int compare(Entry<Long, Double> arg0, Entry<Long, Double> arg1) {
                        int result = 0;
                        if (arg0.getValue() > arg1.getValue()) {
                            result = -1;
                        } else if (arg0.getValue() < arg1.getValue()) {
                            result = 1;
                        }
                        return result;
                    }
                });

                if (10 > mappingListLength) {
                    for (int i = 0; i < mappingListLength; i++) {
                        topTen.add(feedMap.get(mappingList.get(i).getKey()));
                    }
                } else {
                    for (int i = 0; i < 10; i++) {
                        topTen.add(feedMap.get(mappingList.get(i).getKey()));
                    }
                }
            }
        }

        feedMap.clear();
        scoreMap.clear();

        return topTen;
    }

    /**
     * 获得Feed的分数，（2*喜欢数＋评论数）*时间递减函数
     * 
     * @param feed Feed对象
     * @param time 计算的时间基点
     * @return Feed的分数
     */
    public static double getFeedScore(Feed feed, long time) {
        double score = 0.0;
        if (null != feed) {
            int commentNum = feed.getCommentsCount();
            int likeNum = feed.getFLikesCount() + feed.getMLikesCount();
            long createTime = feed.getCreateTime();
            score = (LIKE_VALUE_TO_COMMENT * likeNum + commentNum)
                    * timeFunction(time - createTime);
        }
        return score;
    }

    /**
     * 时间递减函数，分钟为单位，Power(2+时间长度,-1.2)
     * 
     * @param timeParam 毫秒单位的时间长度
     * @return 函数值
     */
    public static double timeFunctionOrigin(long timeParam) {
        if (timeParam < 0) {
            return 0.0;
        } else {
            return Math.pow(2 + timeParam / ONE_MINUTE, -POWER_IN_MINUTE);
        }
    }

    /**
     * 时间递减函数，小时为单位，Power(2+时间长度,指数)
     * 
     * POWER = 2.26482
     * 
     * 1/10时间：3.5小时； 1/100时间：13.3小时； 1/1000时间：40小时； 1/10000时间：114小时
     * 
     * 24小时剩余量：3/1000（统计小概率）
     * 
     * @param timeParam 毫秒单位的时间长度
     * @return 函数值
     */
    public static double timeFunction(long timeParam) {
        if (timeParam < 0) {
            return 0.0;
        } else {
            return Math.pow(2 + ((double) timeParam) / ONE_HOUR, -POWER);
        }
    }

}
