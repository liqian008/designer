package com.bruce.designer.service;

import java.util.List;
import java.util.Map;

import com.bruce.designer.model.User;
import com.bruce.designer.model.UserCriteria;
import com.bruce.foundation.service.IFoundationService;

public interface IUserService extends IFoundationService<User, Integer, UserCriteria>{
	
	public User loadById(Integer id, boolean loadAccessTokenMap); 

	/*使用oauth方式注册用户*/
	public int registerByOauth(User user, Short thirdpartyType, String accessToken, String thirdpartyAvatar);
	
	public List<User> queryUsersByStatus(short status);

	public List<User> queryAllDesigners();

	public List<User> queryDesignersByStatus(short status);
	
	/*更新用户头像*/
	public int updateAvatar(int userId, String avatarUrl);
	
	/**
	 * 根据idList加载用户
	 * @param idList
	 * @return
	 */
	public List<User> queryUsersByIds(List<Integer> idList);
    
    public Map<Integer, User> getUserMap(List<Integer> userIds);
    
    public User authUser(String username, String password);
    
    public boolean usernameExists(String username);
    
    public boolean nicknameExists(String nickname);
    
    public int changePassword(int userId, String password);
    
    /*获取用户的push选项*/
    public long getUserPushMask(int userId);
    /*设置用户的push选项*/
    public int setUserPushMask(int userId, long pushMask);
    
    /**
     * 申请设计师
     * @param userId
     * @return
     */
	public int apply4Designer(int userId, String realname, String idNum, String mobile, String company, String taobaoHomepage);

    
    /**
     * 批准申请
     * @param userId
     * @return
     */
    public int designerApprove(int userId);
    
    /**
     * 拒绝申请
     * @param userId
     * @return
     */
    public int designerDenied(int userId);
    
    /**
     * 前台瀑布流方式加载设计师
     * @param approvelTailTime
     * @param limit
     * @return
     */
	public List<User> fallLoadDesignerList(long approvelTailTime, int limit);

	

}
