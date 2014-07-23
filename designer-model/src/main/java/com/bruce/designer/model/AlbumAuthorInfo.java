package com.bruce.designer.model;

public class AlbumAuthorInfo {
	
	private String designerAvatar;
	
	private String designerNickname;
	/*关注状态*/
	private boolean followed;
	
	
	public AlbumAuthorInfo(String designerAvatar, String designerNickname,
			boolean followed) {
		super();
		this.designerAvatar = designerAvatar;
		this.designerNickname = designerNickname;
		this.followed = followed;
	}
	public String getDesignerAvatar() {
		return designerAvatar;
	}
	public void setDesignerAvatar(String designerAvatar) {
		this.designerAvatar = designerAvatar;
	}
	public String getDesignerNickname() {
		return designerNickname;
	}
	public void setDesignerNickname(String designerNickname) {
		this.designerNickname = designerNickname;
	}
	public boolean isFollowed() {
		return followed;
	}
	public void setFollowed(boolean followed) {
		this.followed = followed;
	}
}
