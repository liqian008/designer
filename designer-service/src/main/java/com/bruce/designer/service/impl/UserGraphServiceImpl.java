package com.bruce.designer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.bruce.designer.cache.user.FanCache;
import com.bruce.designer.cache.user.FollowCache;
import com.bruce.designer.dao.IUserFanDao;
import com.bruce.designer.dao.IUserFollowDao;
import com.bruce.designer.exception.RedisKeyNotExistException;
import com.bruce.designer.model.User;
import com.bruce.designer.model.UserFan;
import com.bruce.designer.model.UserFollow;
import com.bruce.designer.service.IUserGraphService;
import com.bruce.designer.service.IUserService;
//import com.bruce.designer.constants.ConstRedis;

@Service
public class UserGraphServiceImpl implements IUserGraphService, InitializingBean {

    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(UserGraphServiceImpl.class);
    
    private static final int FANS_CACHE_MAX_COUNT = 5000;
    
//    private static final long MULTI_GET_FOLLOW_TIMEOUT = 500;

    @Autowired
    private IUserFollowDao followDao;
    @Autowired
    private IUserFanDao fanDao;
    @Autowired
    private FollowCache followCache;
    @Autowired
    private FanCache fanCache;
//    @Autowired
//    private ICounterService counterService;
    @Autowired
    private IUserService userService;

    /**
     * 获取指定uid的关注列表，分页查询
     */
    @Override
    public List<UserFollow> getFollowListWithUser(int uid, int page, int pageSize) {
        List<UserFollow> followList = getFollowList(uid, page, pageSize);
        List<Integer> userIdList = new ArrayList<Integer>();
        userIdList.add(uid);
        for (UserFollow follow : followList) {
            userIdList.add(follow.getFollowId());
        }

        Map<Integer, User> userMap = userService.getUserMap(userIdList);
        User followUser = userMap.get(uid);

        for (UserFollow follow : followList) {
            follow.setFollowUser(followUser);
            follow.setFollowUser(userMap.get(follow.getFollowId()));
        }
        return followList;
    }
    
    @Override
    public List<UserFollow> getFollowList(int uid, int page, int pageSize) {
        page = page<1?1:page;
        List<UserFollow> followList = getFollowList(uid);
        if (followList != null && followList.size() > 0) {
            int size = followList.size();
            int fromIndex = (page - 1) * pageSize;
            int toIndex = (page) * pageSize;
            if (size > fromIndex) {
                toIndex = toIndex > size ? size : toIndex;
                return followList.subList(fromIndex, toIndex);
            }
        }
        return new ArrayList<UserFollow>();
    }
    
    @Override
    public List<UserFollow> getFollowList(int uid) {
        List<UserFollow> followList;
        try {
            followList = followCache.getAllFollowList(uid);
        } catch (RedisKeyNotExistException e) {
        	if(logger.isErrorEnabled()){
        		logger.error("getFollowList ："+uid, e);
        	}
        	
            followList = followDao.getFollowList(uid);
            if(followList!=null&&followList.size()>0){
                followCache.setFollowList(uid, followList);
            }
        }
        return followList;
    }

    /**
     * 检查是否关注
     */
    @Override
    public boolean isFollow(int uid, int followId) {
        if (uid == followId) {
            return false;
        }
        boolean isFollow = false;
        try {
            isFollow = followCache.isFollowed(uid, followId);
        } catch (RedisKeyNotExistException e) {
        	if(logger.isErrorEnabled()){
        		logger.error("isFollow ："+uid+", "+ followId, e);
        	}
        	
            List<UserFollow> followList = getFollowList(uid);
            if(followList!=null&&followList.size()>0){
                followCache.setFollowList(uid, followList);
                for (UserFollow follow : followList) {
                    if (follow.getFollowId() == followId) {
                        isFollow = true;
                        break;
                    }
                }
            }
//            friendCache.setFriendList(uid, followList);
        }
        return isFollow;
    }
    
    /**
     * 关注某人
     */
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

            if (followDao.save(follow) > 0) {
                try {
                    followCache.addFollow(follow);
//                    if (isFriend) {
//                        friendCache.addFriend(follow);
//                    }
                } catch (RedisKeyNotExistException e) {
                    List<UserFollow> followList = followDao.getFollowList(uid);
                    if(followList!=null&&followList.size()>0){
                        followCache.setFollowList(uid, followList);
                    }
//                    friendCache.setFriendList(uid, followList);
                }
                // TODO 增加follow计数
//                counterService.increase(ConstRedis.COUNTER_KEY_FOLLOW + uid);
            } else {
                // TODO 添加失败队列修复
            }

            //添加粉丝
            UserFan fan = new UserFan();
            fan.setUserId(followId);
            fan.setFanId(uid);
            fan.setCreateTime(currentTime);
            
            if (fanDao.save(fan) > 0) {
                try {
                    fanCache.addFan(fan);
                } catch (Exception e) {
                	if(logger.isErrorEnabled()){
                		logger.error("follow ："+uid+", "+followId, e);
                	}
                	
                    List<UserFan> fansList = fanDao.getFanList(followId, FANS_CACHE_MAX_COUNT);
                    if(fansList!=null&&fansList.size()>0){
                        fanCache.setFanList(followId, fansList);
                    }
                }
                // TODO 增加粉丝计数
//                counterService.increase(ConstRedis.COUNTER_KEY_FAN + followId);
            } else {
                // TODO 添加失败队列修复
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
                if (followDao.deleteFollow(uid, unfollowId) > 0) {
                    followCache.removeFollow(uid, unfollowId);
                    // TODO 删cache减少计数
//                    counterService.reduce(ConstRedis.COUNTER_KEY_FOLLOW + uid);
                } else {
                    // TODO 队列修复
                } 

                //删除粉丝
                if (fanDao.deleteFan(unfollowId, uid) > 0) { 
                    fanCache.removeFan(unfollowId, uid);
                    // TODO 删cache减少计数
//                    counterService.reduce(ConstRedis.COUNTER_KEY_FAN + uid);
                } else {
                    // TODO 队列修复
                }

                return true;
            } catch (Exception e) {
            	if(logger.isErrorEnabled()){
            		logger.error("unfollow ："+uid+", "+unfollowId, e);
            	}
            }

        } else {
            return false;
        }
        return false;
    }

    @Override
    public List<UserFan> getFanList(int userId) {
        List<UserFan> fansList;
        try {
            fansList = fanCache.getAllFanList(userId);
        } catch (RedisKeyNotExistException e) {
        	if(logger.isErrorEnabled()){
    			logger.error("getFanList: "+userId, e);
    		}
        	
            fansList = fanDao.getFanList(userId, FANS_CACHE_MAX_COUNT);
            if(fansList!=null&&fansList.size()>0){
                fanCache.setFanList(userId, fansList);
            }
        }
        return fansList;
    }

    @Override
    public List<UserFan> getFanList(int uid, int page, int pageSize) {
        page = page<1?1:page;
        List<UserFan> fanList = getFanList(uid);
        if (fanList != null && fanList.size() > 0) {
            int size = fanList.size();
            int fromIndex = (page-1) * pageSize;
            int toIndex = (page) * pageSize;
            if (size > fromIndex) {
                toIndex = toIndex > size ? size : toIndex;
                return fanList.subList(fromIndex, toIndex);
            }
        }
        return new ArrayList<UserFan>();
    }

    @Override
    public List<UserFan> getFanListWithUser(int uid, int page, int pageSize) {
        List<UserFan> fanList = getFanList(uid, page, pageSize);
        List<Integer> userIdList = new ArrayList<Integer>();
        userIdList.add(uid);
        for (UserFan fan : fanList) {
            userIdList.add(fan.getFanId());
        }

        Map<Integer, User> userMap = userService.getUserMap(userIdList);
        User fanUser = userMap.get(uid);

        for (UserFan fan : fanList) {
            fan.setFanUser(fanUser);
            fan.setFanUser(userMap.get(fan.getFanId()));
        }

        return fanList;
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
        Assert.notNull(fanDao, "fanDao must not null");
        Assert.notNull(followDao, "followDao must not null");
        Assert.notNull(followCache, "followCache must not null");
        Assert.notNull(fanCache, "fanCache must not null");
//        Assert.notNull(counterService, "countService must not null");
        Assert.notNull(userService, "userService must not null");
    }
    
    
    /**
     * 获取关注数
     */
    @Override
    public long getFollowCount(int userId) {
        //关注数
    	try {
			return followCache.getFollowCount(userId);
		} catch (RedisKeyNotExistException e) {
			
			if(logger.isErrorEnabled()){
    			logger.error("getFollowCount: "+userId, e);
    		}
			//DB加载数据，重建cache
			List<UserFollow> followList = followDao.getFollowList(userId);
			if(followList!=null&&followList.size()>0){
			    followCache.setFollowList(userId, followList);
			    return followList.size();
			}
			return 0;
		}
    }

    /**
     *获取粉丝数
     */
    @Override
    public long getFanCount(int userId) {
    	//粉丝数
    	try {
			return fanCache.getFanCount(userId);
		} catch (RedisKeyNotExistException e) {
			
			if(logger.isErrorEnabled()){
    			logger.error("getFanCount: "+userId, e);
    		}
			
			//DB加载数据，重建cache
			List<UserFan> fansList = fanDao.getFanList(userId, FANS_CACHE_MAX_COUNT);
			if(fansList!=null&&fansList.size()>0){
			    fanCache.setFanList(userId, fansList);
			    return fansList.size();
			}
			return 0;
		}
    }

}
