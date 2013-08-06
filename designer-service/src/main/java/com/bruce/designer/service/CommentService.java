package com.bruce.designer.service;

import java.util.List;

import com.bruce.baseSkeleton.service.IBaseService;

import com.bruce.designer.bean.Comment;

public interface CommentService extends IBaseService<Comment, Long> {

	public List<Comment> queryCommentsByAlbumId(int albumId);

}
