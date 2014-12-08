package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.model.Vote;
import com.bruce.designer.model.VoteCriteria;
import com.bruce.designer.model.VoteOption;
import com.bruce.foundation.service.IFoundationService;

public interface IVoteService extends IFoundationService<Vote, Integer, VoteCriteria> {

	/*获取选项列表*/
	public List<VoteOption> queryOptionsByVoteId(Integer id);
	
	/*根据optionId获取vote信息*/
	public Vote loadByOptionId(int voteOptionId);
	
	/*投票操作*/
	public int vote(int voteId, int voteOptionId);
	
	/*投票数据统计*/
	public List<CountCacheBean> queryVoteResultStat(int voteId);
	
}
