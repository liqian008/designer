package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.bruce.designer.dao.IVersionUpdateDao;
import com.bruce.designer.model.VersionUpdate;
import com.bruce.designer.model.VersionUpdateCriteria;
import com.bruce.designer.service.IVersionUpdateService;

@Service
public class VersionUpdateServiceImpl implements IVersionUpdateService, InitializingBean {
	
	@Autowired
	private IVersionUpdateDao versionUpdateDao;
	
	public int save(VersionUpdate t) {
		return versionUpdateDao.save(t);
	}

	public List<VersionUpdate> queryAll() {
		return versionUpdateDao.queryAll();
	}

	public int updateById(VersionUpdate t) {
		return versionUpdateDao.updateById(t);
	}

	public int deleteById(Integer id) {
		return versionUpdateDao.deleteById(id);
	}

	public VersionUpdate loadById(Integer id) {
		return versionUpdateDao.loadById(id);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(versionUpdateDao, "versionUpdateDao can't be null");
	}

	@Override
	public int updateByCriteria(VersionUpdate t, VersionUpdateCriteria criteria) {
		return versionUpdateDao.updateByCriteria(t, criteria);
	}

	@Override
	public int deleteByCriteria(VersionUpdateCriteria criteria) {
		return versionUpdateDao.deleteByCriteria(criteria);
	}

	@Override
	public List<VersionUpdate> queryAll(String orderByClause) {
		return versionUpdateDao.queryAll(orderByClause);
	}

	@Override
	public List<VersionUpdate> queryByCriteria(VersionUpdateCriteria criteria) {
		return versionUpdateDao.queryByCriteria(criteria);
	}

	@Override
	public VersionUpdate loadByClientInfo(short clientType, int versionCode,
			String channel) {
		return versionUpdateDao.loadByClientInfo(clientType, versionCode, channel);
	}
	
}
