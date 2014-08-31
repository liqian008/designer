package com.bruce.designer.service.oauth;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.dao.IAccessTokenDao;
import com.bruce.designer.model.AccessTokenInfo;
import com.bruce.designer.model.AccessTokenInfoCriteria;

@Service
public class AccessTokenServiceImpl implements IAccessTokenService {

	@Autowired
	private IAccessTokenDao accessTokenDao;

	public int save(AccessTokenInfo t) {
		return accessTokenDao.save(t);
	}

	public List<AccessTokenInfo> queryAll() {
		return accessTokenDao.queryAll();
	}

	public int updateById(AccessTokenInfo t) {
		return accessTokenDao.updateById(t);
	}

	public int deleteById(Integer id) {
		return accessTokenDao.deleteById(id);
	}

	public AccessTokenInfo loadById(Integer id) {
		return accessTokenDao.loadById(id);
	}
	
    @Override
    public AccessTokenInfo load(String thirdpartyUid, Short thirdpartyType) {
        return accessTokenDao.load(thirdpartyUid, thirdpartyType);
    }
    
    /**
     * 查询用户已绑定的第三方账户
     */
    @Override
    public List<AccessTokenInfo> queryByUserId(Integer userId){
        return accessTokenDao.queryByUserId(userId);
    }
    
    /**
     * 解绑定第三方账户
     */
    @Override
    public int delete(Integer userId, Short thirdpartyType) {
        return accessTokenDao.delete(userId, thirdpartyType);
    }

	@Override
	public int updateByCriteria(AccessTokenInfo t, AccessTokenInfoCriteria criteria) {
		return accessTokenDao.updateByCriteria(t, criteria);
	}

	@Override
	public int deleteByCriteria(AccessTokenInfoCriteria criteria) {
		return accessTokenDao.deleteByCriteria(criteria);
	}

	@Override
	public List<AccessTokenInfo> queryAll(String orderByClause) {
		return accessTokenDao.queryAll(orderByClause);
	}

	@Override
	public List<AccessTokenInfo> queryByCriteria(AccessTokenInfoCriteria criteria) {
		return accessTokenDao.queryByCriteria(criteria);
	}

	@Override
	public int updateSyncStatus(short syncStatus, String thirdpartyUid, Short thirdpartyType) {
		return accessTokenDao.updateSyncStatus(syncStatus, thirdpartyUid, thirdpartyType);
	}
    
}
