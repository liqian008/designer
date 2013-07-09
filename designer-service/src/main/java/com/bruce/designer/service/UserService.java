package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.bean.User;

public interface UserService extends BaseService<User, Integer>{
    
//    public User loadUserById(int userId) throws SQLException; 
//
    public List<User> queryUsersByStatus(short status);
    
    public User authUser(String username, String password);
//
//    public int saveUser(User user) throws SQLException;

    
}
