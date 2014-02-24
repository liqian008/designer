package com.bruce.designer.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlbumCounterCriteria {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tb_album_counter
	 * @mbggenerated  Sun Feb 16 10:16:39 CST 2014
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tb_album_counter
	 * @mbggenerated  Sun Feb 16 10:16:39 CST 2014
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tb_album_counter
	 * @mbggenerated  Sun Feb 16 10:16:39 CST 2014
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_counter
	 * @mbggenerated  Sun Feb 16 10:16:39 CST 2014
	 */
	public AlbumCounterCriteria() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_counter
	 * @mbggenerated  Sun Feb 16 10:16:39 CST 2014
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_counter
	 * @mbggenerated  Sun Feb 16 10:16:39 CST 2014
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_counter
	 * @mbggenerated  Sun Feb 16 10:16:39 CST 2014
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_counter
	 * @mbggenerated  Sun Feb 16 10:16:39 CST 2014
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_counter
	 * @mbggenerated  Sun Feb 16 10:16:39 CST 2014
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_counter
	 * @mbggenerated  Sun Feb 16 10:16:39 CST 2014
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_counter
	 * @mbggenerated  Sun Feb 16 10:16:39 CST 2014
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_counter
	 * @mbggenerated  Sun Feb 16 10:16:39 CST 2014
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_counter
	 * @mbggenerated  Sun Feb 16 10:16:39 CST 2014
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_album_counter
	 * @mbggenerated  Sun Feb 16 10:16:39 CST 2014
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table tb_album_counter
	 * @mbggenerated  Sun Feb 16 10:16:39 CST 2014
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

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
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

		public Criteria andBrowseCountIsNull() {
			addCriterion("browse_count is null");
			return (Criteria) this;
		}

		public Criteria andBrowseCountIsNotNull() {
			addCriterion("browse_count is not null");
			return (Criteria) this;
		}

		public Criteria andBrowseCountEqualTo(Integer value) {
			addCriterion("browse_count =", value, "browseCount");
			return (Criteria) this;
		}

		public Criteria andBrowseCountNotEqualTo(Integer value) {
			addCriterion("browse_count <>", value, "browseCount");
			return (Criteria) this;
		}

		public Criteria andBrowseCountGreaterThan(Integer value) {
			addCriterion("browse_count >", value, "browseCount");
			return (Criteria) this;
		}

		public Criteria andBrowseCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("browse_count >=", value, "browseCount");
			return (Criteria) this;
		}

		public Criteria andBrowseCountLessThan(Integer value) {
			addCriterion("browse_count <", value, "browseCount");
			return (Criteria) this;
		}

		public Criteria andBrowseCountLessThanOrEqualTo(Integer value) {
			addCriterion("browse_count <=", value, "browseCount");
			return (Criteria) this;
		}

		public Criteria andBrowseCountIn(List<Integer> values) {
			addCriterion("browse_count in", values, "browseCount");
			return (Criteria) this;
		}

		public Criteria andBrowseCountNotIn(List<Integer> values) {
			addCriterion("browse_count not in", values, "browseCount");
			return (Criteria) this;
		}

		public Criteria andBrowseCountBetween(Integer value1, Integer value2) {
			addCriterion("browse_count between", value1, value2, "browseCount");
			return (Criteria) this;
		}

		public Criteria andBrowseCountNotBetween(Integer value1, Integer value2) {
			addCriterion("browse_count not between", value1, value2, "browseCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountIsNull() {
			addCriterion("comment_count is null");
			return (Criteria) this;
		}

		public Criteria andCommentCountIsNotNull() {
			addCriterion("comment_count is not null");
			return (Criteria) this;
		}

		public Criteria andCommentCountEqualTo(Integer value) {
			addCriterion("comment_count =", value, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountNotEqualTo(Integer value) {
			addCriterion("comment_count <>", value, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountGreaterThan(Integer value) {
			addCriterion("comment_count >", value, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("comment_count >=", value, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountLessThan(Integer value) {
			addCriterion("comment_count <", value, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountLessThanOrEqualTo(Integer value) {
			addCriterion("comment_count <=", value, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountIn(List<Integer> values) {
			addCriterion("comment_count in", values, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountNotIn(List<Integer> values) {
			addCriterion("comment_count not in", values, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountBetween(Integer value1, Integer value2) {
			addCriterion("comment_count between", value1, value2, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountNotBetween(Integer value1, Integer value2) {
			addCriterion("comment_count not between", value1, value2, "commentCount");
			return (Criteria) this;
		}

		public Criteria andLikeCountIsNull() {
			addCriterion("like_count is null");
			return (Criteria) this;
		}

		public Criteria andLikeCountIsNotNull() {
			addCriterion("like_count is not null");
			return (Criteria) this;
		}

		public Criteria andLikeCountEqualTo(Integer value) {
			addCriterion("like_count =", value, "likeCount");
			return (Criteria) this;
		}

		public Criteria andLikeCountNotEqualTo(Integer value) {
			addCriterion("like_count <>", value, "likeCount");
			return (Criteria) this;
		}

		public Criteria andLikeCountGreaterThan(Integer value) {
			addCriterion("like_count >", value, "likeCount");
			return (Criteria) this;
		}

		public Criteria andLikeCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("like_count >=", value, "likeCount");
			return (Criteria) this;
		}

		public Criteria andLikeCountLessThan(Integer value) {
			addCriterion("like_count <", value, "likeCount");
			return (Criteria) this;
		}

		public Criteria andLikeCountLessThanOrEqualTo(Integer value) {
			addCriterion("like_count <=", value, "likeCount");
			return (Criteria) this;
		}

		public Criteria andLikeCountIn(List<Integer> values) {
			addCriterion("like_count in", values, "likeCount");
			return (Criteria) this;
		}

		public Criteria andLikeCountNotIn(List<Integer> values) {
			addCriterion("like_count not in", values, "likeCount");
			return (Criteria) this;
		}

		public Criteria andLikeCountBetween(Integer value1, Integer value2) {
			addCriterion("like_count between", value1, value2, "likeCount");
			return (Criteria) this;
		}

		public Criteria andLikeCountNotBetween(Integer value1, Integer value2) {
			addCriterion("like_count not between", value1, value2, "likeCount");
			return (Criteria) this;
		}

		public Criteria andFavoriteCountIsNull() {
			addCriterion("favorite_count is null");
			return (Criteria) this;
		}

		public Criteria andFavoriteCountIsNotNull() {
			addCriterion("favorite_count is not null");
			return (Criteria) this;
		}

		public Criteria andFavoriteCountEqualTo(Integer value) {
			addCriterion("favorite_count =", value, "favoriteCount");
			return (Criteria) this;
		}

		public Criteria andFavoriteCountNotEqualTo(Integer value) {
			addCriterion("favorite_count <>", value, "favoriteCount");
			return (Criteria) this;
		}

		public Criteria andFavoriteCountGreaterThan(Integer value) {
			addCriterion("favorite_count >", value, "favoriteCount");
			return (Criteria) this;
		}

		public Criteria andFavoriteCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("favorite_count >=", value, "favoriteCount");
			return (Criteria) this;
		}

		public Criteria andFavoriteCountLessThan(Integer value) {
			addCriterion("favorite_count <", value, "favoriteCount");
			return (Criteria) this;
		}

		public Criteria andFavoriteCountLessThanOrEqualTo(Integer value) {
			addCriterion("favorite_count <=", value, "favoriteCount");
			return (Criteria) this;
		}

		public Criteria andFavoriteCountIn(List<Integer> values) {
			addCriterion("favorite_count in", values, "favoriteCount");
			return (Criteria) this;
		}

		public Criteria andFavoriteCountNotIn(List<Integer> values) {
			addCriterion("favorite_count not in", values, "favoriteCount");
			return (Criteria) this;
		}

		public Criteria andFavoriteCountBetween(Integer value1, Integer value2) {
			addCriterion("favorite_count between", value1, value2, "favoriteCount");
			return (Criteria) this;
		}

		public Criteria andFavoriteCountNotBetween(Integer value1, Integer value2) {
			addCriterion("favorite_count not between", value1, value2, "favoriteCount");
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
	 * This class was generated by MyBatis Generator. This class corresponds to the database table tb_album_counter
	 * @mbggenerated  Sun Feb 16 10:16:39 CST 2014
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
     * This class corresponds to the database table tb_album_counter
     *
     * @mbggenerated do_not_delete_during_merge Sun Feb 16 09:56:28 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}