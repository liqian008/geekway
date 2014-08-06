package com.bruce.geekway.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WxHistoryMessageCriteria {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_history_message
	 * @mbggenerated  Wed Aug 06 14:43:01 CST 2014
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_history_message
	 * @mbggenerated  Wed Aug 06 14:43:01 CST 2014
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_history_message
	 * @mbggenerated  Wed Aug 06 14:43:01 CST 2014
	 */
	protected List<Criteria> oredCriteria;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_history_message
	 * @mbggenerated  Wed Aug 06 14:43:01 CST 2014
	 */
	protected Integer limitOffset;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_history_message
	 * @mbggenerated  Wed Aug 06 14:43:01 CST 2014
	 */
	protected Integer limitRows;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_history_message
	 * @mbggenerated  Wed Aug 06 14:43:01 CST 2014
	 */
	public WxHistoryMessageCriteria() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_history_message
	 * @mbggenerated  Wed Aug 06 14:43:01 CST 2014
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_history_message
	 * @mbggenerated  Wed Aug 06 14:43:01 CST 2014
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_history_message
	 * @mbggenerated  Wed Aug 06 14:43:01 CST 2014
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_history_message
	 * @mbggenerated  Wed Aug 06 14:43:01 CST 2014
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_history_message
	 * @mbggenerated  Wed Aug 06 14:43:01 CST 2014
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_history_message
	 * @mbggenerated  Wed Aug 06 14:43:01 CST 2014
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_history_message
	 * @mbggenerated  Wed Aug 06 14:43:01 CST 2014
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_history_message
	 * @mbggenerated  Wed Aug 06 14:43:01 CST 2014
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_history_message
	 * @mbggenerated  Wed Aug 06 14:43:01 CST 2014
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_history_message
	 * @mbggenerated  Wed Aug 06 14:43:01 CST 2014
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_history_message
	 * @mbggenerated  Wed Aug 06 14:43:01 CST 2014
	 */
	public void setLimitOffset(Integer limitOffset) {
		this.limitOffset = limitOffset;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_history_message
	 * @mbggenerated  Wed Aug 06 14:43:01 CST 2014
	 */
	public Integer getLimitOffset() {
		return limitOffset;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_history_message
	 * @mbggenerated  Wed Aug 06 14:43:01 CST 2014
	 */
	public void setLimitRows(Integer limitRows) {
		this.limitRows = limitRows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_history_message
	 * @mbggenerated  Wed Aug 06 14:43:01 CST 2014
	 */
	public Integer getLimitRows() {
		return limitRows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table wx_history_message
	 * @mbggenerated  Wed Aug 06 14:43:01 CST 2014
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

		public Criteria andMsgIdIsNull() {
			addCriterion("msg_id is null");
			return (Criteria) this;
		}

		public Criteria andMsgIdIsNotNull() {
			addCriterion("msg_id is not null");
			return (Criteria) this;
		}

		public Criteria andMsgIdEqualTo(String value) {
			addCriterion("msg_id =", value, "msgId");
			return (Criteria) this;
		}

		public Criteria andMsgIdNotEqualTo(String value) {
			addCriterion("msg_id <>", value, "msgId");
			return (Criteria) this;
		}

		public Criteria andMsgIdGreaterThan(String value) {
			addCriterion("msg_id >", value, "msgId");
			return (Criteria) this;
		}

		public Criteria andMsgIdGreaterThanOrEqualTo(String value) {
			addCriterion("msg_id >=", value, "msgId");
			return (Criteria) this;
		}

		public Criteria andMsgIdLessThan(String value) {
			addCriterion("msg_id <", value, "msgId");
			return (Criteria) this;
		}

		public Criteria andMsgIdLessThanOrEqualTo(String value) {
			addCriterion("msg_id <=", value, "msgId");
			return (Criteria) this;
		}

		public Criteria andMsgIdLike(String value) {
			addCriterion("msg_id like", value, "msgId");
			return (Criteria) this;
		}

		public Criteria andMsgIdNotLike(String value) {
			addCriterion("msg_id not like", value, "msgId");
			return (Criteria) this;
		}

		public Criteria andMsgIdIn(List<String> values) {
			addCriterion("msg_id in", values, "msgId");
			return (Criteria) this;
		}

		public Criteria andMsgIdNotIn(List<String> values) {
			addCriterion("msg_id not in", values, "msgId");
			return (Criteria) this;
		}

		public Criteria andMsgIdBetween(String value1, String value2) {
			addCriterion("msg_id between", value1, value2, "msgId");
			return (Criteria) this;
		}

		public Criteria andMsgIdNotBetween(String value1, String value2) {
			addCriterion("msg_id not between", value1, value2, "msgId");
			return (Criteria) this;
		}

		public Criteria andOpenIdIsNull() {
			addCriterion("open_id is null");
			return (Criteria) this;
		}

		public Criteria andOpenIdIsNotNull() {
			addCriterion("open_id is not null");
			return (Criteria) this;
		}

		public Criteria andOpenIdEqualTo(String value) {
			addCriterion("open_id =", value, "openId");
			return (Criteria) this;
		}

		public Criteria andOpenIdNotEqualTo(String value) {
			addCriterion("open_id <>", value, "openId");
			return (Criteria) this;
		}

		public Criteria andOpenIdGreaterThan(String value) {
			addCriterion("open_id >", value, "openId");
			return (Criteria) this;
		}

		public Criteria andOpenIdGreaterThanOrEqualTo(String value) {
			addCriterion("open_id >=", value, "openId");
			return (Criteria) this;
		}

		public Criteria andOpenIdLessThan(String value) {
			addCriterion("open_id <", value, "openId");
			return (Criteria) this;
		}

		public Criteria andOpenIdLessThanOrEqualTo(String value) {
			addCriterion("open_id <=", value, "openId");
			return (Criteria) this;
		}

		public Criteria andOpenIdLike(String value) {
			addCriterion("open_id like", value, "openId");
			return (Criteria) this;
		}

		public Criteria andOpenIdNotLike(String value) {
			addCriterion("open_id not like", value, "openId");
			return (Criteria) this;
		}

		public Criteria andOpenIdIn(List<String> values) {
			addCriterion("open_id in", values, "openId");
			return (Criteria) this;
		}

		public Criteria andOpenIdNotIn(List<String> values) {
			addCriterion("open_id not in", values, "openId");
			return (Criteria) this;
		}

		public Criteria andOpenIdBetween(String value1, String value2) {
			addCriterion("open_id between", value1, value2, "openId");
			return (Criteria) this;
		}

		public Criteria andOpenIdNotBetween(String value1, String value2) {
			addCriterion("open_id not between", value1, value2, "openId");
			return (Criteria) this;
		}

		public Criteria andInboxIsNull() {
			addCriterion("inbox is null");
			return (Criteria) this;
		}

		public Criteria andInboxIsNotNull() {
			addCriterion("inbox is not null");
			return (Criteria) this;
		}

		public Criteria andInboxEqualTo(Short value) {
			addCriterion("inbox =", value, "inbox");
			return (Criteria) this;
		}

		public Criteria andInboxNotEqualTo(Short value) {
			addCriterion("inbox <>", value, "inbox");
			return (Criteria) this;
		}

		public Criteria andInboxGreaterThan(Short value) {
			addCriterion("inbox >", value, "inbox");
			return (Criteria) this;
		}

		public Criteria andInboxGreaterThanOrEqualTo(Short value) {
			addCriterion("inbox >=", value, "inbox");
			return (Criteria) this;
		}

		public Criteria andInboxLessThan(Short value) {
			addCriterion("inbox <", value, "inbox");
			return (Criteria) this;
		}

		public Criteria andInboxLessThanOrEqualTo(Short value) {
			addCriterion("inbox <=", value, "inbox");
			return (Criteria) this;
		}

		public Criteria andInboxIn(List<Short> values) {
			addCriterion("inbox in", values, "inbox");
			return (Criteria) this;
		}

		public Criteria andInboxNotIn(List<Short> values) {
			addCriterion("inbox not in", values, "inbox");
			return (Criteria) this;
		}

		public Criteria andInboxBetween(Short value1, Short value2) {
			addCriterion("inbox between", value1, value2, "inbox");
			return (Criteria) this;
		}

		public Criteria andInboxNotBetween(Short value1, Short value2) {
			addCriterion("inbox not between", value1, value2, "inbox");
			return (Criteria) this;
		}

		public Criteria andMsgTypeIsNull() {
			addCriterion("msg_type is null");
			return (Criteria) this;
		}

		public Criteria andMsgTypeIsNotNull() {
			addCriterion("msg_type is not null");
			return (Criteria) this;
		}

		public Criteria andMsgTypeEqualTo(String value) {
			addCriterion("msg_type =", value, "msgType");
			return (Criteria) this;
		}

		public Criteria andMsgTypeNotEqualTo(String value) {
			addCriterion("msg_type <>", value, "msgType");
			return (Criteria) this;
		}

		public Criteria andMsgTypeGreaterThan(String value) {
			addCriterion("msg_type >", value, "msgType");
			return (Criteria) this;
		}

		public Criteria andMsgTypeGreaterThanOrEqualTo(String value) {
			addCriterion("msg_type >=", value, "msgType");
			return (Criteria) this;
		}

		public Criteria andMsgTypeLessThan(String value) {
			addCriterion("msg_type <", value, "msgType");
			return (Criteria) this;
		}

		public Criteria andMsgTypeLessThanOrEqualTo(String value) {
			addCriterion("msg_type <=", value, "msgType");
			return (Criteria) this;
		}

		public Criteria andMsgTypeLike(String value) {
			addCriterion("msg_type like", value, "msgType");
			return (Criteria) this;
		}

		public Criteria andMsgTypeNotLike(String value) {
			addCriterion("msg_type not like", value, "msgType");
			return (Criteria) this;
		}

		public Criteria andMsgTypeIn(List<String> values) {
			addCriterion("msg_type in", values, "msgType");
			return (Criteria) this;
		}

		public Criteria andMsgTypeNotIn(List<String> values) {
			addCriterion("msg_type not in", values, "msgType");
			return (Criteria) this;
		}

		public Criteria andMsgTypeBetween(String value1, String value2) {
			addCriterion("msg_type between", value1, value2, "msgType");
			return (Criteria) this;
		}

		public Criteria andMsgTypeNotBetween(String value1, String value2) {
			addCriterion("msg_type not between", value1, value2, "msgType");
			return (Criteria) this;
		}

		public Criteria andFullMessageIsNull() {
			addCriterion("full_message is null");
			return (Criteria) this;
		}

		public Criteria andFullMessageIsNotNull() {
			addCriterion("full_message is not null");
			return (Criteria) this;
		}

		public Criteria andFullMessageEqualTo(String value) {
			addCriterion("full_message =", value, "fullMessage");
			return (Criteria) this;
		}

		public Criteria andFullMessageNotEqualTo(String value) {
			addCriterion("full_message <>", value, "fullMessage");
			return (Criteria) this;
		}

		public Criteria andFullMessageGreaterThan(String value) {
			addCriterion("full_message >", value, "fullMessage");
			return (Criteria) this;
		}

		public Criteria andFullMessageGreaterThanOrEqualTo(String value) {
			addCriterion("full_message >=", value, "fullMessage");
			return (Criteria) this;
		}

		public Criteria andFullMessageLessThan(String value) {
			addCriterion("full_message <", value, "fullMessage");
			return (Criteria) this;
		}

		public Criteria andFullMessageLessThanOrEqualTo(String value) {
			addCriterion("full_message <=", value, "fullMessage");
			return (Criteria) this;
		}

		public Criteria andFullMessageLike(String value) {
			addCriterion("full_message like", value, "fullMessage");
			return (Criteria) this;
		}

		public Criteria andFullMessageNotLike(String value) {
			addCriterion("full_message not like", value, "fullMessage");
			return (Criteria) this;
		}

		public Criteria andFullMessageIn(List<String> values) {
			addCriterion("full_message in", values, "fullMessage");
			return (Criteria) this;
		}

		public Criteria andFullMessageNotIn(List<String> values) {
			addCriterion("full_message not in", values, "fullMessage");
			return (Criteria) this;
		}

		public Criteria andFullMessageBetween(String value1, String value2) {
			addCriterion("full_message between", value1, value2, "fullMessage");
			return (Criteria) this;
		}

		public Criteria andFullMessageNotBetween(String value1, String value2) {
			addCriterion("full_message not between", value1, value2,
					"fullMessage");
			return (Criteria) this;
		}

		public Criteria andContentIsNull() {
			addCriterion("content is null");
			return (Criteria) this;
		}

		public Criteria andContentIsNotNull() {
			addCriterion("content is not null");
			return (Criteria) this;
		}

		public Criteria andContentEqualTo(String value) {
			addCriterion("content =", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentNotEqualTo(String value) {
			addCriterion("content <>", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentGreaterThan(String value) {
			addCriterion("content >", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentGreaterThanOrEqualTo(String value) {
			addCriterion("content >=", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentLessThan(String value) {
			addCriterion("content <", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentLessThanOrEqualTo(String value) {
			addCriterion("content <=", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentLike(String value) {
			addCriterion("content like", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentNotLike(String value) {
			addCriterion("content not like", value, "content");
			return (Criteria) this;
		}

		public Criteria andContentIn(List<String> values) {
			addCriterion("content in", values, "content");
			return (Criteria) this;
		}

		public Criteria andContentNotIn(List<String> values) {
			addCriterion("content not in", values, "content");
			return (Criteria) this;
		}

		public Criteria andContentBetween(String value1, String value2) {
			addCriterion("content between", value1, value2, "content");
			return (Criteria) this;
		}

		public Criteria andContentNotBetween(String value1, String value2) {
			addCriterion("content not between", value1, value2, "content");
			return (Criteria) this;
		}

		public Criteria andMediaIdIsNull() {
			addCriterion("media_id is null");
			return (Criteria) this;
		}

		public Criteria andMediaIdIsNotNull() {
			addCriterion("media_id is not null");
			return (Criteria) this;
		}

		public Criteria andMediaIdEqualTo(String value) {
			addCriterion("media_id =", value, "mediaId");
			return (Criteria) this;
		}

		public Criteria andMediaIdNotEqualTo(String value) {
			addCriterion("media_id <>", value, "mediaId");
			return (Criteria) this;
		}

		public Criteria andMediaIdGreaterThan(String value) {
			addCriterion("media_id >", value, "mediaId");
			return (Criteria) this;
		}

		public Criteria andMediaIdGreaterThanOrEqualTo(String value) {
			addCriterion("media_id >=", value, "mediaId");
			return (Criteria) this;
		}

		public Criteria andMediaIdLessThan(String value) {
			addCriterion("media_id <", value, "mediaId");
			return (Criteria) this;
		}

		public Criteria andMediaIdLessThanOrEqualTo(String value) {
			addCriterion("media_id <=", value, "mediaId");
			return (Criteria) this;
		}

		public Criteria andMediaIdLike(String value) {
			addCriterion("media_id like", value, "mediaId");
			return (Criteria) this;
		}

		public Criteria andMediaIdNotLike(String value) {
			addCriterion("media_id not like", value, "mediaId");
			return (Criteria) this;
		}

		public Criteria andMediaIdIn(List<String> values) {
			addCriterion("media_id in", values, "mediaId");
			return (Criteria) this;
		}

		public Criteria andMediaIdNotIn(List<String> values) {
			addCriterion("media_id not in", values, "mediaId");
			return (Criteria) this;
		}

		public Criteria andMediaIdBetween(String value1, String value2) {
			addCriterion("media_id between", value1, value2, "mediaId");
			return (Criteria) this;
		}

		public Criteria andMediaIdNotBetween(String value1, String value2) {
			addCriterion("media_id not between", value1, value2, "mediaId");
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

		public Criteria andFormatIsNull() {
			addCriterion("format is null");
			return (Criteria) this;
		}

		public Criteria andFormatIsNotNull() {
			addCriterion("format is not null");
			return (Criteria) this;
		}

		public Criteria andFormatEqualTo(String value) {
			addCriterion("format =", value, "format");
			return (Criteria) this;
		}

		public Criteria andFormatNotEqualTo(String value) {
			addCriterion("format <>", value, "format");
			return (Criteria) this;
		}

		public Criteria andFormatGreaterThan(String value) {
			addCriterion("format >", value, "format");
			return (Criteria) this;
		}

		public Criteria andFormatGreaterThanOrEqualTo(String value) {
			addCriterion("format >=", value, "format");
			return (Criteria) this;
		}

		public Criteria andFormatLessThan(String value) {
			addCriterion("format <", value, "format");
			return (Criteria) this;
		}

		public Criteria andFormatLessThanOrEqualTo(String value) {
			addCriterion("format <=", value, "format");
			return (Criteria) this;
		}

		public Criteria andFormatLike(String value) {
			addCriterion("format like", value, "format");
			return (Criteria) this;
		}

		public Criteria andFormatNotLike(String value) {
			addCriterion("format not like", value, "format");
			return (Criteria) this;
		}

		public Criteria andFormatIn(List<String> values) {
			addCriterion("format in", values, "format");
			return (Criteria) this;
		}

		public Criteria andFormatNotIn(List<String> values) {
			addCriterion("format not in", values, "format");
			return (Criteria) this;
		}

		public Criteria andFormatBetween(String value1, String value2) {
			addCriterion("format between", value1, value2, "format");
			return (Criteria) this;
		}

		public Criteria andFormatNotBetween(String value1, String value2) {
			addCriterion("format not between", value1, value2, "format");
			return (Criteria) this;
		}

		public Criteria andThumbMediaIdIsNull() {
			addCriterion("thumb_media_id is null");
			return (Criteria) this;
		}

		public Criteria andThumbMediaIdIsNotNull() {
			addCriterion("thumb_media_id is not null");
			return (Criteria) this;
		}

		public Criteria andThumbMediaIdEqualTo(String value) {
			addCriterion("thumb_media_id =", value, "thumbMediaId");
			return (Criteria) this;
		}

		public Criteria andThumbMediaIdNotEqualTo(String value) {
			addCriterion("thumb_media_id <>", value, "thumbMediaId");
			return (Criteria) this;
		}

		public Criteria andThumbMediaIdGreaterThan(String value) {
			addCriterion("thumb_media_id >", value, "thumbMediaId");
			return (Criteria) this;
		}

		public Criteria andThumbMediaIdGreaterThanOrEqualTo(String value) {
			addCriterion("thumb_media_id >=", value, "thumbMediaId");
			return (Criteria) this;
		}

		public Criteria andThumbMediaIdLessThan(String value) {
			addCriterion("thumb_media_id <", value, "thumbMediaId");
			return (Criteria) this;
		}

		public Criteria andThumbMediaIdLessThanOrEqualTo(String value) {
			addCriterion("thumb_media_id <=", value, "thumbMediaId");
			return (Criteria) this;
		}

		public Criteria andThumbMediaIdLike(String value) {
			addCriterion("thumb_media_id like", value, "thumbMediaId");
			return (Criteria) this;
		}

		public Criteria andThumbMediaIdNotLike(String value) {
			addCriterion("thumb_media_id not like", value, "thumbMediaId");
			return (Criteria) this;
		}

		public Criteria andThumbMediaIdIn(List<String> values) {
			addCriterion("thumb_media_id in", values, "thumbMediaId");
			return (Criteria) this;
		}

		public Criteria andThumbMediaIdNotIn(List<String> values) {
			addCriterion("thumb_media_id not in", values, "thumbMediaId");
			return (Criteria) this;
		}

		public Criteria andThumbMediaIdBetween(String value1, String value2) {
			addCriterion("thumb_media_id between", value1, value2,
					"thumbMediaId");
			return (Criteria) this;
		}

		public Criteria andThumbMediaIdNotBetween(String value1, String value2) {
			addCriterion("thumb_media_id not between", value1, value2,
					"thumbMediaId");
			return (Criteria) this;
		}

		public Criteria andSendTimeIsNull() {
			addCriterion("send_time is null");
			return (Criteria) this;
		}

		public Criteria andSendTimeIsNotNull() {
			addCriterion("send_time is not null");
			return (Criteria) this;
		}

		public Criteria andSendTimeEqualTo(Date value) {
			addCriterion("send_time =", value, "sendTime");
			return (Criteria) this;
		}

		public Criteria andSendTimeNotEqualTo(Date value) {
			addCriterion("send_time <>", value, "sendTime");
			return (Criteria) this;
		}

		public Criteria andSendTimeGreaterThan(Date value) {
			addCriterion("send_time >", value, "sendTime");
			return (Criteria) this;
		}

		public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("send_time >=", value, "sendTime");
			return (Criteria) this;
		}

		public Criteria andSendTimeLessThan(Date value) {
			addCriterion("send_time <", value, "sendTime");
			return (Criteria) this;
		}

		public Criteria andSendTimeLessThanOrEqualTo(Date value) {
			addCriterion("send_time <=", value, "sendTime");
			return (Criteria) this;
		}

		public Criteria andSendTimeIn(List<Date> values) {
			addCriterion("send_time in", values, "sendTime");
			return (Criteria) this;
		}

		public Criteria andSendTimeNotIn(List<Date> values) {
			addCriterion("send_time not in", values, "sendTime");
			return (Criteria) this;
		}

		public Criteria andSendTimeBetween(Date value1, Date value2) {
			addCriterion("send_time between", value1, value2, "sendTime");
			return (Criteria) this;
		}

		public Criteria andSendTimeNotBetween(Date value1, Date value2) {
			addCriterion("send_time not between", value1, value2, "sendTime");
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
	 * This class was generated by MyBatis Generator. This class corresponds to the database table wx_history_message
	 * @mbggenerated  Wed Aug 06 14:43:01 CST 2014
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
     * This class corresponds to the database table wx_history_message
     *
     * @mbggenerated do_not_delete_during_merge Wed Aug 06 13:38:50 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}