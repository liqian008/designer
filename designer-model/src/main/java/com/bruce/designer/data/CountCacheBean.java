package com.bruce.designer.data;


public class CountCacheBean{

	private String member;
    private long score;
    
    public CountCacheBean(){
    	super();
    }
    
    public CountCacheBean(String member, long score){
    	super();
    	this.member = member;
    	this.score = score;
    }

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}
    
	
    
}