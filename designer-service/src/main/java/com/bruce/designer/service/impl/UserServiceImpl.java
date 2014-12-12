package com.bruce.designer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.cache.user.UserCache;
import com.bruce.designer.constants.ConstConfig;
import com.bruce.designer.constants.ConstService;
import com.bruce.designer.dao.IUserDao;
import com.bruce.designer.exception.DesignerException;
import com.bruce.designer.exception.ErrorCode;
import com.bruce.designer.mail.MailService;
import com.bruce.designer.model.AccessTokenInfo;
import com.bruce.designer.model.User;
import com.bruce.designer.model.UserCriteria;
import com.bruce.designer.service.IMessageService;
import com.bruce.designer.service.ITaskService;
import com.bruce.designer.service.IUserService;
import com.bruce.designer.service.oauth.IAccessTokenService;
import com.bruce.designer.service.oauth.IOAuthService;
import com.bruce.designer.service.oauth.SharedInfo;
import com.bruce.designer.service.upload.impl.UploadQiniuServiceImpl;
import com.bruce.designer.util.ConfigUtil;
import com.bruce.designer.util.Md5Utils;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	@Autowired
	private IAccessTokenService accessTokenService;
	@Autowired
    private UserCache userCache;
	@Autowired
	private IMessageService messageService;
	@Autowired
    private UploadQiniuServiceImpl uploadQiniuService;
	@Autowired
	private MailService mailService;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private IOAuthService oauthService; 
	
	public int save(User t) {
		if(t!=null){
		    t.setPassword(Md5Utils.md5Encode(t.getPassword().trim()));
		    return userDao.save(t);
		}
		return 0;
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
//            	checkUserStatus(user);
                user.setPassword(null);
                //completeUser(user);
                userCache.setUser(user);
            }
//          else{
//            throw new DesignerException(ErrorCode.USER_NOT_EXIST);
//          }
        }
        return user;
	}
	
	/**
	 * 加载个人资料&accessToken信息
	 * @param id
	 * @return
	 */
	@Override
	public User loadById(Integer id, boolean loadAccessTokenMap) {
	    User user = loadById(id);
	    if(loadAccessTokenMap && user!=null){
	    	completeUser(user);
	    }
	    return user;
	}
	
	
	/**
	 * 用户认证
	 */
	public User authUser(String username, String password) {
		User user = userDao.loadByNamePassword(username, Md5Utils.md5Encode(password.trim()));
		if(user!=null){
			checkUserStatus(user);
			completeUser(user);//加载accessToken的信息
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
            List<User> userList = userDao.queryUsersByIds(leftIdList);//封禁用户处理
            for (User user : userList) {
                if (user != null) {
                    userMap.put(user.getId(), user);
                }
            }
            userCache.setUserList(userList);
        }
        return userMap;
    }
	
    @Override
	public int updateAvatar(int userId, String avatarUrl) {
		int result = userDao.updateAvatar(userId, avatarUrl);
		if(result>0){
			//清空用户cache
			userCache.deleteUser(userId);
		}
		return result;
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
	    return userDao.changePassword(userId, Md5Utils.md5Encode(password).trim());
    }
	
	/**
	 * 查询指定idList的用户数据
	 */
	@Override
	public List<User> queryUsersByIds(List<Integer> idList) {
		if(idList!=null&&idList.size()>0){
			//取正常状态的用户列表
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
	
	
	@Override
	public List<User> fallLoadDesignerList(long approvelTailTime, int limit) {
		//取正常状态的用户列表
		List<User> userList =userDao.fallLoadDesignerList(approvelTailTime, limit);
		return userList;
	}
	
	public List<User> queryUsersByStatus(short status) {
	    return userDao.queryUsersByStatus(status);
	}
	
	@Override
	public List<User> queryAllDesigners() {
	    return userDao.queryAllDesigners();
	}
	
	@Override
	public List<User> queryDesignersByStatus(short status) {
		//查询设计师列表
	    return userDao.queryDesignersByStatus(status);
	}
	
	/**
     * 提交审核
     */
	@Override
	public int apply4Designer(int userId, String realname, String idNum, String mobile, String company, String taobaoHomepage) {
		int result = userDao.applyDesigner(userId, idNum, realname, mobile, company, taobaoHomepage);
	    if(result>0){
	    	userCache.deleteUser(userId);
			String message = ConfigUtil.getString("designer_apply_message");
			messageService.sendSystemMessage(userId, message);
		}
	    return result;
    }
	
	@Override
	public int designerDenied(int userId) {
		//系统发送拒绝消息
		int result = designerApplyOp(userId, ConstService.DESIGNER_APPLY_DENIED);
		if(result>0){
			String message = ConfigUtil.getString("designer_denied_message");
			messageService.sendSystemMessage(userId, message);
		}
		return result;
    }
	
	/**
	 * 审核通过
	 */
	@Override
	public int designerApprove(int userId) {
		//系统发送审核通过消息
		int result = designerApplyOp(userId, ConstService.DESIGNER_APPLY_APPROVED);
		if(result>0){
			String message = ConfigUtil.getString("designer_approved_message");
			messageService.sendSystemMessage(userId, message);
		}
		return result;
    }
	
	/**
	 * 处理设计师申请操作
	 * @param userId
	 * @param operationType
	 * @return
	 */
	private int designerApplyOp(int userId, short operationType) {
        return userDao.operateDesigner(userId, operationType);
    }
	
	/**
	 * 检查用户状态
	 * @param user
	 */
	private void checkUserStatus(User user){
		if(user.getStatus()==ConstService.USER_STATUS_FORBIDDEN){
	    	throw new DesignerException(ErrorCode.USER_FORBIDDEN);
	    }
	}
	
	/**
	 * 完善并设置第三方绑定信息 
	 * @param user
	 */
	private void completeUser(User user) {
	    List<AccessTokenInfo> accessTokenList = accessTokenService.queryByUserId(user.getId());
//	    //清空accessToken，避免泄漏
//	    if(accessTokenList!=null&&accessTokenList.size()>0){
//	    	for(AccessTokenInfo accessTokenInfo: accessTokenList){
//	    		accessTokenInfo.setAccessToken(null);
//	    		accessTokenInfo.setRefreshToken(null);
//	    	}
//	    }
	    user.refreshTokenMap(accessTokenList);
    }
	
	
	

	@Override
	public int updateByCriteria(User t, UserCriteria criteria) {
		return userDao.updateByCriteria(t, criteria);
	}

	@Override
	public int deleteByCriteria(UserCriteria criteria) {
		return userDao.deleteByCriteria(criteria);
	}

	@Override
	public List<User> queryAll(String orderByClause) {
		return userDao.queryAll(orderByClause);
	}

	@Override
	public List<User> queryByCriteria(UserCriteria criteria) {
		return userDao.queryByCriteria(criteria);
	}
	
	@Override
	public int countByCriteria(UserCriteria criteria) {
		return userDao.countByCriteria(criteria);
	}

	/**
	 * oauth方式的用户注册（需要将oauth中的头像作为用户 头像保存）
	 */
	@Override
	public int registerByOauth(User user, Short thirdpartyType, String accessToken, String thirdpartyAvatar) {
		//使用第三方头像
		user.setHeadImg(thirdpartyAvatar);
		int result =  save(user);
		
		//修改头像策略，直接使用第三方账户系统的头像链接
//		if(!StringUtils.isBlank(thirdpartyAvatar)&&user.getId()!=null&&user.getId()>0){
//			//保存用户头像
//			try {
//				uploadQiniuService.uploadAvatarByUrl(thirdpartyAvatar, String.valueOf(user.getId()));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		if(result>0){
			String welcomeMessage = ConfigUtil.getString("welcome_message");
			//TODO发送系统欢迎消息
			messageService.sendSystemMessage(user.getId(), welcomeMessage);
			//系统异步发送欢迎邮件
			mailService.sendWelcomeMail(user.getUsername());

			if (thirdpartyType != null && thirdpartyType == IOAuthService.OAUTH_WEIBO_TYPE) {
				// 发布一条微博，添加至线程池运行
				SharedInfo sharedInfo = new SharedInfo(thirdpartyType, accessToken, ConstConfig.WEIBO_REGISTER_POST_CONTENT, ConstConfig.WEIBO_REGISTER_POST_IMG);
				oauthService.shareout(sharedInfo);
			}
		}
		
		return result;
	}

	@Override
	public long getUserPushMask(int userId) {
		return userDao.getUserPushMask(userId);
	}

	@Override
	public int setUserPushMask(int userId, long pushMask) {
		int result =  userDao.setUserPushMask(userId, pushMask);
		return result;
	}

	
	
}
