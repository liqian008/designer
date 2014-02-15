package com.bruce.designer.data;


/**
 * 右侧的userbox中ajax请求后组装的数据结构
 * @author liqian
 *
 */
public class UserboxInfoBean{
	
	/*专辑数*/
    private int albumCount;
    /*关注状态*/
    private boolean hasFollowed;
	/*粉丝数*/
    private int fansCount;
    /*关注数*/
    private int followsCount;
    
    public UserboxInfoBean(){
    	super();
    }
    
    public UserboxInfoBean(boolean hasFollowed, int albumCount, int fansCount, int followsCount){
    	this.hasFollowed = hasFollowed;
    	this.albumCount = albumCount;
    	this.fansCount = fansCount;
    	this.followsCount = followsCount;
    }
    
    
	public int getFansCount() {
		return fansCount;
	}
	public void setFansCount(int fansCount) {
		this.fansCount = fansCount;
	}
	
	public int getFollowsCount() {
		return followsCount;
	}

	public void setFollowsCount(int followsCount) {
		this.followsCount = followsCount;
	}

	public int getAlbumCount() {
		return albumCount;
	}
	public void setAlbumCount(int albumCount) {
		this.albumCount = albumCount;
	}
	public boolean isHasFollowed() {
		return hasFollowed;
	}
	public void setHasFollowed(boolean hasFollowed) {
		this.hasFollowed = hasFollowed;
	}
    
    
}