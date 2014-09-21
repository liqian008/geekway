package com.bruce.geekway.model;

import java.util.Date;

public class WxProduct {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wx_product.id
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wx_product.sku_category_id
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	private Integer skuCategoryId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wx_product.category_id
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	private Integer categoryId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wx_product.out_id
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	private String outId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wx_product.name
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wx_product.description
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wx_product.amount
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	private Integer amount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wx_product.origin_price
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	private Double originPrice;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wx_product.price
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	private Double price;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wx_product.product_thumb_pic_url
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	private String productThumbPicUrl;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wx_product.product_pic_url
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	private String productPicUrl;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wx_product.status
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	private Short status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wx_product.create_time
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wx_product.update_time
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	private Date updateTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wx_product.id
	 * @return  the value of wx_product.id
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wx_product.id
	 * @param id  the value for wx_product.id
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wx_product.sku_category_id
	 * @return  the value of wx_product.sku_category_id
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public Integer getSkuCategoryId() {
		return skuCategoryId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wx_product.sku_category_id
	 * @param skuCategoryId  the value for wx_product.sku_category_id
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public void setSkuCategoryId(Integer skuCategoryId) {
		this.skuCategoryId = skuCategoryId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wx_product.category_id
	 * @return  the value of wx_product.category_id
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wx_product.category_id
	 * @param categoryId  the value for wx_product.category_id
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wx_product.out_id
	 * @return  the value of wx_product.out_id
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public String getOutId() {
		return outId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wx_product.out_id
	 * @param outId  the value for wx_product.out_id
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public void setOutId(String outId) {
		this.outId = outId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wx_product.name
	 * @return  the value of wx_product.name
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wx_product.name
	 * @param name  the value for wx_product.name
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wx_product.description
	 * @return  the value of wx_product.description
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wx_product.description
	 * @param description  the value for wx_product.description
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wx_product.amount
	 * @return  the value of wx_product.amount
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wx_product.amount
	 * @param amount  the value for wx_product.amount
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wx_product.origin_price
	 * @return  the value of wx_product.origin_price
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public Double getOriginPrice() {
		return originPrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wx_product.origin_price
	 * @param originPrice  the value for wx_product.origin_price
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public void setOriginPrice(Double originPrice) {
		this.originPrice = originPrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wx_product.price
	 * @return  the value of wx_product.price
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wx_product.price
	 * @param price  the value for wx_product.price
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wx_product.product_thumb_pic_url
	 * @return  the value of wx_product.product_thumb_pic_url
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public String getProductThumbPicUrl() {
		return productThumbPicUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wx_product.product_thumb_pic_url
	 * @param productThumbPicUrl  the value for wx_product.product_thumb_pic_url
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public void setProductThumbPicUrl(String productThumbPicUrl) {
		this.productThumbPicUrl = productThumbPicUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wx_product.product_pic_url
	 * @return  the value of wx_product.product_pic_url
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public String getProductPicUrl() {
		return productPicUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wx_product.product_pic_url
	 * @param productPicUrl  the value for wx_product.product_pic_url
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public void setProductPicUrl(String productPicUrl) {
		this.productPicUrl = productPicUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wx_product.status
	 * @return  the value of wx_product.status
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public Short getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wx_product.status
	 * @param status  the value for wx_product.status
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wx_product.create_time
	 * @return  the value of wx_product.create_time
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wx_product.create_time
	 * @param createTime  the value for wx_product.create_time
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wx_product.update_time
	 * @return  the value of wx_product.update_time
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wx_product.update_time
	 * @param updateTime  the value for wx_product.update_time
	 * @mbggenerated  Sat Sep 20 23:06:48 CST 2014
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}