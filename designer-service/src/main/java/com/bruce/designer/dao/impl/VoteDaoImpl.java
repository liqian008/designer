package com.bruce.designer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.dao.IVoteDao;
import com.bruce.designer.dao.mapper.VoteMapper;
import com.bruce.designer.model.Vote;
import com.bruce.designer.model.VoteCriteria;
import com.bruce.designer.model.VoteOption;
import com.bruce.designer.model.VoteOptionCriteria;

@Repository
public class VoteDaoImpl implements IVoteDao, InitializingBean {

	@Autowired
	private VoteMapper voteMapper;

	public int save(Vote t) {
		return voteMapper.insertSelective(t);
	}

	public List<Vote> queryAll() {
		return voteMapper.selectByExample(null);
	}

	public int updateById(Vote t) {
		return voteMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Integer id) {
		return voteMapper.deleteByPrimaryKey(id);
	}

	public Vote loadById(Integer id) {
		return voteMapper.selectByPrimaryKey(id);
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	@Override
	public int updateByCriteria(Vote t, VoteCriteria criteria) {
		return voteMapper.updateByExample(t, criteria);
	}

	@Override
	public int deleteByCriteria(VoteCriteria criteria) {
		return voteMapper.deleteByExample(criteria);
	}

	@Override
	public List<Vote> queryAll(String orderByClause) {
		VoteCriteria criteria = new VoteCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<Vote> queryByCriteria(VoteCriteria criteria) {
		return voteMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(VoteCriteria criteria) {
		return voteMapper.countByExample(criteria);
	}
	
	@Override
	public Vote loadByOptionId(int voteOptionId) {
		return voteMapper.loadByOptionId(voteOptionId);
	}
	
}
