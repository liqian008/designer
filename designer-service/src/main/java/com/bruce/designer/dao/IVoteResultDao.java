package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.model.VoteResult;
import com.bruce.designer.model.VoteResultCriteria;
import com.bruce.foundation.service.IFoundationService;

public interface IVoteResultDao extends IFoundationService<VoteResult, Integer, VoteResultCriteria> {
	
	/**
	 * 
	 * @param voteId
	 * @return
	 */
	List<CountCacheBean> queryVoteResultStat(int voteId);
		
}
