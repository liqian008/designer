package com.bruce.designer.service.oauth;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.bean.UserSource;
import com.bruce.designer.bean.UserSourceCriteria;
import com.bruce.designer.dao.UserSourceMapper;

@Service
public class UserSourceServiceImpl implements IUserSourceService {

	@Autowired
	private UserSourceMapper UserSourceMapper;

	public int save(UserSource t) {
		return UserSourceMapper.insert(t);
	}

	public List<UserSource> queryAll() {
		return UserSourceMapper.selectByExample(null);
	}

	public int updateById(UserSource t) {
		return UserSourceMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Integer id) {
		return UserSourceMapper.deleteByPrimaryKey(id);
	}

	public UserSource loadById(Integer id) {
		return UserSourceMapper.selectByPrimaryKey(id);
	}


}
