package com.bruce.designer.dao;

import com.bruce.designer.model.Vote;
import com.bruce.designer.model.VoteCriteria;
import com.bruce.foundation.service.IFoundationService;

public interface IVoteDao extends IFoundationService<Vote, Integer, VoteCriteria> {

	/*根据optionId获取vote信息*/
	public Vote loadByOptionId(int voteOptionId); 
	
}
