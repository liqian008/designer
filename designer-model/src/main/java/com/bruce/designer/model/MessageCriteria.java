package com.bruce.designer.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageCriteria {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tb_message
	 * @mbggenerated  Mon Nov 11 22:18:33 CST 2013
	 */
	protected String orderByClause;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tb_message
	 * @mbggenerated  Mon Nov 11 22:18:33 CST 2013
	 */
	protected boolean distinct;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table tb_message
	 * @mbggenerated  Mon Nov 11 22:18:33 CST 2013
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_message
	 * @mbggenerated  Mon Nov 11 22:18:33 CST 2013
	 */
	public MessageCriteria() {
		oredCriteria = new ArrayList<Criteria>();
	}



	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_message
	 * @mbggenerated  Mon Nov 11 22:18:33 CST 2013
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}



	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_message
	 * @mbggenerated  Mon Nov 11 22:18:33 CST 2013
	 */
	public String getOrderByClause() {
		return orderByClause;
	}



	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_message
	 * @mbggenerated  Mon Nov 11 22:18:33 CST 2013
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}



	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_message
	 * @mbggenerated  Mon Nov 11 22:18:33 CST 2013
	 */
	public boolean isDistinct() {
		return distinct;
	}



	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_message
	 * @mbggenerated  Mon Nov 11 22:18:33 CST 2013
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}



	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_message
	 * @mbggenerated  Mon Nov 11 22:18:33 CST 2013
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}



	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_message
	 * @mbggenerated  Mon Nov 11 22:18:33 CST 2013
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}



	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_message
	 * @mbggenerated  Mon Nov 11 22:18:33 CST 2013
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}



	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_message
	 * @mbggenerated  Mon Nov 11 22:18:33 CST 2013
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}



	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_message
	 * @mbggenerated  Mon Nov 11 22:18:33 CST 2013
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}



	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table tb_message
	 * @mbggenerated  Mon Nov 11 22:18:33 CST 2013
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

		public Criteria andMessageIsNull() {
			addCriterion("message is null");
			return (Criteria) this;
		}

		public Criteria andMessageIsNotNull() {
			addCriterion("message is not null");
			return (Criteria) this;
		}

		public Criteria andMessageEqualTo(String value) {
			addCriterion("message =", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageNotEqualTo(String value) {
			addCriterion("message <>", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageGreaterThan(String value) {
			addCriterion("message >", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageGreaterThanOrEqualTo(String value) {
			addCriterion("message >=", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageLessThan(String value) {
			addCriterion("message <", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageLessThanOrEqualTo(String value) {
			addCriterion("message <=", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageLike(String value) {
			addCriterion("message like", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageNotLike(String value) {
			addCriterion("message not like", value, "message");
			return (Criteria) this;
		}

		public Criteria andMessageIn(List<String> values) {
			addCriterion("message in", values, "message");
			return (Criteria) this;
		}

		public Criteria andMessageNotIn(List<String> values) {
			addCriterion("message not in", values, "message");
			return (Criteria) this;
		}

		public Criteria andMessageBetween(String value1, String value2) {
			addCriterion("message between", value1, value2, "message");
			return (Criteria) this;
		}

		public Criteria andMessageNotBetween(String value1, String value2) {
			addCriterion("message not between", value1, value2, "message");
			return (Criteria) this;
		}

		public Criteria andMessageTypeIsNull() {
			addCriterion("message_type is null");
			return (Criteria) this;
		}

		public Criteria andMessageTypeIsNotNull() {
			addCriterion("message_type is not null");
			return (Criteria) this;
		}

		public Criteria andMessageTypeEqualTo(Integer value) {
			addCriterion("message_type =", value, "messageType");
			return (Criteria) this;
		}

		public Criteria andMessageTypeNotEqualTo(Integer value) {
			addCriterion("message_type <>", value, "messageType");
			return (Criteria) this;
		}

		public Criteria andMessageTypeGreaterThan(Integer value) {
			addCriterion("message_type >", value, "messageType");
			return (Criteria) this;
		}

		public Criteria andMessageTypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("message_type >=", value, "messageType");
			return (Criteria) this;
		}

		public Criteria andMessageTypeLessThan(Integer value) {
			addCriterion("message_type <", value, "messageType");
			return (Criteria) this;
		}

		public Criteria andMessageTypeLessThanOrEqualTo(Integer value) {
			addCriterion("message_type <=", value, "messageType");
			return (Criteria) this;
		}

		public Criteria andMessageTypeIn(List<Integer> values) {
			addCriterion("message_type in", values, "messageType");
			return (Criteria) this;
		}

		public Criteria andMessageTypeNotIn(List<Integer> values) {
			addCriterion("message_type not in", values, "messageType");
			return (Criteria) this;
		}

		public Criteria andMessageTypeBetween(Integer value1, Integer value2) {
			addCriterion("message_type between", value1, value2, "messageType");
			return (Criteria) this;
		}

		public Criteria andMessageTypeNotBetween(Integer value1, Integer value2) {
			addCriterion("message_type not between", value1, value2, "messageType");
			return (Criteria) this;
		}

		public Criteria andSourceIdIsNull() {
			addCriterion("source_id is null");
			return (Criteria) this;
		}

		public Criteria andSourceIdIsNotNull() {
			addCriterion("source_id is not null");
			return (Criteria) this;
		}

		public Criteria andSourceIdEqualTo(Long value) {
			addCriterion("source_id =", value, "sourceId");
			return (Criteria) this;
		}

		public Criteria andSourceIdNotEqualTo(Long value) {
			addCriterion("source_id <>", value, "sourceId");
			return (Criteria) this;
		}

		public Criteria andSourceIdGreaterThan(Long value) {
			addCriterion("source_id >", value, "sourceId");
			return (Criteria) this;
		}

		public Criteria andSourceIdGreaterThanOrEqualTo(Long value) {
			addCriterion("source_id >=", value, "sourceId");
			return (Criteria) this;
		}

		public Criteria andSourceIdLessThan(Long value) {
			addCriterion("source_id <", value, "sourceId");
			return (Criteria) this;
		}

		public Criteria andSourceIdLessThanOrEqualTo(Long value) {
			addCriterion("source_id <=", value, "sourceId");
			return (Criteria) this;
		}

		public Criteria andSourceIdIn(List<Long> values) {
			addCriterion("source_id in", values, "sourceId");
			return (Criteria) this;
		}

		public Criteria andSourceIdNotIn(List<Long> values) {
			addCriterion("source_id not in", values, "sourceId");
			return (Criteria) this;
		}

		public Criteria andSourceIdBetween(Long value1, Long value2) {
			addCriterion("source_id between", value1, value2, "sourceId");
			return (Criteria) this;
		}

		public Criteria andSourceIdNotBetween(Long value1, Long value2) {
			addCriterion("source_id not between", value1, value2, "sourceId");
			return (Criteria) this;
		}

		public Criteria andFromIdIsNull() {
			addCriterion("from_id is null");
			return (Criteria) this;
		}

		public Criteria andFromIdIsNotNull() {
			addCriterion("from_id is not null");
			return (Criteria) this;
		}

		public Criteria andFromIdEqualTo(Integer value) {
			addCriterion("from_id =", value, "fromId");
			return (Criteria) this;
		}

		public Criteria andFromIdNotEqualTo(Integer value) {
			addCriterion("from_id <>", value, "fromId");
			return (Criteria) this;
		}

		public Criteria andFromIdGreaterThan(Integer value) {
			addCriterion("from_id >", value, "fromId");
			return (Criteria) this;
		}

		public Criteria andFromIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("from_id >=", value, "fromId");
			return (Criteria) this;
		}

		public Criteria andFromIdLessThan(Integer value) {
			addCriterion("from_id <", value, "fromId");
			return (Criteria) this;
		}

		public Criteria andFromIdLessThanOrEqualTo(Integer value) {
			addCriterion("from_id <=", value, "fromId");
			return (Criteria) this;
		}

		public Criteria andFromIdIn(List<Integer> values) {
			addCriterion("from_id in", values, "fromId");
			return (Criteria) this;
		}

		public Criteria andFromIdNotIn(List<Integer> values) {
			addCriterion("from_id not in", values, "fromId");
			return (Criteria) this;
		}

		public Criteria andFromIdBetween(Integer value1, Integer value2) {
			addCriterion("from_id between", value1, value2, "fromId");
			return (Criteria) this;
		}

		public Criteria andFromIdNotBetween(Integer value1, Integer value2) {
			addCriterion("from_id not between", value1, value2, "fromId");
			return (Criteria) this;
		}

		public Criteria andToIdIsNull() {
			addCriterion("to_id is null");
			return (Criteria) this;
		}

		public Criteria andToIdIsNotNull() {
			addCriterion("to_id is not null");
			return (Criteria) this;
		}

		public Criteria andToIdEqualTo(Integer value) {
			addCriterion("to_id =", value, "toId");
			return (Criteria) this;
		}

		public Criteria andToIdNotEqualTo(Integer value) {
			addCriterion("to_id <>", value, "toId");
			return (Criteria) this;
		}

		public Criteria andToIdGreaterThan(Integer value) {
			addCriterion("to_id >", value, "toId");
			return (Criteria) this;
		}

		public Criteria andToIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("to_id >=", value, "toId");
			return (Criteria) this;
		}

		public Criteria andToIdLessThan(Integer value) {
			addCriterion("to_id <", value, "toId");
			return (Criteria) this;
		}

		public Criteria andToIdLessThanOrEqualTo(Integer value) {
			addCriterion("to_id <=", value, "toId");
			return (Criteria) this;
		}

		public Criteria andToIdIn(List<Integer> values) {
			addCriterion("to_id in", values, "toId");
			return (Criteria) this;
		}

		public Criteria andToIdNotIn(List<Integer> values) {
			addCriterion("to_id not in", values, "toId");
			return (Criteria) this;
		}

		public Criteria andToIdBetween(Integer value1, Integer value2) {
			addCriterion("to_id between", value1, value2, "toId");
			return (Criteria) this;
		}

		public Criteria andToIdNotBetween(Integer value1, Integer value2) {
			addCriterion("to_id not between", value1, value2, "toId");
			return (Criteria) this;
		}

		public Criteria andUnreadIsNull() {
			addCriterion("unread is null");
			return (Criteria) this;
		}

		public Criteria andUnreadIsNotNull() {
			addCriterion("unread is not null");
			return (Criteria) this;
		}

		public Criteria andUnreadEqualTo(Short value) {
			addCriterion("unread =", value, "unread");
			return (Criteria) this;
		}

		public Criteria andUnreadNotEqualTo(Short value) {
			addCriterion("unread <>", value, "unread");
			return (Criteria) this;
		}

		public Criteria andUnreadGreaterThan(Short value) {
			addCriterion("unread >", value, "unread");
			return (Criteria) this;
		}

		public Criteria andUnreadGreaterThanOrEqualTo(Short value) {
			addCriterion("unread >=", value, "unread");
			return (Criteria) this;
		}

		public Criteria andUnreadLessThan(Short value) {
			addCriterion("unread <", value, "unread");
			return (Criteria) this;
		}

		public Criteria andUnreadLessThanOrEqualTo(Short value) {
			addCriterion("unread <=", value, "unread");
			return (Criteria) this;
		}

		public Criteria andUnreadIn(List<Short> values) {
			addCriterion("unread in", values, "unread");
			return (Criteria) this;
		}

		public Criteria andUnreadNotIn(List<Short> values) {
			addCriterion("unread not in", values, "unread");
			return (Criteria) this;
		}

		public Criteria andUnreadBetween(Short value1, Short value2) {
			addCriterion("unread between", value1, value2, "unread");
			return (Criteria) this;
		}

		public Criteria andUnreadNotBetween(Short value1, Short value2) {
			addCriterion("unread not between", value1, value2, "unread");
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
	 * This class was generated by MyBatis Generator. This class corresponds to the database table tb_message
	 * @mbggenerated  Mon Nov 11 22:18:33 CST 2013
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

    protected Integer start;
    
    protected Integer limit;

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

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table tb_message
     *
     * @mbggenerated do_not_delete_during_merge Fri Sep 27 20:04:06 CST 2013
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}