package com.bruce.designer.model;

public class VoteOptionBase {

	/* 用户是否已投票 */
	private boolean voted;

	private int voteNum;

	public boolean isVoted() {
		return voted;
	}

	public void setVoted(boolean voted) {
		this.voted = voted;
	}

	public int getVoteNum() {
		return voteNum;
	}

	public void setVoteNum(int voteNum) {
		this.voteNum = voteNum;
	}

}