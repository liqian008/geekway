package com.bruce.geekway.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WxAccessTokenCriteria {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_access_token
	 * @mbggenerated  Sun Jan 25 12:35:24 CST 2015
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_access_token
	 * @mbggenerated  Sun Jan 25 12:35:24 CST 2015
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_access_token
	 * @mbggenerated  Sun Jan 25 12:35:24 CST 2015
	 */
	protected List<Criteria> oredCriteria;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_access_token
	 * @mbggenerated  Sun Jan 25 12:35:24 CST 2015
	 */
	protected Integer limitOffset;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_access_token
	 * @mbggenerated  Sun Jan 25 12:35:24 CST 2015
	 */
	protected Integer limitRows;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_access_token
	 * @mbggenerated  Sun Jan 25 12:35:24 CST 2015
	 */
	public WxAccessTokenCriteria() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_access_token
	 * @mbggenerated  Sun Jan 25 12:35:24 CST 2015
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_access_token
	 * @mbggenerated  Sun Jan 25 12:35:24 CST 2015
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_access_token
	 * @mbggenerated  Sun Jan 25 12:35:24 CST 2015
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_access_token
	 * @mbggenerated  Sun Jan 25 12:35:24 CST 2015
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_access_token
	 * @mbggenerated  Sun Jan 25 12:35:24 CST 2015
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_access_token
	 * @mbggenerated  Sun Jan 25 12:35:24 CST 2015
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_access_token
	 * @mbggenerated  Sun Jan 25 12:35:24 CST 2015
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_access_token
	 * @mbggenerated  Sun Jan 25 12:35:24 CST 2015
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_access_token
	 * @mbggenerated  Sun Jan 25 12:35:24 CST 2015
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_access_token
	 * @mbggenerated  Sun Jan 25 12:35:24 CST 2015
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_access_token
	 * @mbggenerated  Sun Jan 25 12:35:24 CST 2015
	 */
	public void setLimitOffset(Integer limitOffset) {
		this.limitOffset = limitOffset;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_access_token
	 * @mbggenerated  Sun Jan 25 12:35:24 CST 2015
	 */
	public Integer getLimitOffset() {
		return limitOffset;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_access_token
	 * @mbggenerated  Sun Jan 25 12:35:24 CST 2015
	 */
	public void setLimitRows(Integer limitRows) {
		this.limitRows = limitRows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_access_token
	 * @mbggenerated  Sun Jan 25 12:35:24 CST 2015
	 */
	public Integer getLimitRows() {
		return limitRows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table wx_access_token
	 * @mbggenerated  Sun Jan 25 12:35:24 CST 2015
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

		public Criteria andAccessTokenIsNull() {
			addCriterion("access_token is null");
			return (Criteria) this;
		}

		public Criteria andAccessTokenIsNotNull() {
			addCriterion("access_token is not null");
			return (Criteria) this;
		}

		public Criteria andAccessTokenEqualTo(String value) {
			addCriterion("access_token =", value, "accessToken");
			return (Criteria) this;
		}

		public Criteria andAccessTokenNotEqualTo(String value) {
			addCriterion("access_token <>", value, "accessToken");
			return (Criteria) this;
		}

		public Criteria andAccessTokenGreaterThan(String value) {
			addCriterion("access_token >", value, "accessToken");
			return (Criteria) this;
		}

		public Criteria andAccessTokenGreaterThanOrEqualTo(String value) {
			addCriterion("access_token >=", value, "accessToken");
			return (Criteria) this;
		}

		public Criteria andAccessTokenLessThan(String value) {
			addCriterion("access_token <", value, "accessToken");
			return (Criteria) this;
		}

		public Criteria andAccessTokenLessThanOrEqualTo(String value) {
			addCriterion("access_token <=", value, "accessToken");
			return (Criteria) this;
		}

		public Criteria andAccessTokenLike(String value) {
			addCriterion("access_token like", value, "accessToken");
			return (Criteria) this;
		}

		public Criteria andAccessTokenNotLike(String value) {
			addCriterion("access_token not like", value, "accessToken");
			return (Criteria) this;
		}

		public Criteria andAccessTokenIn(List<String> values) {
			addCriterion("access_token in", values, "accessToken");
			return (Criteria) this;
		}

		public Criteria andAccessTokenNotIn(List<String> values) {
			addCriterion("access_token not in", values, "accessToken");
			return (Criteria) this;
		}

		public Criteria andAccessTokenBetween(String value1, String value2) {
			addCriterion("access_token between", value1, value2, "accessToken");
			return (Criteria) this;
		}

		public Criteria andAccessTokenNotBetween(String value1, String value2) {
			addCriterion("access_token not between", value1, value2,
					"accessToken");
			return (Criteria) this;
		}

		public Criteria andExpireTimeIsNull() {
			addCriterion("expire_time is null");
			return (Criteria) this;
		}

		public Criteria andExpireTimeIsNotNull() {
			addCriterion("expire_time is not null");
			return (Criteria) this;
		}

		public Criteria andExpireTimeEqualTo(Date value) {
			addCriterion("expire_time =", value, "expireTime");
			return (Criteria) this;
		}

		public Criteria andExpireTimeNotEqualTo(Date value) {
			addCriterion("expire_time <>", value, "expireTime");
			return (Criteria) this;
		}

		public Criteria andExpireTimeGreaterThan(Date value) {
			addCriterion("expire_time >", value, "expireTime");
			return (Criteria) this;
		}

		public Criteria andExpireTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("expire_time >=", value, "expireTime");
			return (Criteria) this;
		}

		public Criteria andExpireTimeLessThan(Date value) {
			addCriterion("expire_time <", value, "expireTime");
			return (Criteria) this;
		}

		public Criteria andExpireTimeLessThanOrEqualTo(Date value) {
			addCriterion("expire_time <=", value, "expireTime");
			return (Criteria) this;
		}

		public Criteria andExpireTimeIn(List<Date> values) {
			addCriterion("expire_time in", values, "expireTime");
			return (Criteria) this;
		}

		public Criteria andExpireTimeNotIn(List<Date> values) {
			addCriterion("expire_time not in", values, "expireTime");
			return (Criteria) this;
		}

		public Criteria andExpireTimeBetween(Date value1, Date value2) {
			addCriterion("expire_time between", value1, value2, "expireTime");
			return (Criteria) this;
		}

		public Criteria andExpireTimeNotBetween(Date value1, Date value2) {
			addCriterion("expire_time not between", value1, value2,
					"expireTime");
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
	 * This class was generated by MyBatis Generator. This class corresponds to the database table wx_access_token
	 * @mbggenerated  Sun Jan 25 12:35:24 CST 2015
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
     * This class corresponds to the database table wx_access_token
     *
     * @mbggenerated do_not_delete_during_merge Tue Aug 12 13:08:57 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}