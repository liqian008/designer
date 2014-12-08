package com.bruce.designer.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VersionUpdateCriteria {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tb_version_update
	 * @mbggenerated  Wed Nov 19 15:22:18 CST 2014
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tb_version_update
	 * @mbggenerated  Wed Nov 19 15:22:18 CST 2014
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tb_version_update
	 * @mbggenerated  Wed Nov 19 15:22:18 CST 2014
	 */
	protected List<Criteria> oredCriteria;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tb_version_update
	 * @mbggenerated  Wed Nov 19 15:22:18 CST 2014
	 */
	protected Integer limitOffset;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tb_version_update
	 * @mbggenerated  Wed Nov 19 15:22:18 CST 2014
	 */
	protected Integer limitRows;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_version_update
	 * @mbggenerated  Wed Nov 19 15:22:18 CST 2014
	 */
	public VersionUpdateCriteria() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_version_update
	 * @mbggenerated  Wed Nov 19 15:22:18 CST 2014
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_version_update
	 * @mbggenerated  Wed Nov 19 15:22:18 CST 2014
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_version_update
	 * @mbggenerated  Wed Nov 19 15:22:18 CST 2014
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_version_update
	 * @mbggenerated  Wed Nov 19 15:22:18 CST 2014
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_version_update
	 * @mbggenerated  Wed Nov 19 15:22:18 CST 2014
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_version_update
	 * @mbggenerated  Wed Nov 19 15:22:18 CST 2014
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_version_update
	 * @mbggenerated  Wed Nov 19 15:22:18 CST 2014
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_version_update
	 * @mbggenerated  Wed Nov 19 15:22:18 CST 2014
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_version_update
	 * @mbggenerated  Wed Nov 19 15:22:18 CST 2014
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_version_update
	 * @mbggenerated  Wed Nov 19 15:22:18 CST 2014
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_version_update
	 * @mbggenerated  Wed Nov 19 15:22:18 CST 2014
	 */
	public void setLimitOffset(Integer limitOffset) {
		this.limitOffset = limitOffset;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_version_update
	 * @mbggenerated  Wed Nov 19 15:22:18 CST 2014
	 */
	public Integer getLimitOffset() {
		return limitOffset;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_version_update
	 * @mbggenerated  Wed Nov 19 15:22:18 CST 2014
	 */
	public void setLimitRows(Integer limitRows) {
		this.limitRows = limitRows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_version_update
	 * @mbggenerated  Wed Nov 19 15:22:18 CST 2014
	 */
	public Integer getLimitRows() {
		return limitRows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table tb_version_update
	 * @mbggenerated  Wed Nov 19 15:22:18 CST 2014
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

		public Criteria andClientTypeIsNull() {
			addCriterion("client_type is null");
			return (Criteria) this;
		}

		public Criteria andClientTypeIsNotNull() {
			addCriterion("client_type is not null");
			return (Criteria) this;
		}

		public Criteria andClientTypeEqualTo(Short value) {
			addCriterion("client_type =", value, "clientType");
			return (Criteria) this;
		}

		public Criteria andClientTypeNotEqualTo(Short value) {
			addCriterion("client_type <>", value, "clientType");
			return (Criteria) this;
		}

		public Criteria andClientTypeGreaterThan(Short value) {
			addCriterion("client_type >", value, "clientType");
			return (Criteria) this;
		}

		public Criteria andClientTypeGreaterThanOrEqualTo(Short value) {
			addCriterion("client_type >=", value, "clientType");
			return (Criteria) this;
		}

		public Criteria andClientTypeLessThan(Short value) {
			addCriterion("client_type <", value, "clientType");
			return (Criteria) this;
		}

		public Criteria andClientTypeLessThanOrEqualTo(Short value) {
			addCriterion("client_type <=", value, "clientType");
			return (Criteria) this;
		}

		public Criteria andClientTypeIn(List<Short> values) {
			addCriterion("client_type in", values, "clientType");
			return (Criteria) this;
		}

		public Criteria andClientTypeNotIn(List<Short> values) {
			addCriterion("client_type not in", values, "clientType");
			return (Criteria) this;
		}

		public Criteria andClientTypeBetween(Short value1, Short value2) {
			addCriterion("client_type between", value1, value2, "clientType");
			return (Criteria) this;
		}

		public Criteria andClientTypeNotBetween(Short value1, Short value2) {
			addCriterion("client_type not between", value1, value2,
					"clientType");
			return (Criteria) this;
		}

		public Criteria andChannelIsNull() {
			addCriterion("channel is null");
			return (Criteria) this;
		}

		public Criteria andChannelIsNotNull() {
			addCriterion("channel is not null");
			return (Criteria) this;
		}

		public Criteria andChannelEqualTo(String value) {
			addCriterion("channel =", value, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelNotEqualTo(String value) {
			addCriterion("channel <>", value, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelGreaterThan(String value) {
			addCriterion("channel >", value, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelGreaterThanOrEqualTo(String value) {
			addCriterion("channel >=", value, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelLessThan(String value) {
			addCriterion("channel <", value, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelLessThanOrEqualTo(String value) {
			addCriterion("channel <=", value, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelLike(String value) {
			addCriterion("channel like", value, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelNotLike(String value) {
			addCriterion("channel not like", value, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelIn(List<String> values) {
			addCriterion("channel in", values, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelNotIn(List<String> values) {
			addCriterion("channel not in", values, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelBetween(String value1, String value2) {
			addCriterion("channel between", value1, value2, "channel");
			return (Criteria) this;
		}

		public Criteria andChannelNotBetween(String value1, String value2) {
			addCriterion("channel not between", value1, value2, "channel");
			return (Criteria) this;
		}

		public Criteria andVersionCodeIsNull() {
			addCriterion("version_code is null");
			return (Criteria) this;
		}

		public Criteria andVersionCodeIsNotNull() {
			addCriterion("version_code is not null");
			return (Criteria) this;
		}

		public Criteria andVersionCodeEqualTo(Integer value) {
			addCriterion("version_code =", value, "versionCode");
			return (Criteria) this;
		}

		public Criteria andVersionCodeNotEqualTo(Integer value) {
			addCriterion("version_code <>", value, "versionCode");
			return (Criteria) this;
		}

		public Criteria andVersionCodeGreaterThan(Integer value) {
			addCriterion("version_code >", value, "versionCode");
			return (Criteria) this;
		}

		public Criteria andVersionCodeGreaterThanOrEqualTo(Integer value) {
			addCriterion("version_code >=", value, "versionCode");
			return (Criteria) this;
		}

		public Criteria andVersionCodeLessThan(Integer value) {
			addCriterion("version_code <", value, "versionCode");
			return (Criteria) this;
		}

		public Criteria andVersionCodeLessThanOrEqualTo(Integer value) {
			addCriterion("version_code <=", value, "versionCode");
			return (Criteria) this;
		}

		public Criteria andVersionCodeIn(List<Integer> values) {
			addCriterion("version_code in", values, "versionCode");
			return (Criteria) this;
		}

		public Criteria andVersionCodeNotIn(List<Integer> values) {
			addCriterion("version_code not in", values, "versionCode");
			return (Criteria) this;
		}

		public Criteria andVersionCodeBetween(Integer value1, Integer value2) {
			addCriterion("version_code between", value1, value2, "versionCode");
			return (Criteria) this;
		}

		public Criteria andVersionCodeNotBetween(Integer value1, Integer value2) {
			addCriterion("version_code not between", value1, value2,
					"versionCode");
			return (Criteria) this;
		}

		public Criteria andVersionNameIsNull() {
			addCriterion("version_name is null");
			return (Criteria) this;
		}

		public Criteria andVersionNameIsNotNull() {
			addCriterion("version_name is not null");
			return (Criteria) this;
		}

		public Criteria andVersionNameEqualTo(String value) {
			addCriterion("version_name =", value, "versionName");
			return (Criteria) this;
		}

		public Criteria andVersionNameNotEqualTo(String value) {
			addCriterion("version_name <>", value, "versionName");
			return (Criteria) this;
		}

		public Criteria andVersionNameGreaterThan(String value) {
			addCriterion("version_name >", value, "versionName");
			return (Criteria) this;
		}

		public Criteria andVersionNameGreaterThanOrEqualTo(String value) {
			addCriterion("version_name >=", value, "versionName");
			return (Criteria) this;
		}

		public Criteria andVersionNameLessThan(String value) {
			addCriterion("version_name <", value, "versionName");
			return (Criteria) this;
		}

		public Criteria andVersionNameLessThanOrEqualTo(String value) {
			addCriterion("version_name <=", value, "versionName");
			return (Criteria) this;
		}

		public Criteria andVersionNameLike(String value) {
			addCriterion("version_name like", value, "versionName");
			return (Criteria) this;
		}

		public Criteria andVersionNameNotLike(String value) {
			addCriterion("version_name not like", value, "versionName");
			return (Criteria) this;
		}

		public Criteria andVersionNameIn(List<String> values) {
			addCriterion("version_name in", values, "versionName");
			return (Criteria) this;
		}

		public Criteria andVersionNameNotIn(List<String> values) {
			addCriterion("version_name not in", values, "versionName");
			return (Criteria) this;
		}

		public Criteria andVersionNameBetween(String value1, String value2) {
			addCriterion("version_name between", value1, value2, "versionName");
			return (Criteria) this;
		}

		public Criteria andVersionNameNotBetween(String value1, String value2) {
			addCriterion("version_name not between", value1, value2,
					"versionName");
			return (Criteria) this;
		}

		public Criteria andVersionTitleIsNull() {
			addCriterion("version_title is null");
			return (Criteria) this;
		}

		public Criteria andVersionTitleIsNotNull() {
			addCriterion("version_title is not null");
			return (Criteria) this;
		}

		public Criteria andVersionTitleEqualTo(String value) {
			addCriterion("version_title =", value, "versionTitle");
			return (Criteria) this;
		}

		public Criteria andVersionTitleNotEqualTo(String value) {
			addCriterion("version_title <>", value, "versionTitle");
			return (Criteria) this;
		}

		public Criteria andVersionTitleGreaterThan(String value) {
			addCriterion("version_title >", value, "versionTitle");
			return (Criteria) this;
		}

		public Criteria andVersionTitleGreaterThanOrEqualTo(String value) {
			addCriterion("version_title >=", value, "versionTitle");
			return (Criteria) this;
		}

		public Criteria andVersionTitleLessThan(String value) {
			addCriterion("version_title <", value, "versionTitle");
			return (Criteria) this;
		}

		public Criteria andVersionTitleLessThanOrEqualTo(String value) {
			addCriterion("version_title <=", value, "versionTitle");
			return (Criteria) this;
		}

		public Criteria andVersionTitleLike(String value) {
			addCriterion("version_title like", value, "versionTitle");
			return (Criteria) this;
		}

		public Criteria andVersionTitleNotLike(String value) {
			addCriterion("version_title not like", value, "versionTitle");
			return (Criteria) this;
		}

		public Criteria andVersionTitleIn(List<String> values) {
			addCriterion("version_title in", values, "versionTitle");
			return (Criteria) this;
		}

		public Criteria andVersionTitleNotIn(List<String> values) {
			addCriterion("version_title not in", values, "versionTitle");
			return (Criteria) this;
		}

		public Criteria andVersionTitleBetween(String value1, String value2) {
			addCriterion("version_title between", value1, value2,
					"versionTitle");
			return (Criteria) this;
		}

		public Criteria andVersionTitleNotBetween(String value1, String value2) {
			addCriterion("version_title not between", value1, value2,
					"versionTitle");
			return (Criteria) this;
		}

		public Criteria andVersionDescriptionIsNull() {
			addCriterion("version_description is null");
			return (Criteria) this;
		}

		public Criteria andVersionDescriptionIsNotNull() {
			addCriterion("version_description is not null");
			return (Criteria) this;
		}

		public Criteria andVersionDescriptionEqualTo(String value) {
			addCriterion("version_description =", value, "versionDescription");
			return (Criteria) this;
		}

		public Criteria andVersionDescriptionNotEqualTo(String value) {
			addCriterion("version_description <>", value, "versionDescription");
			return (Criteria) this;
		}

		public Criteria andVersionDescriptionGreaterThan(String value) {
			addCriterion("version_description >", value, "versionDescription");
			return (Criteria) this;
		}

		public Criteria andVersionDescriptionGreaterThanOrEqualTo(String value) {
			addCriterion("version_description >=", value, "versionDescription");
			return (Criteria) this;
		}

		public Criteria andVersionDescriptionLessThan(String value) {
			addCriterion("version_description <", value, "versionDescription");
			return (Criteria) this;
		}

		public Criteria andVersionDescriptionLessThanOrEqualTo(String value) {
			addCriterion("version_description <=", value, "versionDescription");
			return (Criteria) this;
		}

		public Criteria andVersionDescriptionLike(String value) {
			addCriterion("version_description like", value,
					"versionDescription");
			return (Criteria) this;
		}

		public Criteria andVersionDescriptionNotLike(String value) {
			addCriterion("version_description not like", value,
					"versionDescription");
			return (Criteria) this;
		}

		public Criteria andVersionDescriptionIn(List<String> values) {
			addCriterion("version_description in", values, "versionDescription");
			return (Criteria) this;
		}

		public Criteria andVersionDescriptionNotIn(List<String> values) {
			addCriterion("version_description not in", values,
					"versionDescription");
			return (Criteria) this;
		}

		public Criteria andVersionDescriptionBetween(String value1,
				String value2) {
			addCriterion("version_description between", value1, value2,
					"versionDescription");
			return (Criteria) this;
		}

		public Criteria andVersionDescriptionNotBetween(String value1,
				String value2) {
			addCriterion("version_description not between", value1, value2,
					"versionDescription");
			return (Criteria) this;
		}

		public Criteria andReleaseNoteIsNull() {
			addCriterion("release_note is null");
			return (Criteria) this;
		}

		public Criteria andReleaseNoteIsNotNull() {
			addCriterion("release_note is not null");
			return (Criteria) this;
		}

		public Criteria andReleaseNoteEqualTo(String value) {
			addCriterion("release_note =", value, "releaseNote");
			return (Criteria) this;
		}

		public Criteria andReleaseNoteNotEqualTo(String value) {
			addCriterion("release_note <>", value, "releaseNote");
			return (Criteria) this;
		}

		public Criteria andReleaseNoteGreaterThan(String value) {
			addCriterion("release_note >", value, "releaseNote");
			return (Criteria) this;
		}

		public Criteria andReleaseNoteGreaterThanOrEqualTo(String value) {
			addCriterion("release_note >=", value, "releaseNote");
			return (Criteria) this;
		}

		public Criteria andReleaseNoteLessThan(String value) {
			addCriterion("release_note <", value, "releaseNote");
			return (Criteria) this;
		}

		public Criteria andReleaseNoteLessThanOrEqualTo(String value) {
			addCriterion("release_note <=", value, "releaseNote");
			return (Criteria) this;
		}

		public Criteria andReleaseNoteLike(String value) {
			addCriterion("release_note like", value, "releaseNote");
			return (Criteria) this;
		}

		public Criteria andReleaseNoteNotLike(String value) {
			addCriterion("release_note not like", value, "releaseNote");
			return (Criteria) this;
		}

		public Criteria andReleaseNoteIn(List<String> values) {
			addCriterion("release_note in", values, "releaseNote");
			return (Criteria) this;
		}

		public Criteria andReleaseNoteNotIn(List<String> values) {
			addCriterion("release_note not in", values, "releaseNote");
			return (Criteria) this;
		}

		public Criteria andReleaseNoteBetween(String value1, String value2) {
			addCriterion("release_note between", value1, value2, "releaseNote");
			return (Criteria) this;
		}

		public Criteria andReleaseNoteNotBetween(String value1, String value2) {
			addCriterion("release_note not between", value1, value2,
					"releaseNote");
			return (Criteria) this;
		}

		public Criteria andUpdateStatusIsNull() {
			addCriterion("update_status is null");
			return (Criteria) this;
		}

		public Criteria andUpdateStatusIsNotNull() {
			addCriterion("update_status is not null");
			return (Criteria) this;
		}

		public Criteria andUpdateStatusEqualTo(Short value) {
			addCriterion("update_status =", value, "updateStatus");
			return (Criteria) this;
		}

		public Criteria andUpdateStatusNotEqualTo(Short value) {
			addCriterion("update_status <>", value, "updateStatus");
			return (Criteria) this;
		}

		public Criteria andUpdateStatusGreaterThan(Short value) {
			addCriterion("update_status >", value, "updateStatus");
			return (Criteria) this;
		}

		public Criteria andUpdateStatusGreaterThanOrEqualTo(Short value) {
			addCriterion("update_status >=", value, "updateStatus");
			return (Criteria) this;
		}

		public Criteria andUpdateStatusLessThan(Short value) {
			addCriterion("update_status <", value, "updateStatus");
			return (Criteria) this;
		}

		public Criteria andUpdateStatusLessThanOrEqualTo(Short value) {
			addCriterion("update_status <=", value, "updateStatus");
			return (Criteria) this;
		}

		public Criteria andUpdateStatusIn(List<Short> values) {
			addCriterion("update_status in", values, "updateStatus");
			return (Criteria) this;
		}

		public Criteria andUpdateStatusNotIn(List<Short> values) {
			addCriterion("update_status not in", values, "updateStatus");
			return (Criteria) this;
		}

		public Criteria andUpdateStatusBetween(Short value1, Short value2) {
			addCriterion("update_status between", value1, value2,
					"updateStatus");
			return (Criteria) this;
		}

		public Criteria andUpdateStatusNotBetween(Short value1, Short value2) {
			addCriterion("update_status not between", value1, value2,
					"updateStatus");
			return (Criteria) this;
		}

		public Criteria andUpdateUrlIsNull() {
			addCriterion("update_url is null");
			return (Criteria) this;
		}

		public Criteria andUpdateUrlIsNotNull() {
			addCriterion("update_url is not null");
			return (Criteria) this;
		}

		public Criteria andUpdateUrlEqualTo(String value) {
			addCriterion("update_url =", value, "updateUrl");
			return (Criteria) this;
		}

		public Criteria andUpdateUrlNotEqualTo(String value) {
			addCriterion("update_url <>", value, "updateUrl");
			return (Criteria) this;
		}

		public Criteria andUpdateUrlGreaterThan(String value) {
			addCriterion("update_url >", value, "updateUrl");
			return (Criteria) this;
		}

		public Criteria andUpdateUrlGreaterThanOrEqualTo(String value) {
			addCriterion("update_url >=", value, "updateUrl");
			return (Criteria) this;
		}

		public Criteria andUpdateUrlLessThan(String value) {
			addCriterion("update_url <", value, "updateUrl");
			return (Criteria) this;
		}

		public Criteria andUpdateUrlLessThanOrEqualTo(String value) {
			addCriterion("update_url <=", value, "updateUrl");
			return (Criteria) this;
		}

		public Criteria andUpdateUrlLike(String value) {
			addCriterion("update_url like", value, "updateUrl");
			return (Criteria) this;
		}

		public Criteria andUpdateUrlNotLike(String value) {
			addCriterion("update_url not like", value, "updateUrl");
			return (Criteria) this;
		}

		public Criteria andUpdateUrlIn(List<String> values) {
			addCriterion("update_url in", values, "updateUrl");
			return (Criteria) this;
		}

		public Criteria andUpdateUrlNotIn(List<String> values) {
			addCriterion("update_url not in", values, "updateUrl");
			return (Criteria) this;
		}

		public Criteria andUpdateUrlBetween(String value1, String value2) {
			addCriterion("update_url between", value1, value2, "updateUrl");
			return (Criteria) this;
		}

		public Criteria andUpdateUrlNotBetween(String value1, String value2) {
			addCriterion("update_url not between", value1, value2, "updateUrl");
			return (Criteria) this;
		}

		public Criteria andAgreeTextIsNull() {
			addCriterion("agree_text is null");
			return (Criteria) this;
		}

		public Criteria andAgreeTextIsNotNull() {
			addCriterion("agree_text is not null");
			return (Criteria) this;
		}

		public Criteria andAgreeTextEqualTo(String value) {
			addCriterion("agree_text =", value, "agreeText");
			return (Criteria) this;
		}

		public Criteria andAgreeTextNotEqualTo(String value) {
			addCriterion("agree_text <>", value, "agreeText");
			return (Criteria) this;
		}

		public Criteria andAgreeTextGreaterThan(String value) {
			addCriterion("agree_text >", value, "agreeText");
			return (Criteria) this;
		}

		public Criteria andAgreeTextGreaterThanOrEqualTo(String value) {
			addCriterion("agree_text >=", value, "agreeText");
			return (Criteria) this;
		}

		public Criteria andAgreeTextLessThan(String value) {
			addCriterion("agree_text <", value, "agreeText");
			return (Criteria) this;
		}

		public Criteria andAgreeTextLessThanOrEqualTo(String value) {
			addCriterion("agree_text <=", value, "agreeText");
			return (Criteria) this;
		}

		public Criteria andAgreeTextLike(String value) {
			addCriterion("agree_text like", value, "agreeText");
			return (Criteria) this;
		}

		public Criteria andAgreeTextNotLike(String value) {
			addCriterion("agree_text not like", value, "agreeText");
			return (Criteria) this;
		}

		public Criteria andAgreeTextIn(List<String> values) {
			addCriterion("agree_text in", values, "agreeText");
			return (Criteria) this;
		}

		public Criteria andAgreeTextNotIn(List<String> values) {
			addCriterion("agree_text not in", values, "agreeText");
			return (Criteria) this;
		}

		public Criteria andAgreeTextBetween(String value1, String value2) {
			addCriterion("agree_text between", value1, value2, "agreeText");
			return (Criteria) this;
		}

		public Criteria andAgreeTextNotBetween(String value1, String value2) {
			addCriterion("agree_text not between", value1, value2, "agreeText");
			return (Criteria) this;
		}

		public Criteria andDeniedTextIsNull() {
			addCriterion("denied_text is null");
			return (Criteria) this;
		}

		public Criteria andDeniedTextIsNotNull() {
			addCriterion("denied_text is not null");
			return (Criteria) this;
		}

		public Criteria andDeniedTextEqualTo(String value) {
			addCriterion("denied_text =", value, "deniedText");
			return (Criteria) this;
		}

		public Criteria andDeniedTextNotEqualTo(String value) {
			addCriterion("denied_text <>", value, "deniedText");
			return (Criteria) this;
		}

		public Criteria andDeniedTextGreaterThan(String value) {
			addCriterion("denied_text >", value, "deniedText");
			return (Criteria) this;
		}

		public Criteria andDeniedTextGreaterThanOrEqualTo(String value) {
			addCriterion("denied_text >=", value, "deniedText");
			return (Criteria) this;
		}

		public Criteria andDeniedTextLessThan(String value) {
			addCriterion("denied_text <", value, "deniedText");
			return (Criteria) this;
		}

		public Criteria andDeniedTextLessThanOrEqualTo(String value) {
			addCriterion("denied_text <=", value, "deniedText");
			return (Criteria) this;
		}

		public Criteria andDeniedTextLike(String value) {
			addCriterion("denied_text like", value, "deniedText");
			return (Criteria) this;
		}

		public Criteria andDeniedTextNotLike(String value) {
			addCriterion("denied_text not like", value, "deniedText");
			return (Criteria) this;
		}

		public Criteria andDeniedTextIn(List<String> values) {
			addCriterion("denied_text in", values, "deniedText");
			return (Criteria) this;
		}

		public Criteria andDeniedTextNotIn(List<String> values) {
			addCriterion("denied_text not in", values, "deniedText");
			return (Criteria) this;
		}

		public Criteria andDeniedTextBetween(String value1, String value2) {
			addCriterion("denied_text between", value1, value2, "deniedText");
			return (Criteria) this;
		}

		public Criteria andDeniedTextNotBetween(String value1, String value2) {
			addCriterion("denied_text not between", value1, value2,
					"deniedText");
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
	 * This class was generated by MyBatis Generator. This class corresponds to the database table tb_version_update
	 * @mbggenerated  Wed Nov 19 15:22:18 CST 2014
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
     * This class corresponds to the database table tb_version_update
     *
     * @mbggenerated do_not_delete_during_merge Wed Nov 19 09:40:06 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}