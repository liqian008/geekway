package com.bruce.geekway.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WxProductVoucherCriteria {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_product_voucher
	 * @mbggenerated  Tue Sep 23 15:32:54 CST 2014
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_product_voucher
	 * @mbggenerated  Tue Sep 23 15:32:54 CST 2014
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_product_voucher
	 * @mbggenerated  Tue Sep 23 15:32:54 CST 2014
	 */
	protected List<Criteria> oredCriteria;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_product_voucher
	 * @mbggenerated  Tue Sep 23 15:32:54 CST 2014
	 */
	protected Integer limitOffset;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_product_voucher
	 * @mbggenerated  Tue Sep 23 15:32:54 CST 2014
	 */
	protected Integer limitRows;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_voucher
	 * @mbggenerated  Tue Sep 23 15:32:54 CST 2014
	 */
	public WxProductVoucherCriteria() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_voucher
	 * @mbggenerated  Tue Sep 23 15:32:54 CST 2014
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_voucher
	 * @mbggenerated  Tue Sep 23 15:32:54 CST 2014
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_voucher
	 * @mbggenerated  Tue Sep 23 15:32:54 CST 2014
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_voucher
	 * @mbggenerated  Tue Sep 23 15:32:54 CST 2014
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_voucher
	 * @mbggenerated  Tue Sep 23 15:32:54 CST 2014
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_voucher
	 * @mbggenerated  Tue Sep 23 15:32:54 CST 2014
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_voucher
	 * @mbggenerated  Tue Sep 23 15:32:54 CST 2014
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_voucher
	 * @mbggenerated  Tue Sep 23 15:32:54 CST 2014
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_voucher
	 * @mbggenerated  Tue Sep 23 15:32:54 CST 2014
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_voucher
	 * @mbggenerated  Tue Sep 23 15:32:54 CST 2014
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_voucher
	 * @mbggenerated  Tue Sep 23 15:32:54 CST 2014
	 */
	public void setLimitOffset(Integer limitOffset) {
		this.limitOffset = limitOffset;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_voucher
	 * @mbggenerated  Tue Sep 23 15:32:54 CST 2014
	 */
	public Integer getLimitOffset() {
		return limitOffset;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_voucher
	 * @mbggenerated  Tue Sep 23 15:32:54 CST 2014
	 */
	public void setLimitRows(Integer limitRows) {
		this.limitRows = limitRows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_voucher
	 * @mbggenerated  Tue Sep 23 15:32:54 CST 2014
	 */
	public Integer getLimitRows() {
		return limitRows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table wx_product_voucher
	 * @mbggenerated  Tue Sep 23 15:32:54 CST 2014
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

		public Criteria andUserOpenIdIsNull() {
			addCriterion("user_open_id is null");
			return (Criteria) this;
		}

		public Criteria andUserOpenIdIsNotNull() {
			addCriterion("user_open_id is not null");
			return (Criteria) this;
		}

		public Criteria andUserOpenIdEqualTo(String value) {
			addCriterion("user_open_id =", value, "userOpenId");
			return (Criteria) this;
		}

		public Criteria andUserOpenIdNotEqualTo(String value) {
			addCriterion("user_open_id <>", value, "userOpenId");
			return (Criteria) this;
		}

		public Criteria andUserOpenIdGreaterThan(String value) {
			addCriterion("user_open_id >", value, "userOpenId");
			return (Criteria) this;
		}

		public Criteria andUserOpenIdGreaterThanOrEqualTo(String value) {
			addCriterion("user_open_id >=", value, "userOpenId");
			return (Criteria) this;
		}

		public Criteria andUserOpenIdLessThan(String value) {
			addCriterion("user_open_id <", value, "userOpenId");
			return (Criteria) this;
		}

		public Criteria andUserOpenIdLessThanOrEqualTo(String value) {
			addCriterion("user_open_id <=", value, "userOpenId");
			return (Criteria) this;
		}

		public Criteria andUserOpenIdLike(String value) {
			addCriterion("user_open_id like", value, "userOpenId");
			return (Criteria) this;
		}

		public Criteria andUserOpenIdNotLike(String value) {
			addCriterion("user_open_id not like", value, "userOpenId");
			return (Criteria) this;
		}

		public Criteria andUserOpenIdIn(List<String> values) {
			addCriterion("user_open_id in", values, "userOpenId");
			return (Criteria) this;
		}

		public Criteria andUserOpenIdNotIn(List<String> values) {
			addCriterion("user_open_id not in", values, "userOpenId");
			return (Criteria) this;
		}

		public Criteria andUserOpenIdBetween(String value1, String value2) {
			addCriterion("user_open_id between", value1, value2, "userOpenId");
			return (Criteria) this;
		}

		public Criteria andUserOpenIdNotBetween(String value1, String value2) {
			addCriterion("user_open_id not between", value1, value2,
					"userOpenId");
			return (Criteria) this;
		}

		public Criteria andCodeIsNull() {
			addCriterion("code is null");
			return (Criteria) this;
		}

		public Criteria andCodeIsNotNull() {
			addCriterion("code is not null");
			return (Criteria) this;
		}

		public Criteria andCodeEqualTo(String value) {
			addCriterion("code =", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotEqualTo(String value) {
			addCriterion("code <>", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeGreaterThan(String value) {
			addCriterion("code >", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeGreaterThanOrEqualTo(String value) {
			addCriterion("code >=", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeLessThan(String value) {
			addCriterion("code <", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeLessThanOrEqualTo(String value) {
			addCriterion("code <=", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeLike(String value) {
			addCriterion("code like", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotLike(String value) {
			addCriterion("code not like", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeIn(List<String> values) {
			addCriterion("code in", values, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotIn(List<String> values) {
			addCriterion("code not in", values, "code");
			return (Criteria) this;
		}

		public Criteria andCodeBetween(String value1, String value2) {
			addCriterion("code between", value1, value2, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotBetween(String value1, String value2) {
			addCriterion("code not between", value1, value2, "code");
			return (Criteria) this;
		}

		public Criteria andPriceIsNull() {
			addCriterion("price is null");
			return (Criteria) this;
		}

		public Criteria andPriceIsNotNull() {
			addCriterion("price is not null");
			return (Criteria) this;
		}

		public Criteria andPriceEqualTo(Double value) {
			addCriterion("price =", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceNotEqualTo(Double value) {
			addCriterion("price <>", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceGreaterThan(Double value) {
			addCriterion("price >", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceGreaterThanOrEqualTo(Double value) {
			addCriterion("price >=", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceLessThan(Double value) {
			addCriterion("price <", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceLessThanOrEqualTo(Double value) {
			addCriterion("price <=", value, "price");
			return (Criteria) this;
		}

		public Criteria andPriceIn(List<Double> values) {
			addCriterion("price in", values, "price");
			return (Criteria) this;
		}

		public Criteria andPriceNotIn(List<Double> values) {
			addCriterion("price not in", values, "price");
			return (Criteria) this;
		}

		public Criteria andPriceBetween(Double value1, Double value2) {
			addCriterion("price between", value1, value2, "price");
			return (Criteria) this;
		}

		public Criteria andPriceNotBetween(Double value1, Double value2) {
			addCriterion("price not between", value1, value2, "price");
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
	 * This class was generated by MyBatis Generator. This class corresponds to the database table wx_product_voucher
	 * @mbggenerated  Tue Sep 23 15:32:54 CST 2014
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
     * This class corresponds to the database table wx_product_voucher
     *
     * @mbggenerated do_not_delete_during_merge Tue Sep 23 10:22:56 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}