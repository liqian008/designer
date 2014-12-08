package com.bruce.designer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.dao.IVoteResultDao;
import com.bruce.designer.dao.mapper.VoteResultMapper;
import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.model.VoteResult;
import com.bruce.designer.model.VoteResultCriteria;

@Repository
public class VoteResultDaoImpl implements IVoteResultDao, InitializingBean {

	@Autowired
	private VoteResultMapper voteResultMapper;

	public int save(VoteResult t) {
		return voteResultMapper.insertSelective(t);
	}

	public List<VoteResult> queryAll() {
		return voteResultMapper.selectByExample(null);
	}

	public int updateById(VoteResult t) {
		return voteResultMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Integer id) {
		return voteResultMapper.deleteByPrimaryKey(id);
	}

	public VoteResult loadById(Integer id) {
		return voteResultMapper.selectByPrimaryKey(id);
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	@Override
	public int updateByCriteria(VoteResult t, VoteResultCriteria criteria) {
		return voteResultMapper.updateByExample(t, criteria);
	}

	@Override
	public int deleteByCriteria(VoteResultCriteria criteria) {
		return voteResultMapper.deleteByExample(criteria);
	}

	@Override
	public List<VoteResult> queryAll(String orderByClause) {
		VoteResultCriteria criteria = new VoteResultCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<VoteResult> queryByCriteria(VoteResultCriteria criteria) {
		return voteResultMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(VoteResultCriteria criteria) {
		return voteResultMapper.countByExample(criteria);
	}
	
	@Override
	public List<CountCacheBean> queryVoteResultStat(int voteId) {
		return voteResultMapper.queryVoteResultStat(voteId);
	}
	
}
