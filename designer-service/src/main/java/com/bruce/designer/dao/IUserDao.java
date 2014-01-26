package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.User;

public interface IUserDao extends IBaseDao<User, Integer>{
    

    public boolean usernameExists(String username);
    
    public boolean nicknameExists(String nickname);
    
    public User loadByNamePassword(String username, String password);
    
    public int changePassword(int userId, String password);
        
    public List<User> queryUsersByIds(List<Integer> idList);
    
//    public List<User> queryUsersByStatus(short status);
//    
//    public List<User> queryDesignersByStatus(short status);
    
    public int applyDesigner(int userId, String idNum, String realname, String mobile, String company, String taobaoHomepage);
    
    public int operateDesigner(int userId, short operationType);

    public List<User> fallLoadDesignerList(long approvelTailTime, int limit);


}
