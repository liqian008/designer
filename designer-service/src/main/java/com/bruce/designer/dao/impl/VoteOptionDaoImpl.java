package com.bruce.designer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.dao.IVoteOptionDao;
import com.bruce.designer.dao.mapper.VoteOptionMapper;
import com.bruce.designer.model.VoteOption;
import com.bruce.designer.model.VoteOptionCriteria;

@Repository
public class VoteOptionDaoImpl implements IVoteOptionDao, InitializingBean {

	@Autowired
	private VoteOptionMapper voteOptionMapper;

	public int save(VoteOption t) {
		return voteOptionMapper.insertSelective(t);
	}

	public List<VoteOption> queryAll() {
		return voteOptionMapper.selectByExample(null);
	}

	public int updateById(VoteOption t) {
		return voteOptionMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Integer id) {
		return voteOptionMapper.deleteByPrimaryKey(id);
	}

	public VoteOption loadById(Integer id) {
		return voteOptionMapper.selectByPrimaryKey(id);
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	@Override
	public int updateByCriteria(VoteOption t, VoteOptionCriteria criteria) {
		return voteOptionMapper.updateByExample(t, criteria);
	}

	@Override
	public int deleteByCriteria(VoteOptionCriteria criteria) {
		return voteOptionMapper.deleteByExample(criteria);
	}

	@Override
	public List<VoteOption> queryAll(String orderByClause) {
		VoteOptionCriteria criteria = new VoteOptionCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<VoteOption> queryByCriteria(VoteOptionCriteria criteria) {
		return voteOptionMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(VoteOptionCriteria criteria) {
		return voteOptionMapper.countByExample(criteria);
	}

	@Override
	public List<VoteOption> loadOptionsByVoteId(Integer voteId) {
		VoteOptionCriteria criteria = new VoteOptionCriteria();
		criteria.createCriteria().andVoteIdEqualTo(voteId).andStatusEqualTo((short)1);
		criteria.setOrderByClause(" sort asc");
		return queryByCriteria(criteria);
	}
	
}
