package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.model.Comment;
import com.bruce.designer.model.CommentCriteria;
import com.bruce.designer.dao.ICommentDao;
import com.bruce.designer.service.ICommentService;

@Service
public class CommentServiceImpl implements ICommentService {

	@Autowired
	private ICommentDao commentDao;

	public int save(Comment t) {
		return commentDao.save(t);
	}

	public List<Comment> queryAll() {
		return commentDao.queryAll();
	}

	public int updateById(Comment t) {
		return commentDao.updateById(t);
	}

	public int deleteById(Long id) {
		return commentDao.deleteById(id);
	}

	public Comment loadById(Long id) {
		return commentDao.loadById(id);
	}


	public List<Comment> queryCommentsByAlbumId(int albumId) {
		return commentDao.queryCommentsByAlbumId(albumId);
	}

	@Override
	public int comment(String title, String content, int albumId,
			int albumSlideId, int fromId, int toId, int designerId) {
		Comment comment = new Comment();
		comment.setTitle(title);
		comment.setComment(content);
		comment.setAlbumId(albumId);
		comment.setAlbumSlideId(albumSlideId);
		comment.setFromId(fromId);
		comment.setToId(toId);
		comment.setDesignerId(designerId);
		return save(comment);
	}

	@Override
	public int comment(String title, String content, int albumId,
			int albumSlideId, int fromId, int toId) {
		return comment(title, content, albumId, albumSlideId, fromId, toId, toId);
	}

}
