package com.bruce.designer.service;

import java.util.List;
import java.util.Map;

import com.bruce.designer.model.User;

public interface IUserService extends IBaseService<User, Integer>{
    

    public List<User> queryUsersByStatus(short status);
    
    public List<User> queryDesignersByStatus(short status);
    
    public Map<Integer, User> getUserMap(List<Integer> userIds);
    
    public User authUser(String username, String password);
    
//    public User reloadUser(Integer userId);
    
    public boolean userExists(String username);
    
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
    public int designerApproval(int userId);
    
    /**
     * 拒绝申请
     * @param userId
     * @return
     */
    public int designerDenied(int userId);
//    public User loadUserById(int userId) throws SQLException;
//    public int saveUser(User user) throws SQLException;

}
