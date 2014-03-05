package com.bruce.designer.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlbumActionLogCriteria {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tb_album_action_log
	 * @mbggenerated  Wed Feb 26 00:04:05 CST 2014
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tb_album_action_log
	 * @mbggenerated  Wed Feb 26 00:04:05 CST 2014
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tb_album_action_log
	 * @mbggenerated  Wed Feb 26 00:04:05 CST 2014
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_action_log
	 * @mbggenerated  Wed Feb 26 00:04:05 CST 2014
	 */
	public AlbumActionLogCriteria() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_action_log
	 * @mbggenerated  Wed Feb 26 00:04:05 CST 2014
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_action_log
	 * @mbggenerated  Wed Feb 26 00:04:05 CST 2014
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_action_log
	 * @mbggenerated  Wed Feb 26 00:04:05 CST 2014
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_action_log
	 * @mbggenerated  Wed Feb 26 00:04:05 CST 2014
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_action_log
	 * @mbggenerated  Wed Feb 26 00:04:05 CST 2014
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_action_log
	 * @mbggenerated  Wed Feb 26 00:04:05 CST 2014
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_action_log
	 * @mbggenerated  Wed Feb 26 00:04:05 CST 2014
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_action_log
	 * @mbggenerated  Wed Feb 26 00:04:05 CST 2014
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_action_log
	 * @mbggenerated  Wed Feb 26 00:04:05 CST 2014
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_action_log
	 * @mbggenerated  Wed Feb 26 00:04:05 CST 2014
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table tb_album_action_log
	 * @mbggenerated  Wed Feb 26 00:04:05 CST 2014
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Long value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Long value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Long value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Long value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Long value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Long value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Long> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Long> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Long value1, Long value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Long value1, Long value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andAlbumIdIsNull() {
			addCriterion("album_id is null");
			return (Criteria) this;
		}

		public Criteria andAlbumIdIsNotNull() {
			addCriterion("album_id is not null");
			return (Criteria) this;
		}

		public Criteria andAlbumIdEqualTo(Integer value) {
			addCriterion("album_id =", value, "albumId");
			return (Criteria) this;
		}

		public Criteria andAlbumIdNotEqualTo(Integer value) {
			addCriterion("album_id <>", value, "albumId");
			return (Criteria) this;
		}

		public Criteria andAlbumIdGreaterThan(Integer value) {
			addCriterion("album_id >", value, "albumId");
			return (Criteria) this;
		}

		public Criteria andAlbumIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("album_id >=", value, "albumId");
			return (Criteria) this;
		}

		public Criteria andAlbumIdLessThan(Integer value) {
			addCriterion("album_id <", value, "albumId");
			return (Criteria) this;
		}

		public Criteria andAlbumIdLessThanOrEqualTo(Integer value) {
			addCriterion("album_id <=", value, "albumId");
			return (Criteria) this;
		}

		public Criteria andAlbumIdIn(List<Integer> values) {
			addCriterion("album_id in", values, "albumId");
			return (Criteria) this;
		}

		public Criteria andAlbumIdNotIn(List<Integer> values) {
			addCriterion("album_id not in", values, "albumId");
			return (Criteria) this;
		}

		public Criteria andAlbumIdBetween(Integer value1, Integer value2) {
			addCriterion("album_id between", value1, value2, "albumId");
			return (Criteria) this;
		}

		public Criteria andAlbumIdNotBetween(Integer value1, Integer value2) {
			addCriterion("album_id not between", value1, value2, "albumId");
			return (Criteria) this;
		}

		public Criteria andDesignerIdIsNull() {
			addCriterion("designer_id is null");
			return (Criteria) this;
		}

		public Criteria andDesignerIdIsNotNull() {
			addCriterion("designer_id is not null");
			return (Criteria) this;
		}

		public Criteria andDesignerIdEqualTo(Integer value) {
			addCriterion("designer_id =", value, "designerId");
			return (Criteria) this;
		}

		public Criteria andDesignerIdNotEqualTo(Integer value) {
			addCriterion("designer_id <>", value, "designerId");
			return (Criteria) this;
		}

		public Criteria andDesignerIdGreaterThan(Integer value) {
			addCriterion("designer_id >", value, "designerId");
			return (Criteria) this;
		}

		public Criteria andDesignerIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("designer_id >=", value, "designerId");
			return (Criteria) this;
		}

		public Criteria andDesignerIdLessThan(Integer value) {
			addCriterion("designer_id <", value, "designerId");
			return (Criteria) this;
		}

		public Criteria andDesignerIdLessThanOrEqualTo(Integer value) {
			addCriterion("designer_id <=", value, "designerId");
			return (Criteria) this;
		}

		public Criteria andDesignerIdIn(List<Integer> values) {
			addCriterion("designer_id in", values, "designerId");
			return (Criteria) this;
		}

		public Criteria andDesignerIdNotIn(List<Integer> values) {
			addCriterion("designer_id not in", values, "designerId");
			return (Criteria) this;
		}

		public Criteria andDesignerIdBetween(Integer value1, Integer value2) {
			addCriterion("designer_id between", value1, value2, "designerId");
			return (Criteria) this;
		}

		public Criteria andDesignerIdNotBetween(Integer value1, Integer value2) {
			addCriterion("designer_id not between", value1, value2, "designerId");
			return (Criteria) this;
		}

		public Criteria andUserIdIsNull() {
			addCriterion("user_id is null");
			return (Criteria) this;
		}

		public Criteria andUserIdIsNotNull() {
			addCriterion("user_id is not null");
			return (Criteria) this;
		}

		public Criteria andUserIdEqualTo(Integer value) {
			addCriterion("user_id =", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotEqualTo(Integer value) {
			addCriterion("user_id <>", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThan(Integer value) {
			addCriterion("user_id >", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("user_id >=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThan(Integer value) {
			addCriterion("user_id <", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdLessThanOrEqualTo(Integer value) {
			addCriterion("user_id <=", value, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdIn(List<Integer> values) {
			addCriterion("user_id in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotIn(List<Integer> values) {
			addCriterion("user_id not in", values, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdBetween(Integer value1, Integer value2) {
			addCriterion("user_id between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
			addCriterion("user_id not between", value1, value2, "userId");
			return (Criteria) this;
		}

		public Criteria andBrowseNumIsNull() {
			addCriterion("browse_num is null");
			return (Criteria) this;
		}

		public Criteria andBrowseNumIsNotNull() {
			addCriterion("browse_num is not null");
			return (Criteria) this;
		}

		public Criteria andBrowseNumEqualTo(Short value) {
			addCriterion("browse_num =", value, "browseNum");
			return (Criteria) this;
		}

		public Criteria andBrowseNumNotEqualTo(Short value) {
			addCriterion("browse_num <>", value, "browseNum");
			return (Criteria) this;
		}

		public Criteria andBrowseNumGreaterThan(Short value) {
			addCriterion("browse_num >", value, "browseNum");
			return (Criteria) this;
		}

		public Criteria andBrowseNumGreaterThanOrEqualTo(Short value) {
			addCriterion("browse_num >=", value, "browseNum");
			return (Criteria) this;
		}

		public Criteria andBrowseNumLessThan(Short value) {
			addCriterion("browse_num <", value, "browseNum");
			return (Criteria) this;
		}

		public Criteria andBrowseNumLessThanOrEqualTo(Short value) {
			addCriterion("browse_num <=", value, "browseNum");
			return (Criteria) this;
		}

		public Criteria andBrowseNumIn(List<Short> values) {
			addCriterion("browse_num in", values, "browseNum");
			return (Criteria) this;
		}

		public Criteria andBrowseNumNotIn(List<Short> values) {
			addCriterion("browse_num not in", values, "browseNum");
			return (Criteria) this;
		}

		public Criteria andBrowseNumBetween(Short value1, Short value2) {
			addCriterion("browse_num between", value1, value2, "browseNum");
			return (Criteria) this;
		}

		public Criteria andBrowseNumNotBetween(Short value1, Short value2) {
			addCriterion("browse_num not between", value1, value2, "browseNum");
			return (Criteria) this;
		}

		public Criteria andLikeNumIsNull() {
			addCriterion("like_num is null");
			return (Criteria) this;
		}

		public Criteria andLikeNumIsNotNull() {
			addCriterion("like_num is not null");
			return (Criteria) this;
		}

		public Criteria andLikeNumEqualTo(Short value) {
			addCriterion("like_num =", value, "likeNum");
			return (Criteria) this;
		}

		public Criteria andLikeNumNotEqualTo(Short value) {
			addCriterion("like_num <>", value, "likeNum");
			return (Criteria) this;
		}

		public Criteria andLikeNumGreaterThan(Short value) {
			addCriterion("like_num >", value, "likeNum");
			return (Criteria) this;
		}

		public Criteria andLikeNumGreaterThanOrEqualTo(Short value) {
			addCriterion("like_num >=", value, "likeNum");
			return (Criteria) this;
		}

		public Criteria andLikeNumLessThan(Short value) {
			addCriterion("like_num <", value, "likeNum");
			return (Criteria) this;
		}

		public Criteria andLikeNumLessThanOrEqualTo(Short value) {
			addCriterion("like_num <=", value, "likeNum");
			return (Criteria) this;
		}

		public Criteria andLikeNumIn(List<Short> values) {
			addCriterion("like_num in", values, "likeNum");
			return (Criteria) this;
		}

		public Criteria andLikeNumNotIn(List<Short> values) {
			addCriterion("like_num not in", values, "likeNum");
			return (Criteria) this;
		}

		public Criteria andLikeNumBetween(Short value1, Short value2) {
			addCriterion("like_num between", value1, value2, "likeNum");
			return (Criteria) this;
		}

		public Criteria andLikeNumNotBetween(Short value1, Short value2) {
			addCriterion("like_num not between", value1, value2, "likeNum");
			return (Criteria) this;
		}

		public Criteria andFavoriteNumIsNull() {
			addCriterion("favorite_num is null");
			return (Criteria) this;
		}

		public Criteria andFavoriteNumIsNotNull() {
			addCriterion("favorite_num is not null");
			return (Criteria) this;
		}

		public Criteria andFavoriteNumEqualTo(Short value) {
			addCriterion("favorite_num =", value, "favoriteNum");
			return (Criteria) this;
		}

		public Criteria andFavoriteNumNotEqualTo(Short value) {
			addCriterion("favorite_num <>", value, "favoriteNum");
			return (Criteria) this;
		}

		public Criteria andFavoriteNumGreaterThan(Short value) {
			addCriterion("favorite_num >", value, "favoriteNum");
			return (Criteria) this;
		}

		public Criteria andFavoriteNumGreaterThanOrEqualTo(Short value) {
			addCriterion("favorite_num >=", value, "favoriteNum");
			return (Criteria) this;
		}

		public Criteria andFavoriteNumLessThan(Short value) {
			addCriterion("favorite_num <", value, "favoriteNum");
			return (Criteria) this;
		}

		public Criteria andFavoriteNumLessThanOrEqualTo(Short value) {
			addCriterion("favorite_num <=", value, "favoriteNum");
			return (Criteria) this;
		}

		public Criteria andFavoriteNumIn(List<Short> values) {
			addCriterion("favorite_num in", values, "favoriteNum");
			return (Criteria) this;
		}

		public Criteria andFavoriteNumNotIn(List<Short> values) {
			addCriterion("favorite_num not in", values, "favoriteNum");
			return (Criteria) this;
		}

		public Criteria andFavoriteNumBetween(Short value1, Short value2) {
			addCriterion("favorite_num between", value1, value2, "favoriteNum");
			return (Criteria) this;
		}

		public Criteria andFavoriteNumNotBetween(Short value1, Short value2) {
			addCriterion("favorite_num not between", value1, value2, "favoriteNum");
			return (Criteria) this;
		}

		public Criteria andCommentNumIsNull() {
			addCriterion("comment_num is null");
			return (Criteria) this;
		}

		public Criteria andCommentNumIsNotNull() {
			addCriterion("comment_num is not null");
			return (Criteria) this;
		}

		public Criteria andCommentNumEqualTo(Short value) {
			addCriterion("comment_num =", value, "commentNum");
			return (Criteria) this;
		}

		public Criteria andCommentNumNotEqualTo(Short value) {
			addCriterion("comment_num <>", value, "commentNum");
			return (Criteria) this;
		}

		public Criteria andCommentNumGreaterThan(Short value) {
			addCriterion("comment_num >", value, "commentNum");
			return (Criteria) this;
		}

		public Criteria andCommentNumGreaterThanOrEqualTo(Short value) {
			addCriterion("comment_num >=", value, "commentNum");
			return (Criteria) this;
		}

		public Criteria andCommentNumLessThan(Short value) {
			addCriterion("comment_num <", value, "commentNum");
			return (Criteria) this;
		}

		public Criteria andCommentNumLessThanOrEqualTo(Short value) {
			addCriterion("comment_num <=", value, "commentNum");
			return (Criteria) this;
		}

		public Criteria andCommentNumIn(List<Short> values) {
			addCriterion("comment_num in", values, "commentNum");
			return (Criteria) this;
		}

		public Criteria andCommentNumNotIn(List<Short> values) {
			addCriterion("comment_num not in", values, "commentNum");
			return (Criteria) this;
		}

		public Criteria andCommentNumBetween(Short value1, Short value2) {
			addCriterion("comment_num between", value1, value2, "commentNum");
			return (Criteria) this;
		}

		public Criteria andCommentNumNotBetween(Short value1, Short value2) {
			addCriterion("comment_num not between", value1, value2, "commentNum");
			return (Criteria) this;
		}

		public Criteria andAlbumStatusIsNull() {
			addCriterion("album_status is null");
			return (Criteria) this;
		}

		public Criteria andAlbumStatusIsNotNull() {
			addCriterion("album_status is not null");
			return (Criteria) this;
		}

		public Criteria andAlbumStatusEqualTo(Short value) {
			addCriterion("album_status =", value, "albumStatus");
			return (Criteria) this;
		}

		public Criteria andAlbumStatusNotEqualTo(Short value) {
			addCriterion("album_status <>", value, "albumStatus");
			return (Criteria) this;
		}

		public Criteria andAlbumStatusGreaterThan(Short value) {
			addCriterion("album_status >", value, "albumStatus");
			return (Criteria) this;
		}

		public Criteria andAlbumStatusGreaterThanOrEqualTo(Short value) {
			addCriterion("album_status >=", value, "albumStatus");
			return (Criteria) this;
		}

		public Criteria andAlbumStatusLessThan(Short value) {
			addCriterion("album_status <", value, "albumStatus");
			return (Criteria) this;
		}

		public Criteria andAlbumStatusLessThanOrEqualTo(Short value) {
			addCriterion("album_status <=", value, "albumStatus");
			return (Criteria) this;
		}

		public Criteria andAlbumStatusIn(List<Short> values) {
			addCriterion("album_status in", values, "albumStatus");
			return (Criteria) this;
		}

		public Criteria andAlbumStatusNotIn(List<Short> values) {
			addCriterion("album_status not in", values, "albumStatus");
			return (Criteria) this;
		}

		public Criteria andAlbumStatusBetween(Short value1, Short value2) {
			addCriterion("album_status between", value1, value2, "albumStatus");
			return (Criteria) this;
		}

		public Criteria andAlbumStatusNotBetween(Short value1, Short value2) {
			addCriterion("album_status not between", value1, value2, "albumStatus");
			return (Criteria) this;
		}

		public Criteria andDesignerStatusIsNull() {
			addCriterion("designer_status is null");
			return (Criteria) this;
		}

		public Criteria andDesignerStatusIsNotNull() {
			addCriterion("designer_status is not null");
			return (Criteria) this;
		}

		public Criteria andDesignerStatusEqualTo(Short value) {
			addCriterion("designer_status =", value, "designerStatus");
			return (Criteria) this;
		}

		public Criteria andDesignerStatusNotEqualTo(Short value) {
			addCriterion("designer_status <>", value, "designerStatus");
			return (Criteria) this;
		}

		public Criteria andDesignerStatusGreaterThan(Short value) {
			addCriterion("designer_status >", value, "designerStatus");
			return (Criteria) this;
		}

		public Criteria andDesignerStatusGreaterThanOrEqualTo(Short value) {
			addCriterion("designer_status >=", value, "designerStatus");
			return (Criteria) this;
		}

		public Criteria andDesignerStatusLessThan(Short value) {
			addCriterion("designer_status <", value, "designerStatus");
			return (Criteria) this;
		}

		public Criteria andDesignerStatusLessThanOrEqualTo(Short value) {
			addCriterion("designer_status <=", value, "designerStatus");
			return (Criteria) this;
		}

		public Criteria andDesignerStatusIn(List<Short> values) {
			addCriterion("designer_status in", values, "designerStatus");
			return (Criteria) this;
		}

		public Criteria andDesignerStatusNotIn(List<Short> values) {
			addCriterion("designer_status not in", values, "designerStatus");
			return (Criteria) this;
		}

		public Criteria andDesignerStatusBetween(Short value1, Short value2) {
			addCriterion("designer_status between", value1, value2, "designerStatus");
			return (Criteria) this;
		}

		public Criteria andDesignerStatusNotBetween(Short value1, Short value2) {
			addCriterion("designer_status not between", value1, value2, "designerStatus");
			return (Criteria) this;
		}

		public Criteria andStatusIsNull() {
			addCriterion("status is null");
			return (Criteria) this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("status is not null");
			return (Criteria) this;
		}

		public Criteria andStatusEqualTo(Short value) {
			addCriterion("status =", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotEqualTo(Short value) {
			addCriterion("status <>", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThan(Short value) {
			addCriterion("status >", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(Short value) {
			addCriterion("status >=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThan(Short value) {
			addCriterion("status <", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThanOrEqualTo(Short value) {
			addCriterion("status <=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusIn(List<Short> values) {
			addCriterion("status in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotIn(List<Short> values) {
			addCriterion("status not in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusBetween(Short value1, Short value2) {
			addCriterion("status between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotBetween(Short value1, Short value2) {
			addCriterion("status not between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIsNull() {
			addCriterion("create_time is null");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIsNotNull() {
			addCriterion("create_time is not null");
			return (Criteria) this;
		}

		public Criteria andCreateTimeEqualTo(Date value) {
			addCriterion("create_time =", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotEqualTo(Date value) {
			addCriterion("create_time <>", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeGreaterThan(Date value) {
			addCriterion("create_time >", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("create_time >=", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeLessThan(Date value) {
			addCriterion("create_time <", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
			addCriterion("create_time <=", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIn(List<Date> values) {
			addCriterion("create_time in", values, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotIn(List<Date> values) {
			addCriterion("create_time not in", values, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeBetween(Date value1, Date value2) {
			addCriterion("create_time between", value1, value2, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
			addCriterion("create_time not between", value1, value2, "createTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeIsNull() {
			addCriterion("update_time is null");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeIsNotNull() {
			addCriterion("update_time is not null");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeEqualTo(Date value) {
			addCriterion("update_time =", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeNotEqualTo(Date value) {
			addCriterion("update_time <>", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeGreaterThan(Date value) {
			addCriterion("update_time >", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("update_time >=", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeLessThan(Date value) {
			addCriterion("update_time <", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
			addCriterion("update_time <=", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeIn(List<Date> values) {
			addCriterion("update_time in", values, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeNotIn(List<Date> values) {
			addCriterion("update_time not in", values, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeBetween(Date value1, Date value2) {
			addCriterion("update_time between", value1, value2, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
			addCriterion("update_time not between", value1, value2, "updateTime");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table tb_album_action_log
	 * @mbggenerated  Wed Feb 26 00:04:05 CST 2014
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_album_action_log
     *
     * @mbggenerated do_not_delete_during_merge Mon Feb 24 22:03:10 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
    
    private Integer albumBrowseScore;
    private Integer albumCommentScore;
    private Integer albumLikeScore;
    private Integer albumFavoriteScore;
    
    private Integer designerBrowseScore;
    private Integer designerCommentScore;
    private Integer designerLikeScore;
    private Integer designerFavoriteScore;
    
    private Integer start;
    private Integer limit;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

	public Integer getAlbumBrowseScore() {
		return albumBrowseScore;
	}

	public void setAlbumBrowseScore(Integer albumBrowseScore) {
		this.albumBrowseScore = albumBrowseScore;
	}

	public Integer getAlbumCommentScore() {
		return albumCommentScore;
	}

	public void setAlbumCommentScore(Integer albumCommentScore) {
		this.albumCommentScore = albumCommentScore;
	}

	public Integer getAlbumLikeScore() {
		return albumLikeScore;
	}

	public void setAlbumLikeScore(Integer albumLikeScore) {
		this.albumLikeScore = albumLikeScore;
	}

	public Integer getAlbumFavoriteScore() {
		return albumFavoriteScore;
	}

	public void setAlbumFavoriteScore(Integer albumFavoriteScore) {
		this.albumFavoriteScore = albumFavoriteScore;
	}

	public Integer getDesignerBrowseScore() {
		return designerBrowseScore;
	}

	public void setDesignerBrowseScore(Integer designerBrowseScore) {
		this.designerBrowseScore = designerBrowseScore;
	}

	public Integer getDesignerCommentScore() {
		return designerCommentScore;
	}

	public void setDesignerCommentScore(Integer designerCommentScore) {
		this.designerCommentScore = designerCommentScore;
	}

	public Integer getDesignerLikeScore() {
		return designerLikeScore;
	}

	public void setDesignerLikeScore(Integer designerLikeScore) {
		this.designerLikeScore = designerLikeScore;
	}

	public Integer getDesignerFavoriteScore() {
		return designerFavoriteScore;
	}

	public void setDesignerFavoriteScore(Integer designerFavoriteScore) {
		this.designerFavoriteScore = designerFavoriteScore;
	}

	
    
}