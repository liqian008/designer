package com.bruce.designer.bean;

import java.util.List;

public class TbAlbumBase{
	
	private List<TbComment> commentList;
	
	private List<TbAlbumSlide> slideList;

	public List<TbComment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<TbComment> commentList) {
		this.commentList = commentList;
	}

	public List<TbAlbumSlide> getSlideList() {
		return slideList;
	}

	public void setSlideList(List<TbAlbumSlide> slideList) {
		this.slideList = slideList;
	}
	
	
}