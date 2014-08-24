package com.bruce.designer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.designer.constants.ConstService;
import com.bruce.designer.dao.ICommentDao;
import com.bruce.designer.data.CountCacheBean;
import com.bruce.designer.model.Comment;
import com.bruce.designer.model.CommentCriteria;
import com.bruce.designer.model.User;
import com.bruce.designer.service.IAlbumCommentService;
import com.bruce.designer.service.IAlbumCounterService;
import com.bruce.designer.service.IMessageService;
import com.bruce.designer.service.IUserService;

@Service
public class AlbumCommentServiceImpl implements IAlbumCommentService {

	@Autowired
	IUserService userService;
//	@Autowired
//	private ICounterService counterService;
	@Autowired
	private IAlbumCounterService albumCounterService;
	@Autowired
	private IMessageService messageService;

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

//	public List<Comment> queryCommentsByAlbumSlideId(int albumSlideId) {
//		return commentDao.queryCommentsByAlbumSlideId(albumSlideId);
//	}
//
//	public List<Comment> queryCommentsByAlbumId(int albumId) {
//		return commentDao.queryCommentsByAlbumId(albumId);
//	}
	
	@Override
	public List<Comment> fallLoadComments(int albumId, long commentsTailId, int limit){
		return commentDao.fallLoadComments(albumId, commentsTailId, limit);
	}


	/**
	 * 评论作品
	 */
	@Override
	public Comment comment(String title, String content, int albumId, int fromId, int toId, int designerId) {

		User fromUser = userService.loadById(fromId);
		String fromUserAvatar = fromUser.getHeadImg();
		String fromNickname = fromUser.getNickname();
		
		//校验toId是否正确，如不正确，则使用designerId
		User replyUser = userService.loadById(toId);
		String prefix = "回复"+replyUser.getNickname()+":";
		
//		List<Integer> toIdList = new ArrayList<Integer>();
		Set<Integer> toIdSet  = new HashSet<Integer>();
		if(fromId!=designerId && fromId!=toId){//自己回复评论情况下，不额外发送评论消息
			toIdSet.add(designerId);
		}
		if(replyUser!=null&&content.startsWith(prefix)){//判断toId是否有效
			if(replyUser.getId()!=designerId){
				//判断和designerId是否是同一个人，避免重复发送消息
				toIdSet.add(replyUser.getId());
			}
		}else{
			toId = designerId;
		}
		
		Comment comment = new Comment();
		comment.setTitle(title);
		comment.setComment(content);
		comment.setAlbumId(albumId);
		comment.setFromId(fromId);
		comment.setNickname(fromNickname);
		comment.setUserHeadImg(fromUserAvatar);
		comment.setToId(toId);
		comment.setDesignerId(designerId);
		Date currentTime = new Date();
		comment.setCreateTime(currentTime);
		comment.setUpdateTime(currentTime);
		int result = save(comment);

		if (result > 0) {
			// 评论计数
			albumCounterService.incrComment(designerId, albumId, fromId);

			// 同时发送消息
			if(toIdSet!=null&&toIdSet.size()>0){
				content = content.replace(prefix, "").trim();
//				String messageContent = fromNickname+": " + content;
				messageService.sendMessage(albumId, fromId, toIdSet, content, ConstService.MESSAGE_TYPE_COMMENT);
			}
			
			return comment;
		}
		return null;
	}

	/**
	 * 评论作品
	 */
	@Override
	public Comment comment(String title, String content, int albumId, int fromId, int toId) {
		return comment(title, content, albumId, fromId, toId, toId);
	}

	/**
     * 分组查询评论数据，为重建索引提供数据
     */
    @Override
    public List<CountCacheBean> queryCommentStat() {
        return commentDao.queryCommentStat();
    }

    
    
    
    
	@Override
	public int updateByCriteria(Comment t, CommentCriteria criteria) {
		return commentDao.updateByCriteria(t, criteria);
	}

	@Override
	public int deleteByCriteria(CommentCriteria criteria) {
		return commentDao.deleteByCriteria(criteria);
	}

	@Override
	public List<Comment> queryAll(String orderByClause) {
		return commentDao.queryAll(orderByClause);
	}

	@Override
	public List<Comment> queryByCriteria(CommentCriteria criteria) {
		return commentDao.queryByCriteria(criteria);
	}

}
