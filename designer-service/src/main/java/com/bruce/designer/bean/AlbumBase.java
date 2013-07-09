package com.bruce.designer.bean;

import java.util.List;

public class AlbumBase{
	
	private List<Comment> commentList;
	
	private List<AlbumSlide> slideList;

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public List<AlbumSlide> getSlideList() {
		return slideList;
	}

	public void setSlideList(List<AlbumSlide> slideList) {
		this.slideList = slideList;
	}
	
	
}