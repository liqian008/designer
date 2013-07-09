package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.bean.User;
import com.bruce.designer.bean.UserCriteria;
import com.bruce.designer.dao.UserMapper;
import com.bruce.designer.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	public int save(User t) {
		return userMapper.insert(t);
	}

	public List<User> queryAll() {
		return userMapper.selectByExample(null);
	}

	public int updateById(User t) {
		return userMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Integer id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	public User loadById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	public User authUser(String username, String password) {
		UserCriteria criteria = new UserCriteria();
		criteria.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
		List<User> userList = userMapper.selectByExample(criteria);
		if(userList!=null&&userList.size()==1){
			return userList.get(0);
		}
		return null;
	}

	public List<User> queryUsersByStatus(short status) {
		UserCriteria criteria = new UserCriteria();
//		String username = "%% or 1=1 or email like %%";
		criteria.createCriteria().andStatusEqualTo(status);
		
		return userMapper.selectByExample(criteria);
	}

}
