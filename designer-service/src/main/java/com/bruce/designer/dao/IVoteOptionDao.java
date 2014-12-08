package com.bruce.designer.dao;

import java.util.List;

import com.bruce.designer.model.VoteOption;
import com.bruce.designer.model.VoteOptionCriteria;
import com.bruce.foundation.service.IFoundationService;

public interface IVoteOptionDao extends IFoundationService<VoteOption, Integer, VoteOptionCriteria> {

	/*获取投票的选项列表*/
	public List<VoteOption> loadOptionsByVoteId(Integer id);
	
	
}
