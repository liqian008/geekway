package com.bruce.geekway.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItoProductSkuValueCriteria {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table ito_product_sku_value
	 * @mbggenerated  Fri May 16 17:02:23 CST 2014
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table ito_product_sku_value
	 * @mbggenerated  Fri May 16 17:02:23 CST 2014
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table ito_product_sku_value
	 * @mbggenerated  Fri May 16 17:02:23 CST 2014
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product_sku_value
	 * @mbggenerated  Fri May 16 17:02:23 CST 2014
	 */
	public ItoProductSkuValueCriteria() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product_sku_value
	 * @mbggenerated  Fri May 16 17:02:23 CST 2014
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product_sku_value
	 * @mbggenerated  Fri May 16 17:02:23 CST 2014
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product_sku_value
	 * @mbggenerated  Fri May 16 17:02:23 CST 2014
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product_sku_value
	 * @mbggenerated  Fri May 16 17:02:23 CST 2014
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product_sku_value
	 * @mbggenerated  Fri May 16 17:02:23 CST 2014
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product_sku_value
	 * @mbggenerated  Fri May 16 17:02:23 CST 2014
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product_sku_value
	 * @mbggenerated  Fri May 16 17:02:23 CST 2014
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product_sku_value
	 * @mbggenerated  Fri May 16 17:02:23 CST 2014
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product_sku_value
	 * @mbggenerated  Fri May 16 17:02:23 CST 2014
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product_sku_value
	 * @mbggenerated  Fri May 16 17:02:23 CST 2014
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table ito_product_sku_value
	 * @mbggenerated  Fri May 16 17:02:23 CST 2014
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

		public Criteria andProductIdIsNull() {
			addCriterion("product_id is null");
			return (Criteria) this;
		}

		public Criteria andProductIdIsNotNull() {
			addCriterion("product_id is not null");
			return (Criteria) this;
		}

		public Criteria andProductIdEqualTo(Integer value) {
			addCriterion("product_id =", value, "productId");
			return (Criteria) this;
		}

		public Criteria andProductIdNotEqualTo(Integer value) {
			addCriterion("product_id <>", value, "productId");
			return (Criteria) this;
		}

		public Criteria andProductIdGreaterThan(Integer value) {
			addCriterion("product_id >", value, "productId");
			return (Criteria) this;
		}

		public Criteria andProductIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("product_id >=", value, "productId");
			return (Criteria) this;
		}

		public Criteria andProductIdLessThan(Integer value) {
			addCriterion("product_id <", value, "productId");
			return (Criteria) this;
		}

		public Criteria andProductIdLessThanOrEqualTo(Integer value) {
			addCriterion("product_id <=", value, "productId");
			return (Criteria) this;
		}

		public Criteria andProductIdIn(List<Integer> values) {
			addCriterion("product_id in", values, "productId");
			return (Criteria) this;
		}

		public Criteria andProductIdNotIn(List<Integer> values) {
			addCriterion("product_id not in", values, "productId");
			return (Criteria) this;
		}

		public Criteria andProductIdBetween(Integer value1, Integer value2) {
			addCriterion("product_id between", value1, value2, "productId");
			return (Criteria) this;
		}

		public Criteria andProductIdNotBetween(Integer value1, Integer value2) {
			addCriterion("product_id not between", value1, value2, "productId");
			return (Criteria) this;
		}

		public Criteria andSkuPropValueIdIsNull() {
			addCriterion("sku_prop_value_id is null");
			return (Criteria) this;
		}

		public Criteria andSkuPropValueIdIsNotNull() {
			addCriterion("sku_prop_value_id is not null");
			return (Criteria) this;
		}

		public Criteria andSkuPropValueIdEqualTo(Integer value) {
			addCriterion("sku_prop_value_id =", value, "skuPropValueId");
			return (Criteria) this;
		}

		public Criteria andSkuPropValueIdNotEqualTo(Integer value) {
			addCriterion("sku_prop_value_id <>", value, "skuPropValueId");
			return (Criteria) this;
		}

		public Criteria andSkuPropValueIdGreaterThan(Integer value) {
			addCriterion("sku_prop_value_id >", value, "skuPropValueId");
			return (Criteria) this;
		}

		public Criteria andSkuPropValueIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("sku_prop_value_id >=", value, "skuPropValueId");
			return (Criteria) this;
		}

		public Criteria andSkuPropValueIdLessThan(Integer value) {
			addCriterion("sku_prop_value_id <", value, "skuPropValueId");
			return (Criteria) this;
		}

		public Criteria andSkuPropValueIdLessThanOrEqualTo(Integer value) {
			addCriterion("sku_prop_value_id <=", value, "skuPropValueId");
			return (Criteria) this;
		}

		public Criteria andSkuPropValueIdIn(List<Integer> values) {
			addCriterion("sku_prop_value_id in", values, "skuPropValueId");
			return (Criteria) this;
		}

		public Criteria andSkuPropValueIdNotIn(List<Integer> values) {
			addCriterion("sku_prop_value_id not in", values, "skuPropValueId");
			return (Criteria) this;
		}

		public Criteria andSkuPropValueIdBetween(Integer value1, Integer value2) {
			addCriterion("sku_prop_value_id between", value1, value2,
					"skuPropValueId");
			return (Criteria) this;
		}

		public Criteria andSkuPropValueIdNotBetween(Integer value1,
				Integer value2) {
			addCriterion("sku_prop_value_id not between", value1, value2,
					"skuPropValueId");
			return (Criteria) this;
		}

		public Criteria andSkuPicUrlIsNull() {
			addCriterion("sku_pic_url is null");
			return (Criteria) this;
		}

		public Criteria andSkuPicUrlIsNotNull() {
			addCriterion("sku_pic_url is not null");
			return (Criteria) this;
		}

		public Criteria andSkuPicUrlEqualTo(String value) {
			addCriterion("sku_pic_url =", value, "skuPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuPicUrlNotEqualTo(String value) {
			addCriterion("sku_pic_url <>", value, "skuPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuPicUrlGreaterThan(String value) {
			addCriterion("sku_pic_url >", value, "skuPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuPicUrlGreaterThanOrEqualTo(String value) {
			addCriterion("sku_pic_url >=", value, "skuPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuPicUrlLessThan(String value) {
			addCriterion("sku_pic_url <", value, "skuPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuPicUrlLessThanOrEqualTo(String value) {
			addCriterion("sku_pic_url <=", value, "skuPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuPicUrlLike(String value) {
			addCriterion("sku_pic_url like", value, "skuPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuPicUrlNotLike(String value) {
			addCriterion("sku_pic_url not like", value, "skuPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuPicUrlIn(List<String> values) {
			addCriterion("sku_pic_url in", values, "skuPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuPicUrlNotIn(List<String> values) {
			addCriterion("sku_pic_url not in", values, "skuPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuPicUrlBetween(String value1, String value2) {
			addCriterion("sku_pic_url between", value1, value2, "skuPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuPicUrlNotBetween(String value1, String value2) {
			addCriterion("sku_pic_url not between", value1, value2, "skuPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuThumbPicUrlIsNull() {
			addCriterion("sku_thumb_pic_url is null");
			return (Criteria) this;
		}

		public Criteria andSkuThumbPicUrlIsNotNull() {
			addCriterion("sku_thumb_pic_url is not null");
			return (Criteria) this;
		}

		public Criteria andSkuThumbPicUrlEqualTo(String value) {
			addCriterion("sku_thumb_pic_url =", value, "skuThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuThumbPicUrlNotEqualTo(String value) {
			addCriterion("sku_thumb_pic_url <>", value, "skuThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuThumbPicUrlGreaterThan(String value) {
			addCriterion("sku_thumb_pic_url >", value, "skuThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuThumbPicUrlGreaterThanOrEqualTo(String value) {
			addCriterion("sku_thumb_pic_url >=", value, "skuThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuThumbPicUrlLessThan(String value) {
			addCriterion("sku_thumb_pic_url <", value, "skuThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuThumbPicUrlLessThanOrEqualTo(String value) {
			addCriterion("sku_thumb_pic_url <=", value, "skuThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuThumbPicUrlLike(String value) {
			addCriterion("sku_thumb_pic_url like", value, "skuThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuThumbPicUrlNotLike(String value) {
			addCriterion("sku_thumb_pic_url not like", value, "skuThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuThumbPicUrlIn(List<String> values) {
			addCriterion("sku_thumb_pic_url in", values, "skuThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuThumbPicUrlNotIn(List<String> values) {
			addCriterion("sku_thumb_pic_url not in", values, "skuThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuThumbPicUrlBetween(String value1, String value2) {
			addCriterion("sku_thumb_pic_url between", value1, value2,
					"skuThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andSkuThumbPicUrlNotBetween(String value1, String value2) {
			addCriterion("sku_thumb_pic_url not between", value1, value2,
					"skuThumbPicUrl");
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
	 * This class was generated by MyBatis Generator. This class corresponds to the database table ito_product_sku_value
	 * @mbggenerated  Fri May 16 17:02:23 CST 2014
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
     * This class corresponds to the database table ito_product_sku_value
     *
     * @mbggenerated do_not_delete_during_merge Sat May 03 14:51:30 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}