package com.bruce.designer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.bean.TbComment;
import com.bruce.designer.bean.TbCommentCriteria;
import com.bruce.designer.dao.TbCommentMapper;
import com.bruce.designer.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private TbCommentMapper commentMapper;

	public int save(TbComment t) {
		return commentMapper.insert(t);
	}

	public List<TbComment> queryAll() {
		return commentMapper.selectByExample(null);
	}

	public int updateById(TbComment t) {
		return commentMapper.updateByPrimaryKeySelective(t);
	}

	public int deleteById(Long id) {
		return commentMapper.deleteByPrimaryKey(id);
	}

	public TbComment loadById(Long id) {
		return commentMapper.selectByPrimaryKey(id);
	}


	public List<TbComment> queryCommentsByAlbumId(int albumId) {
		TbCommentCriteria criteria = new TbCommentCriteria();
		criteria.setOrderByClause("id desc");
		criteria.setOrderByClause("create_time desc");
		criteria.createCriteria().andAlbumIdEqualTo(albumId);
		return commentMapper.selectByExample(criteria);
	}

}
