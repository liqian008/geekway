package com.bruce.geekway.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WxDefaultReplyCriteria {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_default_reply
	 * @mbggenerated  Tue Aug 05 12:24:00 CST 2014
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_default_reply
	 * @mbggenerated  Tue Aug 05 12:24:00 CST 2014
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_default_reply
	 * @mbggenerated  Tue Aug 05 12:24:00 CST 2014
	 */
	protected List<Criteria> oredCriteria;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_default_reply
	 * @mbggenerated  Tue Aug 05 12:24:00 CST 2014
	 */
	protected Integer limitOffset;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_default_reply
	 * @mbggenerated  Tue Aug 05 12:24:00 CST 2014
	 */
	protected Integer limitRows;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_default_reply
	 * @mbggenerated  Tue Aug 05 12:24:00 CST 2014
	 */
	public WxDefaultReplyCriteria() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_default_reply
	 * @mbggenerated  Tue Aug 05 12:24:00 CST 2014
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_default_reply
	 * @mbggenerated  Tue Aug 05 12:24:00 CST 2014
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_default_reply
	 * @mbggenerated  Tue Aug 05 12:24:00 CST 2014
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_default_reply
	 * @mbggenerated  Tue Aug 05 12:24:00 CST 2014
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_default_reply
	 * @mbggenerated  Tue Aug 05 12:24:00 CST 2014
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_default_reply
	 * @mbggenerated  Tue Aug 05 12:24:00 CST 2014
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_default_reply
	 * @mbggenerated  Tue Aug 05 12:24:00 CST 2014
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_default_reply
	 * @mbggenerated  Tue Aug 05 12:24:00 CST 2014
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_default_reply
	 * @mbggenerated  Tue Aug 05 12:24:00 CST 2014
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_default_reply
	 * @mbggenerated  Tue Aug 05 12:24:00 CST 2014
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_default_reply
	 * @mbggenerated  Tue Aug 05 12:24:00 CST 2014
	 */
	public void setLimitOffset(Integer limitOffset) {
		this.limitOffset = limitOffset;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_default_reply
	 * @mbggenerated  Tue Aug 05 12:24:00 CST 2014
	 */
	public Integer getLimitOffset() {
		return limitOffset;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_default_reply
	 * @mbggenerated  Tue Aug 05 12:24:00 CST 2014
	 */
	public void setLimitRows(Integer limitRows) {
		this.limitRows = limitRows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_default_reply
	 * @mbggenerated  Tue Aug 05 12:24:00 CST 2014
	 */
	public Integer getLimitRows() {
		return limitRows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table wx_default_reply
	 * @mbggenerated  Tue Aug 05 12:24:00 CST 2014
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

		public Criteria andNewSubscribeReplyIsNull() {
			addCriterion("new_subscribe_reply is null");
			return (Criteria) this;
		}

		public Criteria andNewSubscribeReplyIsNotNull() {
			addCriterion("new_subscribe_reply is not null");
			return (Criteria) this;
		}

		public Criteria andNewSubscribeReplyEqualTo(String value) {
			addCriterion("new_subscribe_reply =", value, "newSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andNewSubscribeReplyNotEqualTo(String value) {
			addCriterion("new_subscribe_reply <>", value, "newSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andNewSubscribeReplyGreaterThan(String value) {
			addCriterion("new_subscribe_reply >", value, "newSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andNewSubscribeReplyGreaterThanOrEqualTo(String value) {
			addCriterion("new_subscribe_reply >=", value, "newSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andNewSubscribeReplyLessThan(String value) {
			addCriterion("new_subscribe_reply <", value, "newSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andNewSubscribeReplyLessThanOrEqualTo(String value) {
			addCriterion("new_subscribe_reply <=", value, "newSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andNewSubscribeReplyLike(String value) {
			addCriterion("new_subscribe_reply like", value, "newSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andNewSubscribeReplyNotLike(String value) {
			addCriterion("new_subscribe_reply not like", value,
					"newSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andNewSubscribeReplyIn(List<String> values) {
			addCriterion("new_subscribe_reply in", values, "newSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andNewSubscribeReplyNotIn(List<String> values) {
			addCriterion("new_subscribe_reply not in", values,
					"newSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andNewSubscribeReplyBetween(String value1, String value2) {
			addCriterion("new_subscribe_reply between", value1, value2,
					"newSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andNewSubscribeReplyNotBetween(String value1,
				String value2) {
			addCriterion("new_subscribe_reply not between", value1, value2,
					"newSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andReSubscribeReplyIsNull() {
			addCriterion("re_subscribe_reply is null");
			return (Criteria) this;
		}

		public Criteria andReSubscribeReplyIsNotNull() {
			addCriterion("re_subscribe_reply is not null");
			return (Criteria) this;
		}

		public Criteria andReSubscribeReplyEqualTo(String value) {
			addCriterion("re_subscribe_reply =", value, "reSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andReSubscribeReplyNotEqualTo(String value) {
			addCriterion("re_subscribe_reply <>", value, "reSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andReSubscribeReplyGreaterThan(String value) {
			addCriterion("re_subscribe_reply >", value, "reSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andReSubscribeReplyGreaterThanOrEqualTo(String value) {
			addCriterion("re_subscribe_reply >=", value, "reSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andReSubscribeReplyLessThan(String value) {
			addCriterion("re_subscribe_reply <", value, "reSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andReSubscribeReplyLessThanOrEqualTo(String value) {
			addCriterion("re_subscribe_reply <=", value, "reSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andReSubscribeReplyLike(String value) {
			addCriterion("re_subscribe_reply like", value, "reSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andReSubscribeReplyNotLike(String value) {
			addCriterion("re_subscribe_reply not like", value,
					"reSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andReSubscribeReplyIn(List<String> values) {
			addCriterion("re_subscribe_reply in", values, "reSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andReSubscribeReplyNotIn(List<String> values) {
			addCriterion("re_subscribe_reply not in", values,
					"reSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andReSubscribeReplyBetween(String value1, String value2) {
			addCriterion("re_subscribe_reply between", value1, value2,
					"reSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andReSubscribeReplyNotBetween(String value1,
				String value2) {
			addCriterion("re_subscribe_reply not between", value1, value2,
					"reSubscribeReply");
			return (Criteria) this;
		}

		public Criteria andTextReplyIsNull() {
			addCriterion("text_reply is null");
			return (Criteria) this;
		}

		public Criteria andTextReplyIsNotNull() {
			addCriterion("text_reply is not null");
			return (Criteria) this;
		}

		public Criteria andTextReplyEqualTo(String value) {
			addCriterion("text_reply =", value, "textReply");
			return (Criteria) this;
		}

		public Criteria andTextReplyNotEqualTo(String value) {
			addCriterion("text_reply <>", value, "textReply");
			return (Criteria) this;
		}

		public Criteria andTextReplyGreaterThan(String value) {
			addCriterion("text_reply >", value, "textReply");
			return (Criteria) this;
		}

		public Criteria andTextReplyGreaterThanOrEqualTo(String value) {
			addCriterion("text_reply >=", value, "textReply");
			return (Criteria) this;
		}

		public Criteria andTextReplyLessThan(String value) {
			addCriterion("text_reply <", value, "textReply");
			return (Criteria) this;
		}

		public Criteria andTextReplyLessThanOrEqualTo(String value) {
			addCriterion("text_reply <=", value, "textReply");
			return (Criteria) this;
		}

		public Criteria andTextReplyLike(String value) {
			addCriterion("text_reply like", value, "textReply");
			return (Criteria) this;
		}

		public Criteria andTextReplyNotLike(String value) {
			addCriterion("text_reply not like", value, "textReply");
			return (Criteria) this;
		}

		public Criteria andTextReplyIn(List<String> values) {
			addCriterion("text_reply in", values, "textReply");
			return (Criteria) this;
		}

		public Criteria andTextReplyNotIn(List<String> values) {
			addCriterion("text_reply not in", values, "textReply");
			return (Criteria) this;
		}

		public Criteria andTextReplyBetween(String value1, String value2) {
			addCriterion("text_reply between", value1, value2, "textReply");
			return (Criteria) this;
		}

		public Criteria andTextReplyNotBetween(String value1, String value2) {
			addCriterion("text_reply not between", value1, value2, "textReply");
			return (Criteria) this;
		}

		public Criteria andImageReplyIsNull() {
			addCriterion("image_reply is null");
			return (Criteria) this;
		}

		public Criteria andImageReplyIsNotNull() {
			addCriterion("image_reply is not null");
			return (Criteria) this;
		}

		public Criteria andImageReplyEqualTo(String value) {
			addCriterion("image_reply =", value, "imageReply");
			return (Criteria) this;
		}

		public Criteria andImageReplyNotEqualTo(String value) {
			addCriterion("image_reply <>", value, "imageReply");
			return (Criteria) this;
		}

		public Criteria andImageReplyGreaterThan(String value) {
			addCriterion("image_reply >", value, "imageReply");
			return (Criteria) this;
		}

		public Criteria andImageReplyGreaterThanOrEqualTo(String value) {
			addCriterion("image_reply >=", value, "imageReply");
			return (Criteria) this;
		}

		public Criteria andImageReplyLessThan(String value) {
			addCriterion("image_reply <", value, "imageReply");
			return (Criteria) this;
		}

		public Criteria andImageReplyLessThanOrEqualTo(String value) {
			addCriterion("image_reply <=", value, "imageReply");
			return (Criteria) this;
		}

		public Criteria andImageReplyLike(String value) {
			addCriterion("image_reply like", value, "imageReply");
			return (Criteria) this;
		}

		public Criteria andImageReplyNotLike(String value) {
			addCriterion("image_reply not like", value, "imageReply");
			return (Criteria) this;
		}

		public Criteria andImageReplyIn(List<String> values) {
			addCriterion("image_reply in", values, "imageReply");
			return (Criteria) this;
		}

		public Criteria andImageReplyNotIn(List<String> values) {
			addCriterion("image_reply not in", values, "imageReply");
			return (Criteria) this;
		}

		public Criteria andImageReplyBetween(String value1, String value2) {
			addCriterion("image_reply between", value1, value2, "imageReply");
			return (Criteria) this;
		}

		public Criteria andImageReplyNotBetween(String value1, String value2) {
			addCriterion("image_reply not between", value1, value2,
					"imageReply");
			return (Criteria) this;
		}

		public Criteria andVoiceReplyIsNull() {
			addCriterion("voice_reply is null");
			return (Criteria) this;
		}

		public Criteria andVoiceReplyIsNotNull() {
			addCriterion("voice_reply is not null");
			return (Criteria) this;
		}

		public Criteria andVoiceReplyEqualTo(String value) {
			addCriterion("voice_reply =", value, "voiceReply");
			return (Criteria) this;
		}

		public Criteria andVoiceReplyNotEqualTo(String value) {
			addCriterion("voice_reply <>", value, "voiceReply");
			return (Criteria) this;
		}

		public Criteria andVoiceReplyGreaterThan(String value) {
			addCriterion("voice_reply >", value, "voiceReply");
			return (Criteria) this;
		}

		public Criteria andVoiceReplyGreaterThanOrEqualTo(String value) {
			addCriterion("voice_reply >=", value, "voiceReply");
			return (Criteria) this;
		}

		public Criteria andVoiceReplyLessThan(String value) {
			addCriterion("voice_reply <", value, "voiceReply");
			return (Criteria) this;
		}

		public Criteria andVoiceReplyLessThanOrEqualTo(String value) {
			addCriterion("voice_reply <=", value, "voiceReply");
			return (Criteria) this;
		}

		public Criteria andVoiceReplyLike(String value) {
			addCriterion("voice_reply like", value, "voiceReply");
			return (Criteria) this;
		}

		public Criteria andVoiceReplyNotLike(String value) {
			addCriterion("voice_reply not like", value, "voiceReply");
			return (Criteria) this;
		}

		public Criteria andVoiceReplyIn(List<String> values) {
			addCriterion("voice_reply in", values, "voiceReply");
			return (Criteria) this;
		}

		public Criteria andVoiceReplyNotIn(List<String> values) {
			addCriterion("voice_reply not in", values, "voiceReply");
			return (Criteria) this;
		}

		public Criteria andVoiceReplyBetween(String value1, String value2) {
			addCriterion("voice_reply between", value1, value2, "voiceReply");
			return (Criteria) this;
		}

		public Criteria andVoiceReplyNotBetween(String value1, String value2) {
			addCriterion("voice_reply not between", value1, value2,
					"voiceReply");
			return (Criteria) this;
		}

		public Criteria andMenuClickReplyIsNull() {
			addCriterion("menu_click_reply is null");
			return (Criteria) this;
		}

		public Criteria andMenuClickReplyIsNotNull() {
			addCriterion("menu_click_reply is not null");
			return (Criteria) this;
		}

		public Criteria andMenuClickReplyEqualTo(String value) {
			addCriterion("menu_click_reply =", value, "menuClickReply");
			return (Criteria) this;
		}

		public Criteria andMenuClickReplyNotEqualTo(String value) {
			addCriterion("menu_click_reply <>", value, "menuClickReply");
			return (Criteria) this;
		}

		public Criteria andMenuClickReplyGreaterThan(String value) {
			addCriterion("menu_click_reply >", value, "menuClickReply");
			return (Criteria) this;
		}

		public Criteria andMenuClickReplyGreaterThanOrEqualTo(String value) {
			addCriterion("menu_click_reply >=", value, "menuClickReply");
			return (Criteria) this;
		}

		public Criteria andMenuClickReplyLessThan(String value) {
			addCriterion("menu_click_reply <", value, "menuClickReply");
			return (Criteria) this;
		}

		public Criteria andMenuClickReplyLessThanOrEqualTo(String value) {
			addCriterion("menu_click_reply <=", value, "menuClickReply");
			return (Criteria) this;
		}

		public Criteria andMenuClickReplyLike(String value) {
			addCriterion("menu_click_reply like", value, "menuClickReply");
			return (Criteria) this;
		}

		public Criteria andMenuClickReplyNotLike(String value) {
			addCriterion("menu_click_reply not like", value, "menuClickReply");
			return (Criteria) this;
		}

		public Criteria andMenuClickReplyIn(List<String> values) {
			addCriterion("menu_click_reply in", values, "menuClickReply");
			return (Criteria) this;
		}

		public Criteria andMenuClickReplyNotIn(List<String> values) {
			addCriterion("menu_click_reply not in", values, "menuClickReply");
			return (Criteria) this;
		}

		public Criteria andMenuClickReplyBetween(String value1, String value2) {
			addCriterion("menu_click_reply between", value1, value2,
					"menuClickReply");
			return (Criteria) this;
		}

		public Criteria andMenuClickReplyNotBetween(String value1, String value2) {
			addCriterion("menu_click_reply not between", value1, value2,
					"menuClickReply");
			return (Criteria) this;
		}

		public Criteria andLocationReplyIsNull() {
			addCriterion("location_reply is null");
			return (Criteria) this;
		}

		public Criteria andLocationReplyIsNotNull() {
			addCriterion("location_reply is not null");
			return (Criteria) this;
		}

		public Criteria andLocationReplyEqualTo(String value) {
			addCriterion("location_reply =", value, "locationReply");
			return (Criteria) this;
		}

		public Criteria andLocationReplyNotEqualTo(String value) {
			addCriterion("location_reply <>", value, "locationReply");
			return (Criteria) this;
		}

		public Criteria andLocationReplyGreaterThan(String value) {
			addCriterion("location_reply >", value, "locationReply");
			return (Criteria) this;
		}

		public Criteria andLocationReplyGreaterThanOrEqualTo(String value) {
			addCriterion("location_reply >=", value, "locationReply");
			return (Criteria) this;
		}

		public Criteria andLocationReplyLessThan(String value) {
			addCriterion("location_reply <", value, "locationReply");
			return (Criteria) this;
		}

		public Criteria andLocationReplyLessThanOrEqualTo(String value) {
			addCriterion("location_reply <=", value, "locationReply");
			return (Criteria) this;
		}

		public Criteria andLocationReplyLike(String value) {
			addCriterion("location_reply like", value, "locationReply");
			return (Criteria) this;
		}

		public Criteria andLocationReplyNotLike(String value) {
			addCriterion("location_reply not like", value, "locationReply");
			return (Criteria) this;
		}

		public Criteria andLocationReplyIn(List<String> values) {
			addCriterion("location_reply in", values, "locationReply");
			return (Criteria) this;
		}

		public Criteria andLocationReplyNotIn(List<String> values) {
			addCriterion("location_reply not in", values, "locationReply");
			return (Criteria) this;
		}

		public Criteria andLocationReplyBetween(String value1, String value2) {
			addCriterion("location_reply between", value1, value2,
					"locationReply");
			return (Criteria) this;
		}

		public Criteria andLocationReplyNotBetween(String value1, String value2) {
			addCriterion("location_reply not between", value1, value2,
					"locationReply");
			return (Criteria) this;
		}

		public Criteria andVideoReplyIsNull() {
			addCriterion("video_reply is null");
			return (Criteria) this;
		}

		public Criteria andVideoReplyIsNotNull() {
			addCriterion("video_reply is not null");
			return (Criteria) this;
		}

		public Criteria andVideoReplyEqualTo(String value) {
			addCriterion("video_reply =", value, "videoReply");
			return (Criteria) this;
		}

		public Criteria andVideoReplyNotEqualTo(String value) {
			addCriterion("video_reply <>", value, "videoReply");
			return (Criteria) this;
		}

		public Criteria andVideoReplyGreaterThan(String value) {
			addCriterion("video_reply >", value, "videoReply");
			return (Criteria) this;
		}

		public Criteria andVideoReplyGreaterThanOrEqualTo(String value) {
			addCriterion("video_reply >=", value, "videoReply");
			return (Criteria) this;
		}

		public Criteria andVideoReplyLessThan(String value) {
			addCriterion("video_reply <", value, "videoReply");
			return (Criteria) this;
		}

		public Criteria andVideoReplyLessThanOrEqualTo(String value) {
			addCriterion("video_reply <=", value, "videoReply");
			return (Criteria) this;
		}

		public Criteria andVideoReplyLike(String value) {
			addCriterion("video_reply like", value, "videoReply");
			return (Criteria) this;
		}

		public Criteria andVideoReplyNotLike(String value) {
			addCriterion("video_reply not like", value, "videoReply");
			return (Criteria) this;
		}

		public Criteria andVideoReplyIn(List<String> values) {
			addCriterion("video_reply in", values, "videoReply");
			return (Criteria) this;
		}

		public Criteria andVideoReplyNotIn(List<String> values) {
			addCriterion("video_reply not in", values, "videoReply");
			return (Criteria) this;
		}

		public Criteria andVideoReplyBetween(String value1, String value2) {
			addCriterion("video_reply between", value1, value2, "videoReply");
			return (Criteria) this;
		}

		public Criteria andVideoReplyNotBetween(String value1, String value2) {
			addCriterion("video_reply not between", value1, value2,
					"videoReply");
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
	 * This class was generated by MyBatis Generator. This class corresponds to the database table wx_default_reply
	 * @mbggenerated  Tue Aug 05 12:24:00 CST 2014
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
     * This class corresponds to the database table wx_default_reply
     *
     * @mbggenerated do_not_delete_during_merge Tue Aug 05 12:23:12 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}