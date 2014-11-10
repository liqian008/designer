package com.bruce.designer.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.model.UserPushToken;
import com.bruce.designer.model.UserPushTokenCriteria;
import com.bruce.designer.dao.IUserPushTokenDao;
import com.bruce.designer.dao.mapper.UserPushTokenMapper;

@Repository
public class UserPushTokenDaoImpl implements IUserPushTokenDao {

	@Autowired
	private UserPushTokenMapper userPushTokenMapper;

	public int save(UserPushToken t) {
		return userPushTokenMapper.insert(t);
	}

	public UserPushToken loadById(Integer id) {
		return userPushTokenMapper.selectByPrimaryKey(id);
	}

	public int updateById(UserPushToken t) {
		return userPushTokenMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(UserPushToken t, UserPushTokenCriteria criteria) {
		return userPushTokenMapper.updateByExample(t, criteria);
	}

	@Override
	public int deleteByCriteria(UserPushTokenCriteria criteria) {
		return userPushTokenMapper.deleteByExample(criteria);
	}

	public int deleteById(Integer id) {
		return userPushTokenMapper.deleteByPrimaryKey(id);
	}

	public List<UserPushToken> queryAll() {
		return userPushTokenMapper.selectByExample(null);
	}

	@Override
	public List<UserPushToken> queryAll(String orderByClause) {
		UserPushTokenCriteria criteria = new UserPushTokenCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<UserPushToken> queryByCriteria(UserPushTokenCriteria criteria) {
		return userPushTokenMapper.selectByExample(criteria);
	}

	/**
	 * 加载用户已绑定的pushToken
	 */
	@Override
	public UserPushToken load(Integer userId, Short channelType) {
		UserPushTokenCriteria criteria = new UserPushTokenCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId).andChannelTypeEqualTo(channelType);
		List<UserPushToken> tokenList = userPushTokenMapper.selectByExample(criteria);
		if (tokenList != null && tokenList.size() == 1) {
			return tokenList.get(0);
		}
		return null;
	}

	/**
	 * 查询用户已绑定的pushToken
	 */
	@Override
	public List<UserPushToken> queryByUserId(Integer userId) {
		UserPushTokenCriteria criteria = new UserPushTokenCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId);
		return userPushTokenMapper.selectByExample(criteria);
	}

	/**
	 * 解绑定第三方账户
	 */
	@Override
	public int delete(Integer userId, Short channelType) {
		UserPushTokenCriteria criteria = new UserPushTokenCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId).andChannelTypeEqualTo(channelType);
		return userPushTokenMapper.deleteByExample(criteria);
	}


}
