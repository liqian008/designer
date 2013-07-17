package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.bean.Comment;
import com.bruce.designer.bean.CommentCriteria;
import com.bruce.designer.dao.CommentMapper;
import com.bruce.designer.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentMapper commentMapper;

	public int save(Comment t) {
		return commentMapper.insert(t);
	}

	public List<Comment> queryAll() {
		return commentMapper.selectByExample(null);
	}

	public int updateById(Comment t) {
		return commentMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Long id) {
		return commentMapper.deleteByPrimaryKey(id);
	}

	public Comment loadById(Long id) {
		return commentMapper.selectByPrimaryKey(id);
	}


	public List<Comment> queryCommentsByAlbumId(int albumId) {
		CommentCriteria criteria = new CommentCriteria();
		criteria.setOrderByClause("id desc");
		criteria.setOrderByClause("create_time desc");
		criteria.createCriteria().andAlbumIdEqualTo(albumId);
		return commentMapper.selectByExample(criteria);
	}

}
