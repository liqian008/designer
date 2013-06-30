package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service; 
import org.springframework.beans.factory.annotation.Autowired;
 
import com.bruce.designer.bean.TbUser;
import com.bruce.designer.bean.TbUserCriteria;
import com.bruce.designer.dao.TbUserMapper;
import com.bruce.designer.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    
	@Autowired
	private TbUserMapper userMapper;

	public int save(TbUser t) {
		return userMapper.insert(t);
	}
	

	
	public List<TbUser> queryAll() { 
		// TODO Auto-generated method stub
		return userMapper.selectByExample(null);
	}

	
	public int updateById(TbUser t) {
		return userMapper.updateByPrimaryKeySelective(t);
	}


	
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.deleteByPrimaryKey(id);
	}


	
	public TbUser loadById(Integer id) {
		// TODO Auto-generated method stub
//		return userMapper.selectByExample(example);
		return userMapper.selectByPrimaryKey(id);
	}


	
	public List<TbUser> queryUsersByStatus(short status) {
		TbUserCriteria criteria = new TbUserCriteria();
		int id=5;
		String username = "%% or 1=1 or email like %%";
		criteria.createCriteria().andStatusEqualTo(status).andUsernameLike(username);
		return userMapper.selectByExample(criteria);
	}

	

}
