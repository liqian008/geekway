package com.bruce.geekway.model;

import java.io.Serializable;
import java.util.Date;

public class SlideImage implements Serializable{

	
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_slide_image.id
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_slide_image.image_type
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	private Short imageType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_slide_image.root_id
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	private Integer rootId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_slide_image.product_id
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	private Integer productId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_slide_image.name
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_slide_image.link
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	private String link;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_slide_image.pic_url
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	private String picUrl;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_slide_image.sort
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	private Integer sort;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_slide_image.status
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	private Short status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_slide_image.create_time
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_slide_image.update_time
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	private Date updateTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_slide_image.id
	 * @return  the value of tb_slide_image.id
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_slide_image.id
	 * @param id  the value for tb_slide_image.id
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_slide_image.image_type
	 * @return  the value of tb_slide_image.image_type
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	public Short getImageType() {
		return imageType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_slide_image.image_type
	 * @param imageType  the value for tb_slide_image.image_type
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	public void setImageType(Short imageType) {
		this.imageType = imageType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_slide_image.root_id
	 * @return  the value of tb_slide_image.root_id
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	public Integer getRootId() {
		return rootId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_slide_image.root_id
	 * @param rootId  the value for tb_slide_image.root_id
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	public void setRootId(Integer rootId) {
		this.rootId = rootId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_slide_image.product_id
	 * @return  the value of tb_slide_image.product_id
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_slide_image.product_id
	 * @param productId  the value for tb_slide_image.product_id
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_slide_image.name
	 * @return  the value of tb_slide_image.name
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_slide_image.name
	 * @param name  the value for tb_slide_image.name
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_slide_image.link
	 * @return  the value of tb_slide_image.link
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	public String getLink() {
		return link;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_slide_image.link
	 * @param link  the value for tb_slide_image.link
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_slide_image.pic_url
	 * @return  the value of tb_slide_image.pic_url
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_slide_image.pic_url
	 * @param picUrl  the value for tb_slide_image.pic_url
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_slide_image.sort
	 * @return  the value of tb_slide_image.sort
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_slide_image.sort
	 * @param sort  the value for tb_slide_image.sort
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_slide_image.status
	 * @return  the value of tb_slide_image.status
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	public Short getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_slide_image.status
	 * @param status  the value for tb_slide_image.status
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_slide_image.create_time
	 * @return  the value of tb_slide_image.create_time
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_slide_image.create_time
	 * @param createTime  the value for tb_slide_image.create_time
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_slide_image.update_time
	 * @return  the value of tb_slide_image.update_time
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_slide_image.update_time
	 * @param updateTime  the value for tb_slide_image.update_time
	 * @mbggenerated  Wed Jan 14 11:41:25 CST 2015
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 202206786234158969L;
}