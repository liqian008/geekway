package com.bruce.geekway.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WxEventCodeCriteria {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_event_code
	 * @mbggenerated  Tue Apr 15 14:23:08 CST 2014
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_event_code
	 * @mbggenerated  Tue Apr 15 14:23:08 CST 2014
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_event_code
	 * @mbggenerated  Tue Apr 15 14:23:08 CST 2014
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_event_code
	 * @mbggenerated  Tue Apr 15 14:23:08 CST 2014
	 */
	public WxEventCodeCriteria() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_event_code
	 * @mbggenerated  Tue Apr 15 14:23:08 CST 2014
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_event_code
	 * @mbggenerated  Tue Apr 15 14:23:08 CST 2014
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_event_code
	 * @mbggenerated  Tue Apr 15 14:23:08 CST 2014
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_event_code
	 * @mbggenerated  Tue Apr 15 14:23:08 CST 2014
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_event_code
	 * @mbggenerated  Tue Apr 15 14:23:08 CST 2014
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_event_code
	 * @mbggenerated  Tue Apr 15 14:23:08 CST 2014
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_event_code
	 * @mbggenerated  Tue Apr 15 14:23:08 CST 2014
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_event_code
	 * @mbggenerated  Tue Apr 15 14:23:08 CST 2014
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_event_code
	 * @mbggenerated  Tue Apr 15 14:23:08 CST 2014
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_event_code
	 * @mbggenerated  Tue Apr 15 14:23:08 CST 2014
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table wx_event_code
	 * @mbggenerated  Tue Apr 15 14:23:08 CST 2014
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

		public Criteria andEventCodeIsNull() {
			addCriterion("event_code is null");
			return (Criteria) this;
		}

		public Criteria andEventCodeIsNotNull() {
			addCriterion("event_code is not null");
			return (Criteria) this;
		}

		public Criteria andEventCodeEqualTo(String value) {
			addCriterion("event_code =", value, "eventCode");
			return (Criteria) this;
		}

		public Criteria andEventCodeNotEqualTo(String value) {
			addCriterion("event_code <>", value, "eventCode");
			return (Criteria) this;
		}

		public Criteria andEventCodeGreaterThan(String value) {
			addCriterion("event_code >", value, "eventCode");
			return (Criteria) this;
		}

		public Criteria andEventCodeGreaterThanOrEqualTo(String value) {
			addCriterion("event_code >=", value, "eventCode");
			return (Criteria) this;
		}

		public Criteria andEventCodeLessThan(String value) {
			addCriterion("event_code <", value, "eventCode");
			return (Criteria) this;
		}

		public Criteria andEventCodeLessThanOrEqualTo(String value) {
			addCriterion("event_code <=", value, "eventCode");
			return (Criteria) this;
		}

		public Criteria andEventCodeLike(String value) {
			addCriterion("event_code like", value, "eventCode");
			return (Criteria) this;
		}

		public Criteria andEventCodeNotLike(String value) {
			addCriterion("event_code not like", value, "eventCode");
			return (Criteria) this;
		}

		public Criteria andEventCodeIn(List<String> values) {
			addCriterion("event_code in", values, "eventCode");
			return (Criteria) this;
		}

		public Criteria andEventCodeNotIn(List<String> values) {
			addCriterion("event_code not in", values, "eventCode");
			return (Criteria) this;
		}

		public Criteria andEventCodeBetween(String value1, String value2) {
			addCriterion("event_code between", value1, value2, "eventCode");
			return (Criteria) this;
		}

		public Criteria andEventCodeNotBetween(String value1, String value2) {
			addCriterion("event_code not between", value1, value2, "eventCode");
			return (Criteria) this;
		}

		public Criteria andEventTypeIsNull() {
			addCriterion("event_type is null");
			return (Criteria) this;
		}

		public Criteria andEventTypeIsNotNull() {
			addCriterion("event_type is not null");
			return (Criteria) this;
		}

		public Criteria andEventTypeEqualTo(Short value) {
			addCriterion("event_type =", value, "eventType");
			return (Criteria) this;
		}

		public Criteria andEventTypeNotEqualTo(Short value) {
			addCriterion("event_type <>", value, "eventType");
			return (Criteria) this;
		}

		public Criteria andEventTypeGreaterThan(Short value) {
			addCriterion("event_type >", value, "eventType");
			return (Criteria) this;
		}

		public Criteria andEventTypeGreaterThanOrEqualTo(Short value) {
			addCriterion("event_type >=", value, "eventType");
			return (Criteria) this;
		}

		public Criteria andEventTypeLessThan(Short value) {
			addCriterion("event_type <", value, "eventType");
			return (Criteria) this;
		}

		public Criteria andEventTypeLessThanOrEqualTo(Short value) {
			addCriterion("event_type <=", value, "eventType");
			return (Criteria) this;
		}

		public Criteria andEventTypeIn(List<Short> values) {
			addCriterion("event_type in", values, "eventType");
			return (Criteria) this;
		}

		public Criteria andEventTypeNotIn(List<Short> values) {
			addCriterion("event_type not in", values, "eventType");
			return (Criteria) this;
		}

		public Criteria andEventTypeBetween(Short value1, Short value2) {
			addCriterion("event_type between", value1, value2, "eventType");
			return (Criteria) this;
		}

		public Criteria andEventTypeNotBetween(Short value1, Short value2) {
			addCriterion("event_type not between", value1, value2, "eventType");
			return (Criteria) this;
		}

		public Criteria andDisplayTypeIsNull() {
			addCriterion("display_type is null");
			return (Criteria) this;
		}

		public Criteria andDisplayTypeIsNotNull() {
			addCriterion("display_type is not null");
			return (Criteria) this;
		}

		public Criteria andDisplayTypeEqualTo(Short value) {
			addCriterion("display_type =", value, "displayType");
			return (Criteria) this;
		}

		public Criteria andDisplayTypeNotEqualTo(Short value) {
			addCriterion("display_type <>", value, "displayType");
			return (Criteria) this;
		}

		public Criteria andDisplayTypeGreaterThan(Short value) {
			addCriterion("display_type >", value, "displayType");
			return (Criteria) this;
		}

		public Criteria andDisplayTypeGreaterThanOrEqualTo(Short value) {
			addCriterion("display_type >=", value, "displayType");
			return (Criteria) this;
		}

		public Criteria andDisplayTypeLessThan(Short value) {
			addCriterion("display_type <", value, "displayType");
			return (Criteria) this;
		}

		public Criteria andDisplayTypeLessThanOrEqualTo(Short value) {
			addCriterion("display_type <=", value, "displayType");
			return (Criteria) this;
		}

		public Criteria andDisplayTypeIn(List<Short> values) {
			addCriterion("display_type in", values, "displayType");
			return (Criteria) this;
		}

		public Criteria andDisplayTypeNotIn(List<Short> values) {
			addCriterion("display_type not in", values, "displayType");
			return (Criteria) this;
		}

		public Criteria andDisplayTypeBetween(Short value1, Short value2) {
			addCriterion("display_type between", value1, value2, "displayType");
			return (Criteria) this;
		}

		public Criteria andDisplayTypeNotBetween(Short value1, Short value2) {
			addCriterion("display_type not between", value1, value2,
					"displayType");
			return (Criteria) this;
		}

		public Criteria andReplyContentIsNull() {
			addCriterion("reply_content is null");
			return (Criteria) this;
		}

		public Criteria andReplyContentIsNotNull() {
			addCriterion("reply_content is not null");
			return (Criteria) this;
		}

		public Criteria andReplyContentEqualTo(String value) {
			addCriterion("reply_content =", value, "replyContent");
			return (Criteria) this;
		}

		public Criteria andReplyContentNotEqualTo(String value) {
			addCriterion("reply_content <>", value, "replyContent");
			return (Criteria) this;
		}

		public Criteria andReplyContentGreaterThan(String value) {
			addCriterion("reply_content >", value, "replyContent");
			return (Criteria) this;
		}

		public Criteria andReplyContentGreaterThanOrEqualTo(String value) {
			addCriterion("reply_content >=", value, "replyContent");
			return (Criteria) this;
		}

		public Criteria andReplyContentLessThan(String value) {
			addCriterion("reply_content <", value, "replyContent");
			return (Criteria) this;
		}

		public Criteria andReplyContentLessThanOrEqualTo(String value) {
			addCriterion("reply_content <=", value, "replyContent");
			return (Criteria) this;
		}

		public Criteria andReplyContentLike(String value) {
			addCriterion("reply_content like", value, "replyContent");
			return (Criteria) this;
		}

		public Criteria andReplyContentNotLike(String value) {
			addCriterion("reply_content not like", value, "replyContent");
			return (Criteria) this;
		}

		public Criteria andReplyContentIn(List<String> values) {
			addCriterion("reply_content in", values, "replyContent");
			return (Criteria) this;
		}

		public Criteria andReplyContentNotIn(List<String> values) {
			addCriterion("reply_content not in", values, "replyContent");
			return (Criteria) this;
		}

		public Criteria andReplyContentBetween(String value1, String value2) {
			addCriterion("reply_content between", value1, value2,
					"replyContent");
			return (Criteria) this;
		}

		public Criteria andReplyContentNotBetween(String value1, String value2) {
			addCriterion("reply_content not between", value1, value2,
					"replyContent");
			return (Criteria) this;
		}

		public Criteria andModuleIdIsNull() {
			addCriterion("module_id is null");
			return (Criteria) this;
		}

		public Criteria andModuleIdIsNotNull() {
			addCriterion("module_id is not null");
			return (Criteria) this;
		}

		public Criteria andModuleIdEqualTo(Integer value) {
			addCriterion("module_id =", value, "moduleId");
			return (Criteria) this;
		}

		public Criteria andModuleIdNotEqualTo(Integer value) {
			addCriterion("module_id <>", value, "moduleId");
			return (Criteria) this;
		}

		public Criteria andModuleIdGreaterThan(Integer value) {
			addCriterion("module_id >", value, "moduleId");
			return (Criteria) this;
		}

		public Criteria andModuleIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("module_id >=", value, "moduleId");
			return (Criteria) this;
		}

		public Criteria andModuleIdLessThan(Integer value) {
			addCriterion("module_id <", value, "moduleId");
			return (Criteria) this;
		}

		public Criteria andModuleIdLessThanOrEqualTo(Integer value) {
			addCriterion("module_id <=", value, "moduleId");
			return (Criteria) this;
		}

		public Criteria andModuleIdIn(List<Integer> values) {
			addCriterion("module_id in", values, "moduleId");
			return (Criteria) this;
		}

		public Criteria andModuleIdNotIn(List<Integer> values) {
			addCriterion("module_id not in", values, "moduleId");
			return (Criteria) this;
		}

		public Criteria andModuleIdBetween(Integer value1, Integer value2) {
			addCriterion("module_id between", value1, value2, "moduleId");
			return (Criteria) this;
		}

		public Criteria andModuleIdNotBetween(Integer value1, Integer value2) {
			addCriterion("module_id not between", value1, value2, "moduleId");
			return (Criteria) this;
		}

		public Criteria andModuleDescIsNull() {
			addCriterion("module_desc is null");
			return (Criteria) this;
		}

		public Criteria andModuleDescIsNotNull() {
			addCriterion("module_desc is not null");
			return (Criteria) this;
		}

		public Criteria andModuleDescEqualTo(String value) {
			addCriterion("module_desc =", value, "moduleDesc");
			return (Criteria) this;
		}

		public Criteria andModuleDescNotEqualTo(String value) {
			addCriterion("module_desc <>", value, "moduleDesc");
			return (Criteria) this;
		}

		public Criteria andModuleDescGreaterThan(String value) {
			addCriterion("module_desc >", value, "moduleDesc");
			return (Criteria) this;
		}

		public Criteria andModuleDescGreaterThanOrEqualTo(String value) {
			addCriterion("module_desc >=", value, "moduleDesc");
			return (Criteria) this;
		}

		public Criteria andModuleDescLessThan(String value) {
			addCriterion("module_desc <", value, "moduleDesc");
			return (Criteria) this;
		}

		public Criteria andModuleDescLessThanOrEqualTo(String value) {
			addCriterion("module_desc <=", value, "moduleDesc");
			return (Criteria) this;
		}

		public Criteria andModuleDescLike(String value) {
			addCriterion("module_desc like", value, "moduleDesc");
			return (Criteria) this;
		}

		public Criteria andModuleDescNotLike(String value) {
			addCriterion("module_desc not like", value, "moduleDesc");
			return (Criteria) this;
		}

		public Criteria andModuleDescIn(List<String> values) {
			addCriterion("module_desc in", values, "moduleDesc");
			return (Criteria) this;
		}

		public Criteria andModuleDescNotIn(List<String> values) {
			addCriterion("module_desc not in", values, "moduleDesc");
			return (Criteria) this;
		}

		public Criteria andModuleDescBetween(String value1, String value2) {
			addCriterion("module_desc between", value1, value2, "moduleDesc");
			return (Criteria) this;
		}

		public Criteria andModuleDescNotBetween(String value1, String value2) {
			addCriterion("module_desc not between", value1, value2,
					"moduleDesc");
			return (Criteria) this;
		}

		public Criteria andRowLimitIsNull() {
			addCriterion("row_limit is null");
			return (Criteria) this;
		}

		public Criteria andRowLimitIsNotNull() {
			addCriterion("row_limit is not null");
			return (Criteria) this;
		}

		public Criteria andRowLimitEqualTo(Short value) {
			addCriterion("row_limit =", value, "rowLimit");
			return (Criteria) this;
		}

		public Criteria andRowLimitNotEqualTo(Short value) {
			addCriterion("row_limit <>", value, "rowLimit");
			return (Criteria) this;
		}

		public Criteria andRowLimitGreaterThan(Short value) {
			addCriterion("row_limit >", value, "rowLimit");
			return (Criteria) this;
		}

		public Criteria andRowLimitGreaterThanOrEqualTo(Short value) {
			addCriterion("row_limit >=", value, "rowLimit");
			return (Criteria) this;
		}

		public Criteria andRowLimitLessThan(Short value) {
			addCriterion("row_limit <", value, "rowLimit");
			return (Criteria) this;
		}

		public Criteria andRowLimitLessThanOrEqualTo(Short value) {
			addCriterion("row_limit <=", value, "rowLimit");
			return (Criteria) this;
		}

		public Criteria andRowLimitIn(List<Short> values) {
			addCriterion("row_limit in", values, "rowLimit");
			return (Criteria) this;
		}

		public Criteria andRowLimitNotIn(List<Short> values) {
			addCriterion("row_limit not in", values, "rowLimit");
			return (Criteria) this;
		}

		public Criteria andRowLimitBetween(Short value1, Short value2) {
			addCriterion("row_limit between", value1, value2, "rowLimit");
			return (Criteria) this;
		}

		public Criteria andRowLimitNotBetween(Short value1, Short value2) {
			addCriterion("row_limit not between", value1, value2, "rowLimit");
			return (Criteria) this;
		}

		public Criteria andPublishStatusIsNull() {
			addCriterion("publish_status is null");
			return (Criteria) this;
		}

		public Criteria andPublishStatusIsNotNull() {
			addCriterion("publish_status is not null");
			return (Criteria) this;
		}

		public Criteria andPublishStatusEqualTo(Short value) {
			addCriterion("publish_status =", value, "publishStatus");
			return (Criteria) this;
		}

		public Criteria andPublishStatusNotEqualTo(Short value) {
			addCriterion("publish_status <>", value, "publishStatus");
			return (Criteria) this;
		}

		public Criteria andPublishStatusGreaterThan(Short value) {
			addCriterion("publish_status >", value, "publishStatus");
			return (Criteria) this;
		}

		public Criteria andPublishStatusGreaterThanOrEqualTo(Short value) {
			addCriterion("publish_status >=", value, "publishStatus");
			return (Criteria) this;
		}

		public Criteria andPublishStatusLessThan(Short value) {
			addCriterion("publish_status <", value, "publishStatus");
			return (Criteria) this;
		}

		public Criteria andPublishStatusLessThanOrEqualTo(Short value) {
			addCriterion("publish_status <=", value, "publishStatus");
			return (Criteria) this;
		}

		public Criteria andPublishStatusIn(List<Short> values) {
			addCriterion("publish_status in", values, "publishStatus");
			return (Criteria) this;
		}

		public Criteria andPublishStatusNotIn(List<Short> values) {
			addCriterion("publish_status not in", values, "publishStatus");
			return (Criteria) this;
		}

		public Criteria andPublishStatusBetween(Short value1, Short value2) {
			addCriterion("publish_status between", value1, value2,
					"publishStatus");
			return (Criteria) this;
		}

		public Criteria andPublishStatusNotBetween(Short value1, Short value2) {
			addCriterion("publish_status not between", value1, value2,
					"publishStatus");
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
	 * This class was generated by MyBatis Generator. This class corresponds to the database table wx_event_code
	 * @mbggenerated  Tue Apr 15 14:23:08 CST 2014
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
     * This class corresponds to the database table wx_event_code
     *
     * @mbggenerated do_not_delete_during_merge Sun Apr 13 09:50:59 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}