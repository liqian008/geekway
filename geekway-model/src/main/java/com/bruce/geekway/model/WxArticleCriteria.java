package com.bruce.geekway.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WxArticleCriteria {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	public WxArticleCriteria() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
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

		public Criteria andShortTitleIsNull() {
			addCriterion("short_title is null");
			return (Criteria) this;
		}

		public Criteria andShortTitleIsNotNull() {
			addCriterion("short_title is not null");
			return (Criteria) this;
		}

		public Criteria andShortTitleEqualTo(String value) {
			addCriterion("short_title =", value, "shortTitle");
			return (Criteria) this;
		}

		public Criteria andShortTitleNotEqualTo(String value) {
			addCriterion("short_title <>", value, "shortTitle");
			return (Criteria) this;
		}

		public Criteria andShortTitleGreaterThan(String value) {
			addCriterion("short_title >", value, "shortTitle");
			return (Criteria) this;
		}

		public Criteria andShortTitleGreaterThanOrEqualTo(String value) {
			addCriterion("short_title >=", value, "shortTitle");
			return (Criteria) this;
		}

		public Criteria andShortTitleLessThan(String value) {
			addCriterion("short_title <", value, "shortTitle");
			return (Criteria) this;
		}

		public Criteria andShortTitleLessThanOrEqualTo(String value) {
			addCriterion("short_title <=", value, "shortTitle");
			return (Criteria) this;
		}

		public Criteria andShortTitleLike(String value) {
			addCriterion("short_title like", value, "shortTitle");
			return (Criteria) this;
		}

		public Criteria andShortTitleNotLike(String value) {
			addCriterion("short_title not like", value, "shortTitle");
			return (Criteria) this;
		}

		public Criteria andShortTitleIn(List<String> values) {
			addCriterion("short_title in", values, "shortTitle");
			return (Criteria) this;
		}

		public Criteria andShortTitleNotIn(List<String> values) {
			addCriterion("short_title not in", values, "shortTitle");
			return (Criteria) this;
		}

		public Criteria andShortTitleBetween(String value1, String value2) {
			addCriterion("short_title between", value1, value2, "shortTitle");
			return (Criteria) this;
		}

		public Criteria andShortTitleNotBetween(String value1, String value2) {
			addCriterion("short_title not between", value1, value2,
					"shortTitle");
			return (Criteria) this;
		}

		public Criteria andShortContentIsNull() {
			addCriterion("short_content is null");
			return (Criteria) this;
		}

		public Criteria andShortContentIsNotNull() {
			addCriterion("short_content is not null");
			return (Criteria) this;
		}

		public Criteria andShortContentEqualTo(String value) {
			addCriterion("short_content =", value, "shortContent");
			return (Criteria) this;
		}

		public Criteria andShortContentNotEqualTo(String value) {
			addCriterion("short_content <>", value, "shortContent");
			return (Criteria) this;
		}

		public Criteria andShortContentGreaterThan(String value) {
			addCriterion("short_content >", value, "shortContent");
			return (Criteria) this;
		}

		public Criteria andShortContentGreaterThanOrEqualTo(String value) {
			addCriterion("short_content >=", value, "shortContent");
			return (Criteria) this;
		}

		public Criteria andShortContentLessThan(String value) {
			addCriterion("short_content <", value, "shortContent");
			return (Criteria) this;
		}

		public Criteria andShortContentLessThanOrEqualTo(String value) {
			addCriterion("short_content <=", value, "shortContent");
			return (Criteria) this;
		}

		public Criteria andShortContentLike(String value) {
			addCriterion("short_content like", value, "shortContent");
			return (Criteria) this;
		}

		public Criteria andShortContentNotLike(String value) {
			addCriterion("short_content not like", value, "shortContent");
			return (Criteria) this;
		}

		public Criteria andShortContentIn(List<String> values) {
			addCriterion("short_content in", values, "shortContent");
			return (Criteria) this;
		}

		public Criteria andShortContentNotIn(List<String> values) {
			addCriterion("short_content not in", values, "shortContent");
			return (Criteria) this;
		}

		public Criteria andShortContentBetween(String value1, String value2) {
			addCriterion("short_content between", value1, value2,
					"shortContent");
			return (Criteria) this;
		}

		public Criteria andShortContentNotBetween(String value1, String value2) {
			addCriterion("short_content not between", value1, value2,
					"shortContent");
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

		public Criteria andLinkIsNull() {
			addCriterion("link is null");
			return (Criteria) this;
		}

		public Criteria andLinkIsNotNull() {
			addCriterion("link is not null");
			return (Criteria) this;
		}

		public Criteria andLinkEqualTo(String value) {
			addCriterion("link =", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkNotEqualTo(String value) {
			addCriterion("link <>", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkGreaterThan(String value) {
			addCriterion("link >", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkGreaterThanOrEqualTo(String value) {
			addCriterion("link >=", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkLessThan(String value) {
			addCriterion("link <", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkLessThanOrEqualTo(String value) {
			addCriterion("link <=", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkLike(String value) {
			addCriterion("link like", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkNotLike(String value) {
			addCriterion("link not like", value, "link");
			return (Criteria) this;
		}

		public Criteria andLinkIn(List<String> values) {
			addCriterion("link in", values, "link");
			return (Criteria) this;
		}

		public Criteria andLinkNotIn(List<String> values) {
			addCriterion("link not in", values, "link");
			return (Criteria) this;
		}

		public Criteria andLinkBetween(String value1, String value2) {
			addCriterion("link between", value1, value2, "link");
			return (Criteria) this;
		}

		public Criteria andLinkNotBetween(String value1, String value2) {
			addCriterion("link not between", value1, value2, "link");
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

		public Criteria andCoverImageUrlIsNull() {
			addCriterion("cover_image_url is null");
			return (Criteria) this;
		}

		public Criteria andCoverImageUrlIsNotNull() {
			addCriterion("cover_image_url is not null");
			return (Criteria) this;
		}

		public Criteria andCoverImageUrlEqualTo(String value) {
			addCriterion("cover_image_url =", value, "coverImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverImageUrlNotEqualTo(String value) {
			addCriterion("cover_image_url <>", value, "coverImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverImageUrlGreaterThan(String value) {
			addCriterion("cover_image_url >", value, "coverImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverImageUrlGreaterThanOrEqualTo(String value) {
			addCriterion("cover_image_url >=", value, "coverImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverImageUrlLessThan(String value) {
			addCriterion("cover_image_url <", value, "coverImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverImageUrlLessThanOrEqualTo(String value) {
			addCriterion("cover_image_url <=", value, "coverImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverImageUrlLike(String value) {
			addCriterion("cover_image_url like", value, "coverImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverImageUrlNotLike(String value) {
			addCriterion("cover_image_url not like", value, "coverImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverImageUrlIn(List<String> values) {
			addCriterion("cover_image_url in", values, "coverImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverImageUrlNotIn(List<String> values) {
			addCriterion("cover_image_url not in", values, "coverImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverImageUrlBetween(String value1, String value2) {
			addCriterion("cover_image_url between", value1, value2,
					"coverImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverImageUrlNotBetween(String value1, String value2) {
			addCriterion("cover_image_url not between", value1, value2,
					"coverImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverThumbImageUrlIsNull() {
			addCriterion("cover_thumb_image_url is null");
			return (Criteria) this;
		}

		public Criteria andCoverThumbImageUrlIsNotNull() {
			addCriterion("cover_thumb_image_url is not null");
			return (Criteria) this;
		}

		public Criteria andCoverThumbImageUrlEqualTo(String value) {
			addCriterion("cover_thumb_image_url =", value, "coverThumbImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverThumbImageUrlNotEqualTo(String value) {
			addCriterion("cover_thumb_image_url <>", value,
					"coverThumbImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverThumbImageUrlGreaterThan(String value) {
			addCriterion("cover_thumb_image_url >", value, "coverThumbImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverThumbImageUrlGreaterThanOrEqualTo(String value) {
			addCriterion("cover_thumb_image_url >=", value,
					"coverThumbImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverThumbImageUrlLessThan(String value) {
			addCriterion("cover_thumb_image_url <", value, "coverThumbImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverThumbImageUrlLessThanOrEqualTo(String value) {
			addCriterion("cover_thumb_image_url <=", value,
					"coverThumbImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverThumbImageUrlLike(String value) {
			addCriterion("cover_thumb_image_url like", value,
					"coverThumbImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverThumbImageUrlNotLike(String value) {
			addCriterion("cover_thumb_image_url not like", value,
					"coverThumbImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverThumbImageUrlIn(List<String> values) {
			addCriterion("cover_thumb_image_url in", values,
					"coverThumbImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverThumbImageUrlNotIn(List<String> values) {
			addCriterion("cover_thumb_image_url not in", values,
					"coverThumbImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverThumbImageUrlBetween(String value1,
				String value2) {
			addCriterion("cover_thumb_image_url between", value1, value2,
					"coverThumbImageUrl");
			return (Criteria) this;
		}

		public Criteria andCoverThumbImageUrlNotBetween(String value1,
				String value2) {
			addCriterion("cover_thumb_image_url not between", value1, value2,
					"coverThumbImageUrl");
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
	 * This class was generated by MyBatis Generator. This class corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
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
     * This class corresponds to the database table wx_article
     *
     * @mbggenerated do_not_delete_during_merge Tue Apr 01 22:00:17 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}