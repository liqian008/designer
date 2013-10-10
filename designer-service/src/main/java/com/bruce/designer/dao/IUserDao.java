package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.User;

public interface IUserDao extends IBaseDao<User, Integer>{
    

    public boolean userExists(String username);
    
    public User loadByNamePassword(String username, String password);
    
    public int changePassword(int userId, String password);
        
    public List<User> queryUsers(List<Integer> userIds);
    
    public List<User> queryUsersByStatus(short status);
    
    public List<User> queryDesignersByStatus(short status);
    
    public int designerApplyOp(int userId, short operationType);
}
