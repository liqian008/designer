package com.bruce.designer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.bruce.designer.cache.user.FansCache;
import com.bruce.designer.cache.user.FollowCache;
import com.bruce.designer.constants.ConstRedis;
import com.bruce.designer.exception.RedisKeyNotExistException;
import com.bruce.designer.model.User;
import com.bruce.designer.model.UserFans;
import com.bruce.designer.model.UserFollow;
import com.bruce.designer.service.ICounterService;
import com.bruce.designer.service.IUserFansService;
import com.bruce.designer.service.IUserFollowService;
import com.bruce.designer.service.IUserGraphService;
import com.bruce.designer.service.IUserService;

@Service
public class UserGraphServiceImpl implements IUserGraphService, InitializingBean {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(UserGraphServiceImpl.class);
    
    private static final int FANS_CACHE_MAX_COUNT = 5000;
    
    private static final long MULTI_GET_FOLLOW_TIMEOUT = 500;

//    private static final String FOLLOW_COUNT_PREFIX = "follow_count_";
//
//    private static final String FANS_COUNT_PREFIX = "fans_count_";
//
//    private static final String FRIEND_COUNT_PREFIX = "friend_count_";
//
//  private FriendCache friendCache;

    
    @Autowired
    private IUserFollowService followService;
    @Autowired
    private IUserFansService fansService;
    @Autowired
    private FollowCache followCache;
    @Autowired
    private FansCache fansCache;
    @Autowired
    private ICounterService counterService;
    @Autowired
    private IUserService userService;

//    private ExecutorService executorService = new ThreadPoolExecutor(24, 24, 5, TimeUnit.MINUTES,
//            new LinkedBlockingQueue<Runnable>(1000));

    @Override
    public List<UserFollow> getFollowList(int uid) {
        List<UserFollow> followList;
        try {
            followList = followCache.getAllFollowList(uid);
        } catch (Exception e) {
            followList = followService.getFollowList(uid);
            followCache.setFollowList(uid, followList);
//            friendCache.setFriendList(uid, followList);
        }
        return followList;
    }

    @Override
    public List<UserFollow> getFollowList(int uid, int page, int pageSize) {
        List<UserFollow> followList = getFollowList(uid);
        if (followList != null && followList.size() > 0) {
            int size = followList.size();
            int fromIndex = page * pageSize;
            int toIndex = (page + 1) * pageSize;
            if (size > fromIndex) {
                toIndex = toIndex > size ? size : toIndex;
                return followList.subList(fromIndex, toIndex);
            }
        }
        return new ArrayList<UserFollow>();
    }

    @Override
    public List<UserFollow> getFollowListWithUser(int uid, int page, int pageSize) {
        List<UserFollow> followList = getFollowList(uid, page, pageSize);
//        List<Integer> userIdList = new ArrayList<Integer>();
//        userIdList.add(uid);
//        for (UserFollow follow : followList) {
//            userIdList.add(follow.getFollowId());
//        }
//
//        Map<Integer, User> userMap = userService.getUserMap(userIdList);
//        User user = userMap.get(uid);
//
//        for (UserFollow follow : followList) {
//            follow.setUser(user);
//            follow.setFollowUser(userMap.get(follow.getFollowId()));
//        }

        return followList;
    }

    @Override
    public boolean isFollow(int uid, int followId) {
        if (uid == followId) {
            return false;
        }
        boolean isFollow = false;
        try {
            isFollow = followCache.isFollowed(uid, followId);
        } catch (Exception e) {
            List<UserFollow> followList = getFollowList(uid);
            followCache.setFollowList(uid, followList);
//            friendCache.setFriendList(uid, followList);
            if (followList != null) {
                for (UserFollow follow : followList) {
                    if (follow.getFollowId() == followId) {
                        isFollow = true;
                        break;
                    }
                }
            }
        }
        return isFollow;
    }

    @Override
    public boolean follow(int uid, int followId) {
        if (uid == followId) {
            return false;
        }
        //检测是否follow
        boolean isFollow = isFollow(uid, followId);
        if (isFollow) {
            return false;
        } else {
//            boolean isFriend = isFollow(followId, uid);

            Date currentTime = new Date(System.currentTimeMillis());
            //添加关注
            UserFollow follow = new UserFollow();
            follow.setUserId(uid);
            follow.setFollowId(followId);
            follow.setCreateTime(currentTime);

            if (followService.save(follow) > 0) {
                try {
                    followCache.addFollow(follow);
//                    if (isFriend) {
//                        friendCache.addFriend(follow);
//                    }
                } catch (RedisKeyNotExistException e) {
                    List<UserFollow> followList = followService.getFollowList(uid);
                    followCache.setFollowList(uid, followList);
//                    friendCache.setFriendList(uid, followList);
                }
                //增加follow计数
                counterService.increase(ConstRedis.COUNTER_KEY_FOLLOW + uid);
            } else {
                //TODO添加失败队列修复
            }

            //添加粉丝
            UserFans fans = new UserFans();
            fans.setUserId(followId);
            fans.setFansId(uid);
            fans.setCreateTime(currentTime);
            
            if (fansService.save(fans) > 0) {
                try {
                    fansCache.addFans(fans);
                } catch (Exception e) {
                    List<UserFans> fansList = fansService.getFansList(followId, FANS_CACHE_MAX_COUNT);
                    fansCache.setFansList(followId, fansList);
                }
                //增加粉丝计数
                counterService.increase(ConstRedis.COUNTER_KEY_FANS + followId);
            } else {
                //TODO添加失败队列修复
            }
            return true;
        }
    }

    @Override
    public boolean unfollow(int uid, int unfollowId) {
        if (uid == unfollowId) {
            return false;
        }
        boolean isFollow = isFollow(uid, unfollowId);
        if (isFollow) {
            //boolean isFriend = isFollow(unfollowId, uid);
            try {
                //删除关注
                if (followService.deleteFollow(uid, unfollowId) > 0) {
                    //删cache减少计数
                    followCache.removeFollow(uid, unfollowId);
                    counterService.reduce(ConstRedis.COUNTER_KEY_FOLLOW + uid);
                } else {
                    //TODO 队列修复
                } 

                //删除粉丝
                if (fansService.deleteFans(unfollowId, uid) > 0) {
                    fansCache.removeFans(unfollowId, uid);
                    counterService.reduce(ConstRedis.COUNTER_KEY_FANS + uid);
                } else {
                    //TODO 队列修复
                }

                return true;
            } catch (Exception e) {
                logger.error(e);
            }

        } else {
            return false;
        }
        return false;
    }

    @Override
    public List<UserFans> getFansList(int uid) {
        List<UserFans> fansList;
        try {
            fansList = fansCache.getAllFansList(uid);
        } catch (Exception e) {
            fansList = fansService.getFansList(uid, FANS_CACHE_MAX_COUNT);
            fansCache.setFansList(uid, fansList);
        }
        return fansList;
    }

    @Override
    public List<UserFans> getFansList(int uid, int page, int pageSize) {
        List<UserFans> fansList = getFansList(uid);
        if (fansList != null && fansList.size() > 0) {
            int size = fansList.size();
            int fromIndex = page * pageSize;
            int toIndex = (page + 1) * pageSize;
            if (size > fromIndex) {
                toIndex = toIndex > size ? size : toIndex;
                return fansList.subList(fromIndex, toIndex);
            }
        }
        return new ArrayList<UserFans>();
    }

    @Override
    public List<UserFans> getFansListWithUser(int uid, int page, int pageSize) {
        List<UserFans> fansList = getFansList(uid, page, pageSize);
//        List<Integer> userIdList = new ArrayList<Integer>();
//        userIdList.add(uid);
//        for (UserFans fans : fansList) {
//            userIdList.add(fans.getFansId());
//        }
//
//        Map<Integer, User> userMap = userService.getUserMap(userIdList);
//        User user = userMap.get(uid);
//
//        for (UserFans fans : fansList) {
//            fans.setUser(user);
//            fans.setFansUser(userMap.get(fans.getFansId()));
//        }

        return fansList;
    }

    @Override
    public List<Integer> getBothFollowList(int uid1, int uid2) {
        List<UserFollow> followList = getFollowList(uid1);
        List<UserFollow> followList2 = getFollowList(uid2);
        if (followList == null || followList.size() == 0 || followList2 == null
                || followList2.size() == 0) {
            return new ArrayList<Integer>();
        }
        Set<Integer> set1 = new HashSet<Integer>();
        for (UserFollow follow : followList) {
            set1.add(follow.getFollowId());
        }
        Set<Integer> set2 = new HashSet<Integer>();
        for (UserFollow follow : followList2) {
            set2.add(follow.getFollowId());
        }
        @SuppressWarnings("unchecked")
        List<Integer> both = (List<Integer>) CollectionUtils.intersection(set1, set2);

        return both;
    }

    @Override
    public List<Integer> getBothFollowList(int uid1, int uid2, int page, int pageSize) {
        List<Integer> bothFollowList = getBothFollowList(uid1, uid2);
        if (bothFollowList != null && bothFollowList.size() > 0) {
            int size = bothFollowList.size();
            int fromIndex = page * pageSize;
            int toIndex = (page + 1) * pageSize;
            if (size > fromIndex) {
                toIndex = toIndex > size ? size : toIndex;
                return bothFollowList.subList(fromIndex, toIndex);
            }
        }
        return new ArrayList<Integer>();
    }

    @Override
    public List<User> getBothFollowUserList(int uid1, int uid2, int page, int pageSize) {
//        List<Integer> userIdList = getBothFollowList(uid1, uid2, page, pageSize);
//        Map<Integer, User> userMap = userService.getUserMap(userIdList);
//
//        List<User> userList = new ArrayList<User>();
//        for (Integer id : userIdList) {
//            User user = userMap.get(id);
//            if (user != null) {
//                userList.add(user);
//            }
//        }
//        return userList;
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(fansService, "fansService must not null");
        Assert.notNull(followService, "followService must not null");
//        Assert.notNull(friendCache, "countService must not null");
        Assert.notNull(followCache, "countService must not null");
        Assert.notNull(fansCache, "countService must not null");
        Assert.notNull(counterService, "countService must not null");
        Assert.notNull(userService, "userService must not null");
    }

    @Override
    public long getFollowCount(int uid) {
        return (int) counterService.getCount(ConstRedis.COUNTER_KEY_FOLLOW + uid);
    }

    @Override
    public long getFansCount(int uid) {
        return (int) counterService.getCount(ConstRedis.COUNTER_KEY_FANS + uid);
    }

}
