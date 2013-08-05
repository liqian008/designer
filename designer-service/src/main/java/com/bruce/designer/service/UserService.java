package com.bruce.designer.service;

import java.util.List;
import com.bruce.baseService.IBaseService;

import com.bruce.designer.bean.User;

public interface UserService extends IBaseService<User, Integer>{
    

    public List<User> queryUsersByStatus(short status);
    
    public User authUser(String username, String password);

//    public User loadUserById(int userId) throws SQLException;
//    public int saveUser(User user) throws SQLException;
    
}
