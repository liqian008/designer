package com.bruce.designer.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.designer.model.Comment;
import com.bruce.designer.model.CommentCriteria;
import com.bruce.designer.model.User;
import com.bruce.designer.constants.ConstRedis;
import com.bruce.designer.dao.ICommentDao;
import com.bruce.designer.service.ICommentService;
import com.bruce.designer.service.ICounterService;
import com.bruce.designer.service.IUserService;

@Service
public class CommentServiceImpl implements ICommentService {

	@Autowired
	IUserService userService;
	@Autowired
	private ICounterService counterService;

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
	public List<Comment> fallLoadComments(int albumSlideId, Long commentsTailId, int limit){
		return commentDao.fallLoadComments(albumSlideId, commentsTailId, limit);
	}


	/**
	 * 评论作品
	 */
	@Override
	public Comment comment(String title, String content, int albumId, int albumSlideId, int fromId, int toId, int designerId) {

		User fromUser = userService.loadById(fromId);
		String fromUserAvatar = fromUser.getHeadImg();
		String fromNickname = fromUser.getNickname();

		Comment comment = new Comment();
		comment.setTitle(title);
		comment.setComment(content);
		comment.setAlbumId(albumId);
		comment.setAlbumSlideId(albumSlideId);
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
			// 评论计量
//			counterService.increase(ConstRedis.COUNTER_KEY_ALBUM_COMMENT + albumId);
//			counterService.increase(ConstRedis.COUNTER_KEY_ALBUMSLIDE_COMMENT + albumSlideId);
			counterService.incrComment(designerId, albumId, albumSlideId);

			// 同时发送消息
			return comment;
		}
		return null;
	}

	/**
	 * 评论作品
	 */
	@Override
	public Comment comment(String title, String content, int albumId, int albumSlideId, int fromId, int toId) {
		return comment(title, content, albumId, albumSlideId, fromId, toId, toId);
	}

	/**
	 * 赞作品
	 */
	@Override
	public int like(int designerId, int albumId, int albumSlideId){
		// 评论计量
		long albumSlideLikeCount = counterService.incrLike(designerId, albumId, albumSlideId);
		
		// 同时发送消息
		// TODO 同时发送消息

		if (albumSlideLikeCount > 0) {
			return 1;
		}
		return 0;
	}

}
