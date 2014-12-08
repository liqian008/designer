package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.bruce.designer.dao.IVoteDao;
import com.bruce.designer.dao.IVoteOptionDao;
import com.bruce.designer.model.Vote;
import com.bruce.designer.model.VoteCriteria;
import com.bruce.designer.model.VoteOption;
import com.bruce.designer.service.IVoteService;

@Service
public class VoteServiceImpl implements IVoteService, InitializingBean { 
	
	@Autowired
	private IVoteDao voteDao;
	@Autowired
	private IVoteOptionDao voteOptionDao;
	

	public int save(Vote t) {
		return voteDao.save(t);
	}

	public List<Vote> queryAll() {
		return voteDao.queryAll();
	}

	public int updateById(Vote t) {
		return voteDao.updateById(t);
	}

	public int deleteById(Integer id) {
		return voteDao.deleteById(id);
	}

	public Vote loadById(Integer id) {
		return voteDao.loadById(id);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(voteDao, "voteDao can't be null");
	}

	
	
	

	
	@Override
	public int updateByCriteria(Vote t, VoteCriteria criteria) {
		return voteDao.updateByCriteria(t, criteria);
	}

	@Override
	public int deleteByCriteria(VoteCriteria criteria) {
		return voteDao.deleteByCriteria(criteria);
	}

	@Override
	public List<Vote> queryAll(String orderByClause) {
		return voteDao.queryAll(orderByClause);
	}

	@Override
	public List<Vote> queryByCriteria(VoteCriteria criteria) {
		return voteDao.queryByCriteria(criteria);
	}
	
	@Override
	public int countByCriteria(VoteCriteria criteria) {
		return voteDao.countByCriteria(criteria);
	}
	
	@Override
	public List<VoteOption> queryOptionsByVoteId(Integer id) {
		return voteOptionDao.loadOptionsByVoteId(id);
	}
	
	/*根据optionId获取vote信息*/
	public Vote loadByOptionId(int voteOptionId){
		return voteDao.loadByOptionId(voteOptionId);
	}

	@Override
	public int vote(int voteOptionId) {
		return 1;
	}
	
}
