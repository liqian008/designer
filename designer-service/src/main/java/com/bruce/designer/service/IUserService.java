package com.bruce.designer.service;

import java.util.List;
import java.util.Map;

import com.bruce.designer.model.User;

public interface IUserService extends IBaseService<User, Integer>{
    
	
	public List<User> queryUsersByIds(List<Integer> idList);
	
    public List<User> queryUsersByStatus(short status);
    
    public List<User> queryDesignersByStatus(short status);
    
    public Map<Integer, User> getUserMap(List<Integer> userIds);
    
    public User authUser(String username, String password);
    
//    public User reloadUser(Integer userId);
    
    public boolean usernameExists(String username);
    
    public boolean nicknameExists(String nickname);
    
    public int changePassword(int userId, String password);
    
    /**
     * 申请设计师
     * @param userId
     * @return
     */
    public int apply4Designer(int userId);
    
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
//    public User loadUserById(int userId) throws SQLException;
//    public int saveUser(User user) throws SQLException;
    
    /**
     * 瀑布流方式加载设计师
     * @param approvelTailTime
     * @param limit
     * @return
     */
	public List<User> fallLoadDesignerList(long approvelTailTime, int limit);

	

}
