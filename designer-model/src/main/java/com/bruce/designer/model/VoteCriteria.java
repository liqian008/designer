package com.bruce.designer.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VoteCriteria {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tb_vote
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tb_vote
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tb_vote
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	protected List<Criteria> oredCriteria;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tb_vote
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	protected Integer limitOffset;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tb_vote
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	protected Integer limitRows;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	public VoteCriteria() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	public void setLimitOffset(Integer limitOffset) {
		this.limitOffset = limitOffset;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	public Integer getLimitOffset() {
		return limitOffset;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	public void setLimitRows(Integer limitRows) {
		this.limitRows = limitRows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_vote
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
	 */
	public Integer getLimitRows() {
		return limitRows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table tb_vote
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
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

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
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

		public Criteria andTitleIsNull() {
			addCriterion("title is null");
			return (Criteria) this;
		}

		public Criteria andTitleIsNotNull() {
			addCriterion("title is not null");
			return (Criteria) this;
		}

		public Criteria andTitleEqualTo(String value) {
			addCriterion("title =", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotEqualTo(String value) {
			addCriterion("title <>", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThan(String value) {
			addCriterion("title >", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThanOrEqualTo(String value) {
			addCriterion("title >=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThan(String value) {
			addCriterion("title <", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThanOrEqualTo(String value) {
			addCriterion("title <=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLike(String value) {
			addCriterion("title like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotLike(String value) {
			addCriterion("title not like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleIn(List<String> values) {
			addCriterion("title in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotIn(List<String> values) {
			addCriterion("title not in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleBetween(String value1, String value2) {
			addCriterion("title between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotBetween(String value1, String value2) {
			addCriterion("title not between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNull() {
			addCriterion("description is null");
			return (Criteria) this;
		}

		public Criteria andDescriptionIsNotNull() {
			addCriterion("description is not null");
			return (Criteria) this;
		}

		public Criteria andDescriptionEqualTo(String value) {
			addCriterion("description =", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotEqualTo(String value) {
			addCriterion("description <>", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThan(String value) {
			addCriterion("description >", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
			addCriterion("description >=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThan(String value) {
			addCriterion("description <", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLessThanOrEqualTo(String value) {
			addCriterion("description <=", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionLike(String value) {
			addCriterion("description like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotLike(String value) {
			addCriterion("description not like", value, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionIn(List<String> values) {
			addCriterion("description in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotIn(List<String> values) {
			addCriterion("description not in", values, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionBetween(String value1, String value2) {
			addCriterion("description between", value1, value2, "description");
			return (Criteria) this;
		}

		public Criteria andDescriptionNotBetween(String value1, String value2) {
			addCriterion("description not between", value1, value2,
					"description");
			return (Criteria) this;
		}

		public Criteria andThumbPicUrlIsNull() {
			addCriterion("thumb_pic_url is null");
			return (Criteria) this;
		}

		public Criteria andThumbPicUrlIsNotNull() {
			addCriterion("thumb_pic_url is not null");
			return (Criteria) this;
		}

		public Criteria andThumbPicUrlEqualTo(String value) {
			addCriterion("thumb_pic_url =", value, "thumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andThumbPicUrlNotEqualTo(String value) {
			addCriterion("thumb_pic_url <>", value, "thumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andThumbPicUrlGreaterThan(String value) {
			addCriterion("thumb_pic_url >", value, "thumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andThumbPicUrlGreaterThanOrEqualTo(String value) {
			addCriterion("thumb_pic_url >=", value, "thumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andThumbPicUrlLessThan(String value) {
			addCriterion("thumb_pic_url <", value, "thumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andThumbPicUrlLessThanOrEqualTo(String value) {
			addCriterion("thumb_pic_url <=", value, "thumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andThumbPicUrlLike(String value) {
			addCriterion("thumb_pic_url like", value, "thumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andThumbPicUrlNotLike(String value) {
			addCriterion("thumb_pic_url not like", value, "thumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andThumbPicUrlIn(List<String> values) {
			addCriterion("thumb_pic_url in", values, "thumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andThumbPicUrlNotIn(List<String> values) {
			addCriterion("thumb_pic_url not in", values, "thumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andThumbPicUrlBetween(String value1, String value2) {
			addCriterion("thumb_pic_url between", value1, value2, "thumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andThumbPicUrlNotBetween(String value1, String value2) {
			addCriterion("thumb_pic_url not between", value1, value2,
					"thumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlIsNull() {
			addCriterion("pic_url is null");
			return (Criteria) this;
		}

		public Criteria andPicUrlIsNotNull() {
			addCriterion("pic_url is not null");
			return (Criteria) this;
		}

		public Criteria andPicUrlEqualTo(String value) {
			addCriterion("pic_url =", value, "picUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlNotEqualTo(String value) {
			addCriterion("pic_url <>", value, "picUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlGreaterThan(String value) {
			addCriterion("pic_url >", value, "picUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlGreaterThanOrEqualTo(String value) {
			addCriterion("pic_url >=", value, "picUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlLessThan(String value) {
			addCriterion("pic_url <", value, "picUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlLessThanOrEqualTo(String value) {
			addCriterion("pic_url <=", value, "picUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlLike(String value) {
			addCriterion("pic_url like", value, "picUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlNotLike(String value) {
			addCriterion("pic_url not like", value, "picUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlIn(List<String> values) {
			addCriterion("pic_url in", values, "picUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlNotIn(List<String> values) {
			addCriterion("pic_url not in", values, "picUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlBetween(String value1, String value2) {
			addCriterion("pic_url between", value1, value2, "picUrl");
			return (Criteria) this;
		}

		public Criteria andPicUrlNotBetween(String value1, String value2) {
			addCriterion("pic_url not between", value1, value2, "picUrl");
			return (Criteria) this;
		}

		public Criteria andSortIsNull() {
			addCriterion("sort is null");
			return (Criteria) this;
		}

		public Criteria andSortIsNotNull() {
			addCriterion("sort is not null");
			return (Criteria) this;
		}

		public Criteria andSortEqualTo(Integer value) {
			addCriterion("sort =", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortNotEqualTo(Integer value) {
			addCriterion("sort <>", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortGreaterThan(Integer value) {
			addCriterion("sort >", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortGreaterThanOrEqualTo(Integer value) {
			addCriterion("sort >=", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortLessThan(Integer value) {
			addCriterion("sort <", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortLessThanOrEqualTo(Integer value) {
			addCriterion("sort <=", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortIn(List<Integer> values) {
			addCriterion("sort in", values, "sort");
			return (Criteria) this;
		}

		public Criteria andSortNotIn(List<Integer> values) {
			addCriterion("sort not in", values, "sort");
			return (Criteria) this;
		}

		public Criteria andSortBetween(Integer value1, Integer value2) {
			addCriterion("sort between", value1, value2, "sort");
			return (Criteria) this;
		}

		public Criteria andSortNotBetween(Integer value1, Integer value2) {
			addCriterion("sort not between", value1, value2, "sort");
			return (Criteria) this;
		}

		public Criteria andMaxCheckLimitIsNull() {
			addCriterion("max_check_limit is null");
			return (Criteria) this;
		}

		public Criteria andMaxCheckLimitIsNotNull() {
			addCriterion("max_check_limit is not null");
			return (Criteria) this;
		}

		public Criteria andMaxCheckLimitEqualTo(Integer value) {
			addCriterion("max_check_limit =", value, "maxCheckLimit");
			return (Criteria) this;
		}

		public Criteria andMaxCheckLimitNotEqualTo(Integer value) {
			addCriterion("max_check_limit <>", value, "maxCheckLimit");
			return (Criteria) this;
		}

		public Criteria andMaxCheckLimitGreaterThan(Integer value) {
			addCriterion("max_check_limit >", value, "maxCheckLimit");
			return (Criteria) this;
		}

		public Criteria andMaxCheckLimitGreaterThanOrEqualTo(Integer value) {
			addCriterion("max_check_limit >=", value, "maxCheckLimit");
			return (Criteria) this;
		}

		public Criteria andMaxCheckLimitLessThan(Integer value) {
			addCriterion("max_check_limit <", value, "maxCheckLimit");
			return (Criteria) this;
		}

		public Criteria andMaxCheckLimitLessThanOrEqualTo(Integer value) {
			addCriterion("max_check_limit <=", value, "maxCheckLimit");
			return (Criteria) this;
		}

		public Criteria andMaxCheckLimitIn(List<Integer> values) {
			addCriterion("max_check_limit in", values, "maxCheckLimit");
			return (Criteria) this;
		}

		public Criteria andMaxCheckLimitNotIn(List<Integer> values) {
			addCriterion("max_check_limit not in", values, "maxCheckLimit");
			return (Criteria) this;
		}

		public Criteria andMaxCheckLimitBetween(Integer value1, Integer value2) {
			addCriterion("max_check_limit between", value1, value2,
					"maxCheckLimit");
			return (Criteria) this;
		}

		public Criteria andMaxCheckLimitNotBetween(Integer value1,
				Integer value2) {
			addCriterion("max_check_limit not between", value1, value2,
					"maxCheckLimit");
			return (Criteria) this;
		}

		public Criteria andMaxRepeatLimitIsNull() {
			addCriterion("max_repeat_limit is null");
			return (Criteria) this;
		}

		public Criteria andMaxRepeatLimitIsNotNull() {
			addCriterion("max_repeat_limit is not null");
			return (Criteria) this;
		}

		public Criteria andMaxRepeatLimitEqualTo(Integer value) {
			addCriterion("max_repeat_limit =", value, "maxRepeatLimit");
			return (Criteria) this;
		}

		public Criteria andMaxRepeatLimitNotEqualTo(Integer value) {
			addCriterion("max_repeat_limit <>", value, "maxRepeatLimit");
			return (Criteria) this;
		}

		public Criteria andMaxRepeatLimitGreaterThan(Integer value) {
			addCriterion("max_repeat_limit >", value, "maxRepeatLimit");
			return (Criteria) this;
		}

		public Criteria andMaxRepeatLimitGreaterThanOrEqualTo(Integer value) {
			addCriterion("max_repeat_limit >=", value, "maxRepeatLimit");
			return (Criteria) this;
		}

		public Criteria andMaxRepeatLimitLessThan(Integer value) {
			addCriterion("max_repeat_limit <", value, "maxRepeatLimit");
			return (Criteria) this;
		}

		public Criteria andMaxRepeatLimitLessThanOrEqualTo(Integer value) {
			addCriterion("max_repeat_limit <=", value, "maxRepeatLimit");
			return (Criteria) this;
		}

		public Criteria andMaxRepeatLimitIn(List<Integer> values) {
			addCriterion("max_repeat_limit in", values, "maxRepeatLimit");
			return (Criteria) this;
		}

		public Criteria andMaxRepeatLimitNotIn(List<Integer> values) {
			addCriterion("max_repeat_limit not in", values, "maxRepeatLimit");
			return (Criteria) this;
		}

		public Criteria andMaxRepeatLimitBetween(Integer value1, Integer value2) {
			addCriterion("max_repeat_limit between", value1, value2,
					"maxRepeatLimit");
			return (Criteria) this;
		}

		public Criteria andMaxRepeatLimitNotBetween(Integer value1,
				Integer value2) {
			addCriterion("max_repeat_limit not between", value1, value2,
					"maxRepeatLimit");
			return (Criteria) this;
		}

		public Criteria andVoteStartTimeIsNull() {
			addCriterion("vote_start_time is null");
			return (Criteria) this;
		}

		public Criteria andVoteStartTimeIsNotNull() {
			addCriterion("vote_start_time is not null");
			return (Criteria) this;
		}

		public Criteria andVoteStartTimeEqualTo(Date value) {
			addCriterion("vote_start_time =", value, "voteStartTime");
			return (Criteria) this;
		}

		public Criteria andVoteStartTimeNotEqualTo(Date value) {
			addCriterion("vote_start_time <>", value, "voteStartTime");
			return (Criteria) this;
		}

		public Criteria andVoteStartTimeGreaterThan(Date value) {
			addCriterion("vote_start_time >", value, "voteStartTime");
			return (Criteria) this;
		}

		public Criteria andVoteStartTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("vote_start_time >=", value, "voteStartTime");
			return (Criteria) this;
		}

		public Criteria andVoteStartTimeLessThan(Date value) {
			addCriterion("vote_start_time <", value, "voteStartTime");
			return (Criteria) this;
		}

		public Criteria andVoteStartTimeLessThanOrEqualTo(Date value) {
			addCriterion("vote_start_time <=", value, "voteStartTime");
			return (Criteria) this;
		}

		public Criteria andVoteStartTimeIn(List<Date> values) {
			addCriterion("vote_start_time in", values, "voteStartTime");
			return (Criteria) this;
		}

		public Criteria andVoteStartTimeNotIn(List<Date> values) {
			addCriterion("vote_start_time not in", values, "voteStartTime");
			return (Criteria) this;
		}

		public Criteria andVoteStartTimeBetween(Date value1, Date value2) {
			addCriterion("vote_start_time between", value1, value2,
					"voteStartTime");
			return (Criteria) this;
		}

		public Criteria andVoteStartTimeNotBetween(Date value1, Date value2) {
			addCriterion("vote_start_time not between", value1, value2,
					"voteStartTime");
			return (Criteria) this;
		}

		public Criteria andVoteEndTimeIsNull() {
			addCriterion("vote_end_time is null");
			return (Criteria) this;
		}

		public Criteria andVoteEndTimeIsNotNull() {
			addCriterion("vote_end_time is not null");
			return (Criteria) this;
		}

		public Criteria andVoteEndTimeEqualTo(Date value) {
			addCriterion("vote_end_time =", value, "voteEndTime");
			return (Criteria) this;
		}

		public Criteria andVoteEndTimeNotEqualTo(Date value) {
			addCriterion("vote_end_time <>", value, "voteEndTime");
			return (Criteria) this;
		}

		public Criteria andVoteEndTimeGreaterThan(Date value) {
			addCriterion("vote_end_time >", value, "voteEndTime");
			return (Criteria) this;
		}

		public Criteria andVoteEndTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("vote_end_time >=", value, "voteEndTime");
			return (Criteria) this;
		}

		public Criteria andVoteEndTimeLessThan(Date value) {
			addCriterion("vote_end_time <", value, "voteEndTime");
			return (Criteria) this;
		}

		public Criteria andVoteEndTimeLessThanOrEqualTo(Date value) {
			addCriterion("vote_end_time <=", value, "voteEndTime");
			return (Criteria) this;
		}

		public Criteria andVoteEndTimeIn(List<Date> values) {
			addCriterion("vote_end_time in", values, "voteEndTime");
			return (Criteria) this;
		}

		public Criteria andVoteEndTimeNotIn(List<Date> values) {
			addCriterion("vote_end_time not in", values, "voteEndTime");
			return (Criteria) this;
		}

		public Criteria andVoteEndTimeBetween(Date value1, Date value2) {
			addCriterion("vote_end_time between", value1, value2, "voteEndTime");
			return (Criteria) this;
		}

		public Criteria andVoteEndTimeNotBetween(Date value1, Date value2) {
			addCriterion("vote_end_time not between", value1, value2,
					"voteEndTime");
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
			addCriterion("create_time not between", value1, value2,
					"createTime");
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
			addCriterion("update_time not between", value1, value2,
					"updateTime");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table tb_vote
	 * @mbggenerated  Mon Dec 08 18:35:33 CST 2014
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

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
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
     * This class corresponds to the database table tb_vote
     *
     * @mbggenerated do_not_delete_during_merge Mon Dec 08 14:30:39 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}