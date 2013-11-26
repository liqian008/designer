package com.bruce.designer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.model.AccessTokenInfo;
import com.bruce.designer.model.Album;
import com.bruce.designer.model.User;
import com.bruce.designer.cache.user.UserCache;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.dao.IUserDao;
import com.bruce.designer.service.ICounterService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.service.oauth.IAccessTokenService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	@Autowired
	private IAccessTokenService accessTokenService;
	@Autowired
    private UserCache userCache;
	@Autowired
	private ICounterService counterService;
	
	public int save(User t) {
		return userDao.save(t);
	}

	public List<User> queryAll() {
		return userDao.queryAll();
	}

	public int updateById(User t) {
		return userDao.updateById(t);
	}

	public int deleteById(Integer id) {
		return 0;
	}
	
	
	
	public User loadById(Integer id) {
	    User user = userCache.getUser(id);
        if (user == null) {
            user = userDao.loadById(id);
            if (user != null) {
                user.setPassword(null);
                completeUser(user);
                userCache.setUser(user);
            }
        }
        return user;
	}
	
	/**
	 * 用户认证
	 */
	public User authUser(String username, String password) {
		User user = userDao.loadByNamePassword(username, password);
		if(user!=null){
		    completeUser(user);
		    return user;
		}
		return null;
	}
	
	/**
     * 重新加载用户
     */
//    public User reloadUser(Integer userId) {
//        User user = loadById(userId);
//        if(user!=null&&user.getId()!=null&&user.getId()>0){
//            //加载并设置第三方绑定信息
//            List<AccessTokenInfo> accessTokenList = accessTokenService.queryByUserId(user.getId());
//            //user.setAccessTokenList(accessTokenList);
//            user.refreshTokenMap(accessTokenList);
//            //返回
//            return user;
//        }
//        return null;
//    }
    
    public Map<Integer, User> getUserMap(List<Integer> userIds) {
        Map<Integer, User> userMap = userCache.multiGetUser(userIds);
        List<Integer> leftIdList = new ArrayList<Integer>();
        for (Integer id : userIds) {
            User user = userMap.get(id);
            if (user == null) {
                leftIdList.add(id);
            }
        }
        if (leftIdList.size() > 0) {
            List<User> userList = userDao.queryUsersByIds(leftIdList);
            for (User user : userList) {
                if (user != null) {
                    completeUser(user);  //补全信息
                    userMap.put(user.getId(), user);
                }
            }
            userCache.setUserList(userList);
        }
        return userMap;
    }
    
	
	/**
	 * 检查用户exists
	 * @param username
	 * @return
	 */
	@Override
    public boolean usernameExists(String username){
        return userDao.usernameExists(username);
    }
	
	/**
	 * 检查用户exists
	 * @param nickname
	 * @return
	 */
	@Override
    public boolean nicknameExists(String nickname){
        return userDao.nicknameExists(nickname);
    }
	
	@Override
    public int changePassword(int userId, String password) {
	    return userDao.changePassword(userId, password);
    }

	@Override
	public List<User> queryUsersByIds(List<Integer> idList) {
		if(idList!=null&&idList.size()>0){
			List<User> userList = userDao.queryUsersByIds(idList);
			if(userList!=null&&userList.size()>0){
				//按idList排序
				List<User> sortedUserList = new ArrayList<User>();
				for(int userId: idList){
					for(User user: userList){
						if(userId == user.getId()){
							sortedUserList.add(user);
							break;
						}
					}
				}
				return sortedUserList;
			}
		}
		return null;
	}
	
	public List<User> queryUsersByStatus(short status) {
	    return userDao.queryUsersByStatus(status);
	}
	
	@Override
	public List<User> fallLoadDesignerList(long approvelTailTime, int limit) {
		List<User> userList =userDao.fallLoadDesignerList(approvelTailTime, limit);
		return userList;
	}
	
	public List<User> queryDesignersByStatus(short status) {
	    return userDao.queryDesignersByStatus(status);
	}
	
	/**
     * 提交审核
     */
	@Override
	public int apply4Designer(int userId) {
	    return designerApplyOp(userId, ConstService.DESIGNER_APPLY_SENT);
    }
	
	@Override
	public int designerDenied(int userId) {
        return designerApplyOp(userId, ConstService.DESIGNER_APPLY_DENIED);
    }
	
	/**
	 * 审核通过
	 */
	@Override
	public int designerApprove(int userId) {
        return designerApplyOp(userId, ConstService.DESIGNER_APPLY_APPROVED);
    }
	
	/**
	 * 处理设计师申请操作
	 * @param userId
	 * @param operationType
	 * @return
	 */
	private int designerApplyOp(int userId, short operationType) {
        return userDao.designerApplyOp(userId, operationType);
    }
	
	/**
	 * 完善并设置第三方绑定信息 
	 * @param user
	 */
	private void completeUser(User user) {
	    List<AccessTokenInfo> accessTokenList = accessTokenService.queryByUserId(user.getId());
	    user.refreshTokenMap(accessTokenList);
    }
}
