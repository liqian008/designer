package com.bruce.designer.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.bruce.designer.dao.IVoteDao;
import com.bruce.designer.dao.IVoteOptionDao;
import com.bruce.designer.dao.IVoteResultDao;
import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.model.Vote;
import com.bruce.designer.model.VoteCriteria;
import com.bruce.designer.model.VoteOption;
import com.bruce.designer.model.VoteResult;
import com.bruce.designer.model.VoteResultCriteria;
import com.bruce.designer.service.IVoteService;

@Service
public class VoteServiceImpl implements IVoteService, InitializingBean { 
	
	@Autowired
	private IVoteDao voteDao;
	@Autowired
	private IVoteOptionDao voteOptionDao;
	@Autowired
	private IVoteResultDao voteResultDao;
	

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
	public int vote(Integer userId, int voteId, int voteOptionId) {
		VoteResult voteResult = new VoteResult();
		voteResult.setUserId(userId);
		voteResult.setVoteId(voteId);
		voteResult.setVoteOptionId(voteOptionId);
		voteResult.setCreateTime(new Date());
		return voteResultDao.save(voteResult);
	}
	
	
	/*投票数据统计*/
	public List<CountCacheBean> queryVoteResultStat(int voteId){
		return voteResultDao.queryVoteResultStat(voteId);
	}
	
	
	@Override
	public Set<Integer> getUserVotedOptionSet(Integer userId, Integer voteId) {
		Set<Integer> votedOptionSet = new HashSet<Integer>();  
		VoteResultCriteria criteria = new VoteResultCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId).andVoteIdEqualTo(voteId);
		
		List<VoteResult> voteResultList = voteResultDao.queryByCriteria(criteria);
		if(voteResultList!=null&&voteResultList.size()>0){
			for(VoteResult loop: voteResultList){
				votedOptionSet.add(loop.getVoteOptionId());
			}
		}
		return votedOptionSet;
	}
	
}
