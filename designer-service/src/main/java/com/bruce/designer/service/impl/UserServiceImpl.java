package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.bean.TbUser;
import com.bruce.designer.bean.TbUserCriteria;
import com.bruce.designer.dao.TbUserMapper;
import com.bruce.designer.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper userMapper;

	public int save(TbUser t) {
		return userMapper.insert(t);
	}

	public List<TbUser> queryAll() {
		return userMapper.selectByExample(null);
	}

	public int updateById(TbUser t) {
		return userMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Integer id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	public TbUser loadById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	public TbUser authUser(String username, String password) {
		TbUserCriteria criteria = new TbUserCriteria();
		criteria.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
		List<TbUser> userList = userMapper.selectByExample(criteria);
		if(userList!=null&&userList.size()==1){
			return userList.get(0);
		}
		return null;
	}

	public List<TbUser> queryUsersByStatus(short status) {
		TbUserCriteria criteria = new TbUserCriteria();
//		String username = "%% or 1=1 or email like %%";
		criteria.createCriteria().andStatusEqualTo(status);
		
		return userMapper.selectByExample(criteria);
	}

}
