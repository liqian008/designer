package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.model.User;
import com.bruce.designer.model.UserFans;
import com.bruce.designer.model.UserFollow;

public interface IUserGraphService{
    
    /**
     * 是否关注过
     * @param uid1
     * @param uid2
     * @return
     */
    public boolean isFollow(int uid1, int uid2);

    /**
     * 加关注
     * 
     * @param uid
     * @param followId
     * @return
     */
    public boolean follow(int uid, int followId);

    /**
     * 取消关注
     * 
     * @param uid
     * @param unfollowId
     * @return
     */
    public boolean unfollow(int uid, int unfollowId);

    /**
     * 获取关注列表
     * 
     * @param id
     * @return
     */
    public List<UserFollow> getFollowList(int uid);

    /**
     * 获取关注列表
     * 
     * @param id
     * @return
     */
    public List<UserFollow> getFollowList(int uid, int page, int pageSize);

    /**
     * 获取关注列表
     * 
     * @param id
     * @return
     */
    public List<UserFollow> getFollowListWithUser(int uid, int page, int pageSize);

    /**
     * 获取粉丝列表
     * 
     * @param id
     * @return
     */
    public List<UserFans> getFansList(int uid);

    /**
     * 获取粉丝列表
     * 
     * @param id
     * @return
     */
    public List<UserFans> getFansList(int uid, int page, int pageSize);

    /**
     * 获取粉丝列表
     * 
     * @param id
     * @return
     */
    public List<UserFans> getFansListWithUser(int uid, int page, int pageSize);

    /**
     * 获取共同关注
     * 
     * @param uid1
     * @param uid2
     * @return
     */
    public List<Integer> getBothFollowList(int uid1, int uid2);

    /**
     * 获取共同关注
     * 
     * @param uid1
     * @param uid2
     * @return
     */
    public List<Integer> getBothFollowList(int uid1, int uid2, int page,
            int pageSize);

    /**
     * 获取共同关注
     * 
     * @param uid1
     * @param uid2
     * @return
     */
    public List<User> getBothFollowUserList(int uid1, int uid2, int page,
            int pageSize);

//    /**
//     * 获取互相关注
//     * 
//     * @param uid1
//     * @return
//     */
//    public List<Integer> getFriendList(int uid1);
//
//    /**
//     * 获取互相关注
//     * 
//     * @param uid1
//     * @return
//     */
//    public List<Integer> getFriendList(int uid1, int page, int pageSize);
//
//    /**
//     * 获取互相关注
//     * 
//     * @param uid1
//     * @return
//     */
//    public List<User> getFriendUserList(int uid1, int page, int pageSize);

//    /**
//     * 
//     * @param uid1
//     * @param uid2
//     * @return
//     */
//    public List<Integer> getFollowListWhoFollowTo(int uid1, int uid2);
//
//    /**
//     * 
//     * @param uid1
//     * @param uid2
//     * @return
//     */
//    public List<Integer> getFollowListWhoFollowTo(int uid1, int uid2, int page,
//            int pageSize);
//
//    /**
//     * 
//     * @param uid1
//     * @param uid2
//     * @return
//     */
//    public List<Integer> getFollowUserListWhoFollowTo(int uid1, int uid2,
//            int page, int pageSize);

    /**
     * 获取关注数
     * 
     * @param uid
     * @return
     */
    public long getFollowCount(int uid);

    /**
     * 获取粉丝数
     * 
     * @param uid
     * @return
     */
    public long getFansCount(int uid);

}
