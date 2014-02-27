package com.bruce.designer.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.designer.dao.ICommentDao;
import com.bruce.designer.dao.mapper.CommentMapper;
import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.model.Comment;
import com.bruce.designer.model.CommentCriteria;

@Repository
public class CommentDaoImpl implements ICommentDao , InitializingBean {

	@Autowired
	private CommentMapper commentMapper;

	public int save(Comment t) {
		return commentMapper.insertSelective(t);
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

//	@Override
//	public List<Comment> queryCommentsByAlbumId(int albumId) {
//		CommentCriteria criteria = new CommentCriteria();
//		criteria.setOrderByClause("create_time desc");
//		criteria.createCriteria().andAlbumIdEqualTo(albumId);
//		return commentMapper.selectByExample(criteria);
//	}
//	
//	@Override
//	public List<Comment> queryCommentsByAlbumSlideId(int albumSlideId) {
//		CommentCriteria criteria = new CommentCriteria();
//		criteria.setOrderByClause("create_time desc");
//		criteria.createCriteria().andAlbumSlideIdEqualTo(albumSlideId);
//		return commentMapper.selectByExample(criteria);
//	}

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

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
	public List<Comment> fallLoadList(Long tailId, int limit) {
		return null;
	}
    
	@Override
	public List<Comment> fallLoadComments(int albumId, Long tailId, int limit) {
		CommentCriteria commentCriteria = new CommentCriteria();
		CommentCriteria.Criteria queryCriteria = commentCriteria.createCriteria().andAlbumIdEqualTo(albumId);
		if(tailId!=null&&tailId>0){
			queryCriteria.andIdLessThan(tailId);
		}
		commentCriteria.setLimit(limit);
		commentCriteria.setOrderByClause("id desc");
        List<Comment> commentList = commentMapper.selectByExample(commentCriteria);
        return commentList;
	}
	
	
    /**
     * 分组查询评论数据，为重建索引提供数据
     */
    @Override
    public List<CountCacheBean> queryCommentStat() {
        // select album_id, count(album_id) total_num from tb_comment where status=1 group by album_id;
        return commentMapper.queryCommentStat();
    }
}
