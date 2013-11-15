package com.bruce.designer.model;

import java.util.List;

public class AlbumBase {

	private long browseCount;

	private long commentCount;

	private long likeCount;

	private long favoriteCount;

	private List<String> tagList;

	private List<AlbumSlide> slideList;

	public long getBrowseCount() {
		return browseCount;
	}

	public void setBrowseCount(long browseCount) {
		this.browseCount = browseCount;
	}

	public long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(long commentCount) {
		this.commentCount = commentCount;
	}

	public long getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(long likeCount) {
		this.likeCount = likeCount;
	}

	public long getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(long favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public List<String> getTagList() {
		return tagList;
	}

	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}

	public List<AlbumSlide> getSlideList() {
		return slideList;
	}

	public void setSlideList(List<AlbumSlide> slideList) {
		this.slideList = slideList;
	}

}