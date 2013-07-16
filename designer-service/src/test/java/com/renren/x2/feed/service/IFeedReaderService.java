package com.renren.x2.feed.service;

import com.renren.x2.feed.exception.FeedException;
import com.renren.x2.feedapi.result.FeedListResult;
import com.renren.x2.feedapi.result.FeedResult;
import com.renren.x2.feedapi.result.UgcListResult;


/**
 * feed系统中所有读操作
 * 
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2012-12-29 下午2:51:59
 */
public interface IFeedReaderService {
    /********************************** 取原生ugc *******************************************/
    /***
     * 按照id取feed实体
     * 
     * @param usreId 当前用户用户id
     * @param feedId 新鲜事id
     * @return
     * @throws FeedException
     */
    public FeedResult getFeedById(int usreId, long feedId) throws FeedException;

    /***
     * 取学校视图的新鲜事列表
     * 
     * @param userId 当前用户的用户id
     * @param schoolId 当前视图学校id
     * @param feedId 边界新鲜事id
     * @param forward 取的方向，true为旧（正向），false为新（反向）
     * @param size 分页大小
     * @param offset 位移，true为旧（正向），false为新（反向）
     * @return
     * @throws FeedException
     */
    public FeedListResult getSchoolFeeds(int userId, int schoolId, long feedId, boolean forward,
            int size, int offset) throws FeedException;

    /***
     * 取user视图的新鲜事列表
     * 
     * @param userId 当前用户的用户id
     * @param ownerId 新鲜事所有者的用户id
     * @param feedId 边界新鲜事id
     * @param forward 取的方向，true为旧（正向），false为新（反向）
     * @param size 分页大小
     * @param offset 位移，true为旧（正向），false为新（反向）
     * @return
     * @throws FeedException
     */
    public FeedListResult getUserFeeds(int userId, int ownerId, long feedId, boolean forward,
            int size, int offset) throws FeedException;

    /***
     * 取follower视图的新鲜事列表
     * 
     * @param userId 当前用户的用户id
     * @param feedId 边界新鲜事id
     * @param forward 取的方向，true为旧（正向），false为新（反向）
     * @param size 分页大小
     * @param offset 位移，true为旧（正向），false为新（反向）
     * @return
     * @throws FeedException
     */
    public FeedListResult getUserFollowerFeeds(int userId, long feedId, boolean forward, int size,
            int offset) throws FeedException;

    /***
     * 取user相册视图的新鲜事列表（相册列表）
     * 
     * @param userId
     * @param ownerId
     * @return
     * @throws FeedException
     */
    public FeedListResult getUserAlbumsFeeds(int userId, int ownerId) throws FeedException;

    /********************************** 取衍生ugc *******************************************/
    /***
     * 取回复列表
     * 
     * @param userId 当前用户的用户id
     * @param feedId 新鲜事id
     * @param ugcId 边界回复id
     * @param forward 取的方向，true为旧（正向），false为新（反向）
     * @param size 分页大小
     * @param offset 位移，true为旧（正向），false为新（反向）
     * @return
     */
    public UgcListResult getComments(int userId, long feedId, long ugcId,
            boolean forward, int size, int offset);

    /***
     * 取女like列表
     * 
     * @param userId 当前用户的用户id
     * @param feedId 新鲜事id
     * @param size 大小，不分页，系统会限制最大值
     * @return
     */
    public UgcListResult getFLikes(int userId, long feedId, int size);

    /***
     * 取男like列表
     * 
     * @param userId 当前用户的用户id
     * @param feedId 新鲜事id
     * @param size 大小，不分页，系统会限制最大值
     * @return
     */
    public UgcListResult getMLikes(int userId, long feedId, int size);


    /**
     * 校园十大
     * 
     * @param schoolId
     * @return
     */
    public FeedListResult getSchoolTopTen(int schoolId);

    /**
     * 校园十大第二版
     * 
     * @param schoolId
     * @return
     * @throws FeedException
     */
    public FeedListResult getSchoolTopTen(int userId, int schoolId) throws FeedException;
}
