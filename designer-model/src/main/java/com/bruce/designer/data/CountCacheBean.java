package com.bruce.designer.data;


public class CountCacheBean{

	private int member;
    private long score;
    
    public CountCacheBean(){
    	super();
    }
    
    public CountCacheBean(int member, long score){
    	super();
    	this.member = member;
    	this.score = score;
    }

	public int getMember() {
		return member;
	}

	public void setMember(int member) {
		this.member = member;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}
    
	
    
}