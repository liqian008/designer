package com.bruce.designer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.dao.IVersionUpdateDao;
import com.bruce.designer.dao.mapper.VersionUpdateMapper;
import com.bruce.designer.model.VersionUpdate;
import com.bruce.designer.model.VersionUpdateCriteria;

@Repository
public class VersionUpdateDaoImpl implements IVersionUpdateDao, InitializingBean {

	@Autowired
	private VersionUpdateMapper versionUpdateMapper;

	public int save(VersionUpdate t) {
		return versionUpdateMapper.insertSelective(t);
	}

	public List<VersionUpdate> queryAll() {
		return versionUpdateMapper.selectByExample(null);
	}

	public int updateById(VersionUpdate t) {
		return versionUpdateMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Integer id) {
		return versionUpdateMapper.deleteByPrimaryKey(id);
	}

	public VersionUpdate loadById(Integer id) {
		return versionUpdateMapper.selectByPrimaryKey(id);
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	@Override
	public int updateByCriteria(VersionUpdate t, VersionUpdateCriteria criteria) {
		return versionUpdateMapper.updateByExample(t, criteria);
	}

	@Override
	public int deleteByCriteria(VersionUpdateCriteria criteria) {
		return versionUpdateMapper.deleteByExample(criteria);
	}

	@Override
	public List<VersionUpdate> queryAll(String orderByClause) {
		VersionUpdateCriteria criteria = new VersionUpdateCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<VersionUpdate> queryByCriteria(VersionUpdateCriteria criteria) {
		return versionUpdateMapper.selectByExample(criteria);
	}

	@Override
	public VersionUpdate loadByClientInfo(short clientType, int versionCode, String channel) {
		VersionUpdateCriteria criteria = new VersionUpdateCriteria();
		criteria.createCriteria().andClientTypeEqualTo(clientType).andVersionCodeEqualTo(versionCode).andChannelEqualTo(channel);
		List<VersionUpdate> versionList =   versionUpdateMapper.selectByExample(criteria);
		if(versionList!=null&&versionList.size()>0){
			return versionList.get(0);
		}
		return null;
	}
}
