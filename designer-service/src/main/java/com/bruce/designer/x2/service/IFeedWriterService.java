package com.renren.x2.feed.service;

import com.renren.x2.common.BaseResult;
import com.renren.x2.feed.exception.FeedException;
import com.renren.x2.feedapi.model.Ugc;

/**
 * feed系统中所有写操作
 * 
 * @author <a href="mailto:chuang.zhang@renren-inc.com">张闯</a>
 * @version 2012-12-29 下午2:49:12
 */
public interface IFeedWriterService {

    /*************************************** 添加ugc *****************************************/
    /***
     * 发帖
     * 
     * @param userId 当前用户的用户id
     * @param schoolId 当前用户所在学校id
     * @param ugc 帖子ugc内容
     * @param publishRenn 1为分享到人人
     * @return
     * @throws FeedException
     */
    public BaseResult postPosts(int userId, int schoolId, Ugc ugc, int publishRenn)
            throws FeedException;

    /***
     * 发相册
     * 
     * @param userId 当前用户用户id
     * @param ugc 相册的ugc内容
     * @return
     * @throws FeedException
     */
    public BaseResult postAlbum(int userId, Ugc ugc) throws FeedException;

    /***
     * 发情感状态
     * 
     * @param userId 当前用户用户id
     * @param ugc 情感状态的ugc内容
     * @return
     * @throws FeedException
     */
    public BaseResult postLoveStatus(int userId, Ugc ugc) throws FeedException;

    /***
     * 发主页语音
     * 
     * @param userId 当前用户用户id
     * @param ugc 主页语音的ugc内容
     * @return
     * @throws FeedException
     */
    public BaseResult postPersonalVoice(int userId, Ugc ugc) throws FeedException;

    /***
     * 发回复
     * 
     * @param userId 当前用户的用户id
     * @param feedId 回复的feedId
     * @param ugc 回复的ugc内容
     * @return
     * @throws FeedException
     */
    public BaseResult postComment(int userId, long feedId, Ugc ugc) throws FeedException;

    /***
     * 发赞
     * 
     * @param userId 当前用户的用户id
     * @param feedId 赞的feedId
     * @return
     * @throws FeedException
     */
    public BaseResult postLike(int userId, long feedId) throws FeedException;

    /*************************************** 更新ugc *****************************************/
    /***
     * 设置相册为默认头像
     * 
     * @param userId 当前用户用户id
     * @param photoId 默认头像id
     * @return
     * @throws FeedException
     */
    public BaseResult updateCurrentAlbum(int userId, int photoId) throws FeedException;

    /********************************** 删除ugc *******************************************/
    /***
     * 删除帖子
     * 
     * @param userId 当前用户id
     * @param ownerId 帖子所有者id
     * @param ugcId 帖子ugcId
     * @return
     * @throws FeedException
     */
    public BaseResult deletePosts(int userId, long feedId, long ugcId) throws FeedException;

    /***
     * 删除相册
     * 
     * @param userId 当前用户id
     * @param ownerId 相册所有者id
     * @param ugcId 相册ugcId
     * @return
     * @throws FeedException
     */
    public BaseResult deleteAlbum(int userId, long feedId, long ugcId) throws FeedException;

    /***
     * 删除回复
     * 
     * @param userId 当前用户id
     * @param ownerId 所在新鲜事所有者的用户id
     * @param feedId 所在新鲜事id
     * @param ugcId 回复的ugcId
     * @return
     * @throws FeedException
     */
    public BaseResult deleteComment(int userId, long feedId, long ugcId) throws FeedException;

    /***
     * 删除赞
     * 
     * @param userId 当前用户id
     * @param ownerId 所在新鲜事所有者的用户id
     * @param feedId 所在新鲜事id
     * @return
     * @throws FeedException
     */
    public BaseResult deleteLike(int userId, long feedId) throws FeedException;

    public BaseResult addFollowerEvent(int userId, int flowerId);

    public BaseResult deleteFollowerEvent(int userId, int flowerId);

    /**
     * 更新校园十大新鲜事
     * 
     * @param schoolId
     * @param limit
     * @return
     */
    public BaseResult updateSchoolTopTen(int schoolId, int limit);
}
