package com.bruce.geekway.model;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable{

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_product.id
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_product.sku_category_id
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	private Integer skuCategoryId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_product.category_id
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	private Integer categoryId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_product.out_id
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	private String outId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_product.title
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	private String title;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_product.name
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_product.description
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_product.param
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	private String param;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_product.deliver_info
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	private String deliverInfo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_product.stock
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	private Integer stock;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_product.origin_price
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	private Integer originPrice;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_product.price
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	private Integer price;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_product.cover_pic_url
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	private String coverPicUrl;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_product.wx_share_title
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	private String wxShareTitle;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_product.wx_share_content
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	private String wxShareContent;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_product.wx_share_icon_url
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	private String wxShareIconUrl;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_product.status
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	private Short status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_product.create_time
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_product.update_time
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	private Date updateTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_product.id
	 * @return  the value of tb_product.id
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_product.id
	 * @param id  the value for tb_product.id
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_product.sku_category_id
	 * @return  the value of tb_product.sku_category_id
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public Integer getSkuCategoryId() {
		return skuCategoryId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_product.sku_category_id
	 * @param skuCategoryId  the value for tb_product.sku_category_id
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public void setSkuCategoryId(Integer skuCategoryId) {
		this.skuCategoryId = skuCategoryId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_product.category_id
	 * @return  the value of tb_product.category_id
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_product.category_id
	 * @param categoryId  the value for tb_product.category_id
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_product.out_id
	 * @return  the value of tb_product.out_id
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public String getOutId() {
		return outId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_product.out_id
	 * @param outId  the value for tb_product.out_id
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public void setOutId(String outId) {
		this.outId = outId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_product.title
	 * @return  the value of tb_product.title
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_product.title
	 * @param title  the value for tb_product.title
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_product.name
	 * @return  the value of tb_product.name
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_product.name
	 * @param name  the value for tb_product.name
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_product.description
	 * @return  the value of tb_product.description
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_product.description
	 * @param description  the value for tb_product.description
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_product.param
	 * @return  the value of tb_product.param
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public String getParam() {
		return param;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_product.param
	 * @param param  the value for tb_product.param
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public void setParam(String param) {
		this.param = param;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_product.deliver_info
	 * @return  the value of tb_product.deliver_info
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public String getDeliverInfo() {
		return deliverInfo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_product.deliver_info
	 * @param deliverInfo  the value for tb_product.deliver_info
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public void setDeliverInfo(String deliverInfo) {
		this.deliverInfo = deliverInfo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_product.stock
	 * @return  the value of tb_product.stock
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public Integer getStock() {
		return stock;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_product.stock
	 * @param stock  the value for tb_product.stock
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_product.origin_price
	 * @return  the value of tb_product.origin_price
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public Integer getOriginPrice() {
		return originPrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_product.origin_price
	 * @param originPrice  the value for tb_product.origin_price
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public void setOriginPrice(Integer originPrice) {
		this.originPrice = originPrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_product.price
	 * @return  the value of tb_product.price
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_product.price
	 * @param price  the value for tb_product.price
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_product.cover_pic_url
	 * @return  the value of tb_product.cover_pic_url
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public String getCoverPicUrl() {
		return coverPicUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_product.cover_pic_url
	 * @param coverPicUrl  the value for tb_product.cover_pic_url
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public void setCoverPicUrl(String coverPicUrl) {
		this.coverPicUrl = coverPicUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_product.wx_share_title
	 * @return  the value of tb_product.wx_share_title
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public String getWxShareTitle() {
		return wxShareTitle;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_product.wx_share_title
	 * @param wxShareTitle  the value for tb_product.wx_share_title
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public void setWxShareTitle(String wxShareTitle) {
		this.wxShareTitle = wxShareTitle;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_product.wx_share_content
	 * @return  the value of tb_product.wx_share_content
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public String getWxShareContent() {
		return wxShareContent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_product.wx_share_content
	 * @param wxShareContent  the value for tb_product.wx_share_content
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public void setWxShareContent(String wxShareContent) {
		this.wxShareContent = wxShareContent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_product.wx_share_icon_url
	 * @return  the value of tb_product.wx_share_icon_url
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public String getWxShareIconUrl() {
		return wxShareIconUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_product.wx_share_icon_url
	 * @param wxShareIconUrl  the value for tb_product.wx_share_icon_url
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public void setWxShareIconUrl(String wxShareIconUrl) {
		this.wxShareIconUrl = wxShareIconUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_product.status
	 * @return  the value of tb_product.status
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public Short getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_product.status
	 * @param status  the value for tb_product.status
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_product.create_time
	 * @return  the value of tb_product.create_time
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_product.create_time
	 * @param createTime  the value for tb_product.create_time
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_product.update_time
	 * @return  the value of tb_product.update_time
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_product.update_time
	 * @param updateTime  the value for tb_product.update_time
	 * @mbggenerated  Wed Feb 04 17:26:24 CST 2015
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -37474572866897231L;
}