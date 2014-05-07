package com.bruce.geekway.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItoSkuCriteria {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table ito_sku
	 * @mbggenerated  Wed May 07 23:29:06 CST 2014
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table ito_sku
	 * @mbggenerated  Wed May 07 23:29:06 CST 2014
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table ito_sku
	 * @mbggenerated  Wed May 07 23:29:06 CST 2014
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_sku
	 * @mbggenerated  Wed May 07 23:29:06 CST 2014
	 */
	public ItoSkuCriteria() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_sku
	 * @mbggenerated  Wed May 07 23:29:06 CST 2014
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_sku
	 * @mbggenerated  Wed May 07 23:29:06 CST 2014
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_sku
	 * @mbggenerated  Wed May 07 23:29:06 CST 2014
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_sku
	 * @mbggenerated  Wed May 07 23:29:06 CST 2014
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_sku
	 * @mbggenerated  Wed May 07 23:29:06 CST 2014
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_sku
	 * @mbggenerated  Wed May 07 23:29:06 CST 2014
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_sku
	 * @mbggenerated  Wed May 07 23:29:06 CST 2014
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_sku
	 * @mbggenerated  Wed May 07 23:29:06 CST 2014
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_sku
	 * @mbggenerated  Wed May 07 23:29:06 CST 2014
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_sku
	 * @mbggenerated  Wed May 07 23:29:06 CST 2014
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table ito_sku
	 * @mbggenerated  Wed May 07 23:29:06 CST 2014
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

		public Criteria andOutIdIsNull() {
			addCriterion("out_id is null");
			return (Criteria) this;
		}

		public Criteria andOutIdIsNotNull() {
			addCriterion("out_id is not null");
			return (Criteria) this;
		}

		public Criteria andOutIdEqualTo(String value) {
			addCriterion("out_id =", value, "outId");
			return (Criteria) this;
		}

		public Criteria andOutIdNotEqualTo(String value) {
			addCriterion("out_id <>", value, "outId");
			return (Criteria) this;
		}

		public Criteria andOutIdGreaterThan(String value) {
			addCriterion("out_id >", value, "outId");
			return (Criteria) this;
		}

		public Criteria andOutIdGreaterThanOrEqualTo(String value) {
			addCriterion("out_id >=", value, "outId");
			return (Criteria) this;
		}

		public Criteria andOutIdLessThan(String value) {
			addCriterion("out_id <", value, "outId");
			return (Criteria) this;
		}

		public Criteria andOutIdLessThanOrEqualTo(String value) {
			addCriterion("out_id <=", value, "outId");
			return (Criteria) this;
		}

		public Criteria andOutIdLike(String value) {
			addCriterion("out_id like", value, "outId");
			return (Criteria) this;
		}

		public Criteria andOutIdNotLike(String value) {
			addCriterion("out_id not like", value, "outId");
			return (Criteria) this;
		}

		public Criteria andOutIdIn(List<String> values) {
			addCriterion("out_id in", values, "outId");
			return (Criteria) this;
		}

		public Criteria andOutIdNotIn(List<String> values) {
			addCriterion("out_id not in", values, "outId");
			return (Criteria) this;
		}

		public Criteria andOutIdBetween(String value1, String value2) {
			addCriterion("out_id between", value1, value2, "outId");
			return (Criteria) this;
		}

		public Criteria andOutIdNotBetween(String value1, String value2) {
			addCriterion("out_id not between", value1, value2, "outId");
			return (Criteria) this;
		}

		public Criteria andNameIsNull() {
			addCriterion("name is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("name is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("name =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("name <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("name >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("name >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("name <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("name <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("name like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("name not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("name in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("name not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("name between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("name not between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andPropertiesNameIsNull() {
			addCriterion("properties_name is null");
			return (Criteria) this;
		}

		public Criteria andPropertiesNameIsNotNull() {
			addCriterion("properties_name is not null");
			return (Criteria) this;
		}

		public Criteria andPropertiesNameEqualTo(String value) {
			addCriterion("properties_name =", value, "propertiesName");
			return (Criteria) this;
		}

		public Criteria andPropertiesNameNotEqualTo(String value) {
			addCriterion("properties_name <>", value, "propertiesName");
			return (Criteria) this;
		}

		public Criteria andPropertiesNameGreaterThan(String value) {
			addCriterion("properties_name >", value, "propertiesName");
			return (Criteria) this;
		}

		public Criteria andPropertiesNameGreaterThanOrEqualTo(String value) {
			addCriterion("properties_name >=", value, "propertiesName");
			return (Criteria) this;
		}

		public Criteria andPropertiesNameLessThan(String value) {
			addCriterion("properties_name <", value, "propertiesName");
			return (Criteria) this;
		}

		public Criteria andPropertiesNameLessThanOrEqualTo(String value) {
			addCriterion("properties_name <=", value, "propertiesName");
			return (Criteria) this;
		}

		public Criteria andPropertiesNameLike(String value) {
			addCriterion("properties_name like", value, "propertiesName");
			return (Criteria) this;
		}

		public Criteria andPropertiesNameNotLike(String value) {
			addCriterion("properties_name not like", value, "propertiesName");
			return (Criteria) this;
		}

		public Criteria andPropertiesNameIn(List<String> values) {
			addCriterion("properties_name in", values, "propertiesName");
			return (Criteria) this;
		}

		public Criteria andPropertiesNameNotIn(List<String> values) {
			addCriterion("properties_name not in", values, "propertiesName");
			return (Criteria) this;
		}

		public Criteria andPropertiesNameBetween(String value1, String value2) {
			addCriterion("properties_name between", value1, value2, "propertiesName");
			return (Criteria) this;
		}

		public Criteria andPropertiesNameNotBetween(String value1, String value2) {
			addCriterion("properties_name not between", value1, value2, "propertiesName");
			return (Criteria) this;
		}

		public Criteria andQualityIsNull() {
			addCriterion("quality is null");
			return (Criteria) this;
		}

		public Criteria andQualityIsNotNull() {
			addCriterion("quality is not null");
			return (Criteria) this;
		}

		public Criteria andQualityEqualTo(Integer value) {
			addCriterion("quality =", value, "quality");
			return (Criteria) this;
		}

		public Criteria andQualityNotEqualTo(Integer value) {
			addCriterion("quality <>", value, "quality");
			return (Criteria) this;
		}

		public Criteria andQualityGreaterThan(Integer value) {
			addCriterion("quality >", value, "quality");
			return (Criteria) this;
		}

		public Criteria andQualityGreaterThanOrEqualTo(Integer value) {
			addCriterion("quality >=", value, "quality");
			return (Criteria) this;
		}

		public Criteria andQualityLessThan(Integer value) {
			addCriterion("quality <", value, "quality");
			return (Criteria) this;
		}

		public Criteria andQualityLessThanOrEqualTo(Integer value) {
			addCriterion("quality <=", value, "quality");
			return (Criteria) this;
		}

		public Criteria andQualityIn(List<Integer> values) {
			addCriterion("quality in", values, "quality");
			return (Criteria) this;
		}

		public Criteria andQualityNotIn(List<Integer> values) {
			addCriterion("quality not in", values, "quality");
			return (Criteria) this;
		}

		public Criteria andQualityBetween(Integer value1, Integer value2) {
			addCriterion("quality between", value1, value2, "quality");
			return (Criteria) this;
		}

		public Criteria andQualityNotBetween(Integer value1, Integer value2) {
			addCriterion("quality not between", value1, value2, "quality");
			return (Criteria) this;
		}

		public Criteria andOriginPriceIsNull() {
			addCriterion("origin_price is null");
			return (Criteria) this;
		}

		public Criteria andOriginPriceIsNotNull() {
			addCriterion("origin_price is not null");
			return (Criteria) this;
		}

		public Criteria andOriginPriceEqualTo(Double value) {
			addCriterion("origin_price =", value, "originPrice");
			return (Criteria) this;
		}

		public Criteria andOriginPriceNotEqualTo(Double value) {
			addCriterion("origin_price <>", value, "originPrice");
			return (Criteria) this;
		}

		public Criteria andOriginPriceGreaterThan(Double value) {
			addCriterion("origin_price >", value, "originPrice");
			return (Criteria) this;
		}

		public Criteria andOriginPriceGreaterThanOrEqualTo(Double value) {
			addCriterion("origin_price >=", value, "originPrice");
			return (Criteria) this;
		}

		public Criteria andOriginPriceLessThan(Double value) {
			addCriterion("origin_price <", value, "originPrice");
			return (Criteria) this;
		}

		public Criteria andOriginPriceLessThanOrEqualTo(Double value) {
			addCriterion("origin_price <=", value, "originPrice");
			return (Criteria) this;
		}

		public Criteria andOriginPriceIn(List<Double> values) {
			addCriterion("origin_price in", values, "originPrice");
			return (Criteria) this;
		}

		public Criteria andOriginPriceNotIn(List<Double> values) {
			addCriterion("origin_price not in", values, "originPrice");
			return (Criteria) this;
		}

		public Criteria andOriginPriceBetween(Double value1, Double value2) {
			addCriterion("origin_price between", value1, value2, "originPrice");
			return (Criteria) this;
		}

		public Criteria andOriginPriceNotBetween(Double value1, Double value2) {
			addCriterion("origin_price not between", value1, value2, "originPrice");
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

		public Criteria andBuyUrlIsNull() {
			addCriterion("buy_url is null");
			return (Criteria) this;
		}

		public Criteria andBuyUrlIsNotNull() {
			addCriterion("buy_url is not null");
			return (Criteria) this;
		}

		public Criteria andBuyUrlEqualTo(String value) {
			addCriterion("buy_url =", value, "buyUrl");
			return (Criteria) this;
		}

		public Criteria andBuyUrlNotEqualTo(String value) {
			addCriterion("buy_url <>", value, "buyUrl");
			return (Criteria) this;
		}

		public Criteria andBuyUrlGreaterThan(String value) {
			addCriterion("buy_url >", value, "buyUrl");
			return (Criteria) this;
		}

		public Criteria andBuyUrlGreaterThanOrEqualTo(String value) {
			addCriterion("buy_url >=", value, "buyUrl");
			return (Criteria) this;
		}

		public Criteria andBuyUrlLessThan(String value) {
			addCriterion("buy_url <", value, "buyUrl");
			return (Criteria) this;
		}

		public Criteria andBuyUrlLessThanOrEqualTo(String value) {
			addCriterion("buy_url <=", value, "buyUrl");
			return (Criteria) this;
		}

		public Criteria andBuyUrlLike(String value) {
			addCriterion("buy_url like", value, "buyUrl");
			return (Criteria) this;
		}

		public Criteria andBuyUrlNotLike(String value) {
			addCriterion("buy_url not like", value, "buyUrl");
			return (Criteria) this;
		}

		public Criteria andBuyUrlIn(List<String> values) {
			addCriterion("buy_url in", values, "buyUrl");
			return (Criteria) this;
		}

		public Criteria andBuyUrlNotIn(List<String> values) {
			addCriterion("buy_url not in", values, "buyUrl");
			return (Criteria) this;
		}

		public Criteria andBuyUrlBetween(String value1, String value2) {
			addCriterion("buy_url between", value1, value2, "buyUrl");
			return (Criteria) this;
		}

		public Criteria andBuyUrlNotBetween(String value1, String value2) {
			addCriterion("buy_url not between", value1, value2, "buyUrl");
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
	 * This class was generated by MyBatis Generator. This class corresponds to the database table ito_sku
	 * @mbggenerated  Wed May 07 23:29:06 CST 2014
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
     * This class corresponds to the database table ito_sku
     *
     * @mbggenerated do_not_delete_during_merge Fri May 02 22:42:49 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}