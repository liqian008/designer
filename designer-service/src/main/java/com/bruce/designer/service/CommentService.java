package com.bruce.designer.service;

import java.util.List;

import com.bruce.designer.bean.TbComment;

public interface CommentService extends BaseService<TbComment, Long> {

	public List<TbComment> queryCommentsByAlbumId(int albumId);

}
