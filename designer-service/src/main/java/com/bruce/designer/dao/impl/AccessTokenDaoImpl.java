package com.bruce.designer.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.model.AccessTokenInfo;
import com.bruce.designer.model.AccessTokenInfoCriteria;
import com.bruce.designer.dao.IAccessTokenDao;
import com.bruce.designer.dao.mapper.AccessTokenInfoMapper;

@Repository
public class AccessTokenDaoImpl implements IAccessTokenDao {

	@Autowired
	private AccessTokenInfoMapper accessTokenInfoMapper;

	public int save(AccessTokenInfo t) {
		return accessTokenInfoMapper.insert(t);
	}

	public AccessTokenInfo loadById(Integer id) {
		return accessTokenInfoMapper.selectByPrimaryKey(id);
	}

	public int updateById(AccessTokenInfo t) {
		return accessTokenInfoMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(AccessTokenInfo t, AccessTokenInfoCriteria criteria) {
		return accessTokenInfoMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteByCriteria(AccessTokenInfoCriteria criteria) {
		return accessTokenInfoMapper.deleteByExample(criteria);
	}

	public int deleteById(Integer id) {
		return accessTokenInfoMapper.deleteByPrimaryKey(id);
	}

	public List<AccessTokenInfo> queryAll() {
		return accessTokenInfoMapper.selectByExample(null);
	}

	@Override
	public List<AccessTokenInfo> queryAll(String orderByClause) {
		AccessTokenInfoCriteria criteria = new AccessTokenInfoCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<AccessTokenInfo> queryByCriteria(AccessTokenInfoCriteria criteria) {
		return accessTokenInfoMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(AccessTokenInfoCriteria criteria) {
		return accessTokenInfoMapper.countByExample(criteria);
	}

	@Override
	public AccessTokenInfo load(String thirdpartyUid, Short thirdpartyType) {
		AccessTokenInfoCriteria criteria = new AccessTokenInfoCriteria();
		criteria.createCriteria().andThirdpartyUidEqualTo(thirdpartyUid).andThirdpartyTypeEqualTo(thirdpartyType);

		List<AccessTokenInfo> tokenList = accessTokenInfoMapper.selectByExample(criteria);
		if (tokenList != null && tokenList.size() == 1) {
			return tokenList.get(0);
		}
		return null;
	}

	/**
	 * 查询用户已绑定的第三方账户
	 */
	@Override
	public List<AccessTokenInfo> queryByUserId(Integer userId) {
		AccessTokenInfoCriteria criteria = new AccessTokenInfoCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId);
		return accessTokenInfoMapper.selectByExample(criteria);
	}

	/**
	 * 解绑定第三方账户
	 */
	@Override
	public int delete(Integer userId, Short thirdpartyType) {
		AccessTokenInfoCriteria criteria = new AccessTokenInfoCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId).andThirdpartyTypeEqualTo(thirdpartyType);
		return accessTokenInfoMapper.deleteByExample(criteria);
	}

	@Override
	public int updateSyncStatus(short syncStatus, String thirdpartyUid, Short thirdpartyType) {
		AccessTokenInfo updatedInfo = new AccessTokenInfo();
		updatedInfo.setSyncAlbum(syncStatus);
		
		AccessTokenInfoCriteria criteria = new AccessTokenInfoCriteria();
		criteria.createCriteria().andThirdpartyUidEqualTo(thirdpartyUid).andThirdpartyTypeEqualTo(thirdpartyType);
		return accessTokenInfoMapper.updateByExampleSelective(updatedInfo, criteria);
	}

}
