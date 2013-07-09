package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.bean.Comment;

public interface CommentService extends BaseService<Comment, Long> {

	public List<Comment> queryCommentsByAlbumId(int albumId);

}
