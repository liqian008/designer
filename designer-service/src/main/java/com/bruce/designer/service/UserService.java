package com.bruce.designer.service;

import java.sql.SQLException;
import java.util.List;

import com.bruce.designer.bean.TbUser;

public interface UserService extends BaseService<TbUser, Integer>{
    
//    public TbUser loadUserById(int userId) throws SQLException; 
//
    public List<TbUser> queryUsersByStatus(short status);
//
//    public int saveUser(TbUser user) throws SQLException;

    
}
