package com.bruce.geekway.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItoProductCriteria {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table ito_product
	 * @mbggenerated  Sun May 04 15:37:57 CST 2014
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table ito_product
	 * @mbggenerated  Sun May 04 15:37:57 CST 2014
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table ito_product
	 * @mbggenerated  Sun May 04 15:37:57 CST 2014
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product
	 * @mbggenerated  Sun May 04 15:37:57 CST 2014
	 */
	public ItoProductCriteria() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product
	 * @mbggenerated  Sun May 04 15:37:57 CST 2014
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product
	 * @mbggenerated  Sun May 04 15:37:57 CST 2014
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product
	 * @mbggenerated  Sun May 04 15:37:57 CST 2014
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product
	 * @mbggenerated  Sun May 04 15:37:57 CST 2014
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product
	 * @mbggenerated  Sun May 04 15:37:57 CST 2014
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product
	 * @mbggenerated  Sun May 04 15:37:57 CST 2014
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product
	 * @mbggenerated  Sun May 04 15:37:57 CST 2014
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product
	 * @mbggenerated  Sun May 04 15:37:57 CST 2014
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product
	 * @mbggenerated  Sun May 04 15:37:57 CST 2014
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product
	 * @mbggenerated  Sun May 04 15:37:57 CST 2014
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table ito_product
	 * @mbggenerated  Sun May 04 15:37:57 CST 2014
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

		public Criteria andCategoryIdIsNull() {
			addCriterion("category_id is null");
			return (Criteria) this;
		}

		public Criteria andCategoryIdIsNotNull() {
			addCriterion("category_id is not null");
			return (Criteria) this;
		}

		public Criteria andCategoryIdEqualTo(Integer value) {
			addCriterion("category_id =", value, "categoryId");
			return (Criteria) this;
		}

		public Criteria andCategoryIdNotEqualTo(Integer value) {
			addCriterion("category_id <>", value, "categoryId");
			return (Criteria) this;
		}

		public Criteria andCategoryIdGreaterThan(Integer value) {
			addCriterion("category_id >", value, "categoryId");
			return (Criteria) this;
		}

		public Criteria andCategoryIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("category_id >=", value, "categoryId");
			return (Criteria) this;
		}

		public Criteria andCategoryIdLessThan(Integer value) {
			addCriterion("category_id <", value, "categoryId");
			return (Criteria) this;
		}

		public Criteria andCategoryIdLessThanOrEqualTo(Integer value) {
			addCriterion("category_id <=", value, "categoryId");
			return (Criteria) this;
		}

		public Criteria andCategoryIdIn(List<Integer> values) {
			addCriterion("category_id in", values, "categoryId");
			return (Criteria) this;
		}

		public Criteria andCategoryIdNotIn(List<Integer> values) {
			addCriterion("category_id not in", values, "categoryId");
			return (Criteria) this;
		}

		public Criteria andCategoryIdBetween(Integer value1, Integer value2) {
			addCriterion("category_id between", value1, value2, "categoryId");
			return (Criteria) this;
		}

		public Criteria andCategoryIdNotBetween(Integer value1, Integer value2) {
			addCriterion("category_id not between", value1, value2, "categoryId");
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
			addCriterion("description not between", value1, value2, "description");
			return (Criteria) this;
		}

		public Criteria andNumIsNull() {
			addCriterion("num is null");
			return (Criteria) this;
		}

		public Criteria andNumIsNotNull() {
			addCriterion("num is not null");
			return (Criteria) this;
		}

		public Criteria andNumEqualTo(Integer value) {
			addCriterion("num =", value, "num");
			return (Criteria) this;
		}

		public Criteria andNumNotEqualTo(Integer value) {
			addCriterion("num <>", value, "num");
			return (Criteria) this;
		}

		public Criteria andNumGreaterThan(Integer value) {
			addCriterion("num >", value, "num");
			return (Criteria) this;
		}

		public Criteria andNumGreaterThanOrEqualTo(Integer value) {
			addCriterion("num >=", value, "num");
			return (Criteria) this;
		}

		public Criteria andNumLessThan(Integer value) {
			addCriterion("num <", value, "num");
			return (Criteria) this;
		}

		public Criteria andNumLessThanOrEqualTo(Integer value) {
			addCriterion("num <=", value, "num");
			return (Criteria) this;
		}

		public Criteria andNumIn(List<Integer> values) {
			addCriterion("num in", values, "num");
			return (Criteria) this;
		}

		public Criteria andNumNotIn(List<Integer> values) {
			addCriterion("num not in", values, "num");
			return (Criteria) this;
		}

		public Criteria andNumBetween(Integer value1, Integer value2) {
			addCriterion("num between", value1, value2, "num");
			return (Criteria) this;
		}

		public Criteria andNumNotBetween(Integer value1, Integer value2) {
			addCriterion("num not between", value1, value2, "num");
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

		public Criteria andPostFeeIsNull() {
			addCriterion("post_fee is null");
			return (Criteria) this;
		}

		public Criteria andPostFeeIsNotNull() {
			addCriterion("post_fee is not null");
			return (Criteria) this;
		}

		public Criteria andPostFeeEqualTo(Double value) {
			addCriterion("post_fee =", value, "postFee");
			return (Criteria) this;
		}

		public Criteria andPostFeeNotEqualTo(Double value) {
			addCriterion("post_fee <>", value, "postFee");
			return (Criteria) this;
		}

		public Criteria andPostFeeGreaterThan(Double value) {
			addCriterion("post_fee >", value, "postFee");
			return (Criteria) this;
		}

		public Criteria andPostFeeGreaterThanOrEqualTo(Double value) {
			addCriterion("post_fee >=", value, "postFee");
			return (Criteria) this;
		}

		public Criteria andPostFeeLessThan(Double value) {
			addCriterion("post_fee <", value, "postFee");
			return (Criteria) this;
		}

		public Criteria andPostFeeLessThanOrEqualTo(Double value) {
			addCriterion("post_fee <=", value, "postFee");
			return (Criteria) this;
		}

		public Criteria andPostFeeIn(List<Double> values) {
			addCriterion("post_fee in", values, "postFee");
			return (Criteria) this;
		}

		public Criteria andPostFeeNotIn(List<Double> values) {
			addCriterion("post_fee not in", values, "postFee");
			return (Criteria) this;
		}

		public Criteria andPostFeeBetween(Double value1, Double value2) {
			addCriterion("post_fee between", value1, value2, "postFee");
			return (Criteria) this;
		}

		public Criteria andPostFeeNotBetween(Double value1, Double value2) {
			addCriterion("post_fee not between", value1, value2, "postFee");
			return (Criteria) this;
		}

		public Criteria andProductThumbPicUrlIsNull() {
			addCriterion("product_thumb_pic_url is null");
			return (Criteria) this;
		}

		public Criteria andProductThumbPicUrlIsNotNull() {
			addCriterion("product_thumb_pic_url is not null");
			return (Criteria) this;
		}

		public Criteria andProductThumbPicUrlEqualTo(String value) {
			addCriterion("product_thumb_pic_url =", value, "productThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductThumbPicUrlNotEqualTo(String value) {
			addCriterion("product_thumb_pic_url <>", value, "productThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductThumbPicUrlGreaterThan(String value) {
			addCriterion("product_thumb_pic_url >", value, "productThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductThumbPicUrlGreaterThanOrEqualTo(String value) {
			addCriterion("product_thumb_pic_url >=", value, "productThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductThumbPicUrlLessThan(String value) {
			addCriterion("product_thumb_pic_url <", value, "productThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductThumbPicUrlLessThanOrEqualTo(String value) {
			addCriterion("product_thumb_pic_url <=", value, "productThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductThumbPicUrlLike(String value) {
			addCriterion("product_thumb_pic_url like", value, "productThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductThumbPicUrlNotLike(String value) {
			addCriterion("product_thumb_pic_url not like", value, "productThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductThumbPicUrlIn(List<String> values) {
			addCriterion("product_thumb_pic_url in", values, "productThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductThumbPicUrlNotIn(List<String> values) {
			addCriterion("product_thumb_pic_url not in", values, "productThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductThumbPicUrlBetween(String value1, String value2) {
			addCriterion("product_thumb_pic_url between", value1, value2, "productThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductThumbPicUrlNotBetween(String value1, String value2) {
			addCriterion("product_thumb_pic_url not between", value1, value2, "productThumbPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductPicUrlIsNull() {
			addCriterion("product_pic_url is null");
			return (Criteria) this;
		}

		public Criteria andProductPicUrlIsNotNull() {
			addCriterion("product_pic_url is not null");
			return (Criteria) this;
		}

		public Criteria andProductPicUrlEqualTo(String value) {
			addCriterion("product_pic_url =", value, "productPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductPicUrlNotEqualTo(String value) {
			addCriterion("product_pic_url <>", value, "productPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductPicUrlGreaterThan(String value) {
			addCriterion("product_pic_url >", value, "productPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductPicUrlGreaterThanOrEqualTo(String value) {
			addCriterion("product_pic_url >=", value, "productPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductPicUrlLessThan(String value) {
			addCriterion("product_pic_url <", value, "productPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductPicUrlLessThanOrEqualTo(String value) {
			addCriterion("product_pic_url <=", value, "productPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductPicUrlLike(String value) {
			addCriterion("product_pic_url like", value, "productPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductPicUrlNotLike(String value) {
			addCriterion("product_pic_url not like", value, "productPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductPicUrlIn(List<String> values) {
			addCriterion("product_pic_url in", values, "productPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductPicUrlNotIn(List<String> values) {
			addCriterion("product_pic_url not in", values, "productPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductPicUrlBetween(String value1, String value2) {
			addCriterion("product_pic_url between", value1, value2, "productPicUrl");
			return (Criteria) this;
		}

		public Criteria andProductPicUrlNotBetween(String value1, String value2) {
			addCriterion("product_pic_url not between", value1, value2, "productPicUrl");
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
	 * This class was generated by MyBatis Generator. This class corresponds to the database table ito_product
	 * @mbggenerated  Sun May 04 15:37:57 CST 2014
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
     * This class corresponds to the database table ito_product
     *
     * @mbggenerated do_not_delete_during_merge Fri May 02 22:42:49 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}