package com.bruce.geekway.model;

import java.util.Date;

public class ItoProductOrder {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.id
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.order_sn
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private String orderSn;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.qrcode_url
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private String qrcodeUrl;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.category_id
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private Integer categoryId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.out_id
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private String outId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.product_id
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private Integer productId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.title
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private String title;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.description
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private String description;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.num
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private Integer num;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.price
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private Double price;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.post_fee
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private Double postFee;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.total_price
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private Double totalPrice;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.post_sn
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private String postSn;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.sku_id
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private Integer skuId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.sku_name
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private String skuName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.sku_properties_name
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private String skuPropertiesName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.pay_type
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private Short payType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.pay_status
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private Short payStatus;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.post_name
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private String postName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.post_code
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private String postCode;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.post_address
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private String postAddress;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.post_mobile
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private String postMobile;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.post_email
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private String postEmail;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.post_status
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private Short postStatus;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.status
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private Short status;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.create_time
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column ito_product_order.update_time
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	private Date updateTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.id
	 * @return  the value of ito_product_order.id
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.id
	 * @param id  the value for ito_product_order.id
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.order_sn
	 * @return  the value of ito_product_order.order_sn
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public String getOrderSn() {
		return orderSn;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.order_sn
	 * @param orderSn  the value for ito_product_order.order_sn
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.qrcode_url
	 * @return  the value of ito_product_order.qrcode_url
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public String getQrcodeUrl() {
		return qrcodeUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.qrcode_url
	 * @param qrcodeUrl  the value for ito_product_order.qrcode_url
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.category_id
	 * @return  the value of ito_product_order.category_id
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.category_id
	 * @param categoryId  the value for ito_product_order.category_id
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.out_id
	 * @return  the value of ito_product_order.out_id
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public String getOutId() {
		return outId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.out_id
	 * @param outId  the value for ito_product_order.out_id
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setOutId(String outId) {
		this.outId = outId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.product_id
	 * @return  the value of ito_product_order.product_id
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.product_id
	 * @param productId  the value for ito_product_order.product_id
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.title
	 * @return  the value of ito_product_order.title
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.title
	 * @param title  the value for ito_product_order.title
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.description
	 * @return  the value of ito_product_order.description
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.description
	 * @param description  the value for ito_product_order.description
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.num
	 * @return  the value of ito_product_order.num
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.num
	 * @param num  the value for ito_product_order.num
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setNum(Integer num) {
		this.num = num;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.price
	 * @return  the value of ito_product_order.price
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.price
	 * @param price  the value for ito_product_order.price
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.post_fee
	 * @return  the value of ito_product_order.post_fee
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public Double getPostFee() {
		return postFee;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.post_fee
	 * @param postFee  the value for ito_product_order.post_fee
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setPostFee(Double postFee) {
		this.postFee = postFee;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.total_price
	 * @return  the value of ito_product_order.total_price
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public Double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.total_price
	 * @param totalPrice  the value for ito_product_order.total_price
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.post_sn
	 * @return  the value of ito_product_order.post_sn
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public String getPostSn() {
		return postSn;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.post_sn
	 * @param postSn  the value for ito_product_order.post_sn
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setPostSn(String postSn) {
		this.postSn = postSn;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.sku_id
	 * @return  the value of ito_product_order.sku_id
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public Integer getSkuId() {
		return skuId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.sku_id
	 * @param skuId  the value for ito_product_order.sku_id
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.sku_name
	 * @return  the value of ito_product_order.sku_name
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public String getSkuName() {
		return skuName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.sku_name
	 * @param skuName  the value for ito_product_order.sku_name
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.sku_properties_name
	 * @return  the value of ito_product_order.sku_properties_name
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public String getSkuPropertiesName() {
		return skuPropertiesName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.sku_properties_name
	 * @param skuPropertiesName  the value for ito_product_order.sku_properties_name
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setSkuPropertiesName(String skuPropertiesName) {
		this.skuPropertiesName = skuPropertiesName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.pay_type
	 * @return  the value of ito_product_order.pay_type
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public Short getPayType() {
		return payType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.pay_type
	 * @param payType  the value for ito_product_order.pay_type
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setPayType(Short payType) {
		this.payType = payType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.pay_status
	 * @return  the value of ito_product_order.pay_status
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public Short getPayStatus() {
		return payStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.pay_status
	 * @param payStatus  the value for ito_product_order.pay_status
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setPayStatus(Short payStatus) {
		this.payStatus = payStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.post_name
	 * @return  the value of ito_product_order.post_name
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public String getPostName() {
		return postName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.post_name
	 * @param postName  the value for ito_product_order.post_name
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setPostName(String postName) {
		this.postName = postName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.post_code
	 * @return  the value of ito_product_order.post_code
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.post_code
	 * @param postCode  the value for ito_product_order.post_code
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.post_address
	 * @return  the value of ito_product_order.post_address
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public String getPostAddress() {
		return postAddress;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.post_address
	 * @param postAddress  the value for ito_product_order.post_address
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setPostAddress(String postAddress) {
		this.postAddress = postAddress;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.post_mobile
	 * @return  the value of ito_product_order.post_mobile
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public String getPostMobile() {
		return postMobile;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.post_mobile
	 * @param postMobile  the value for ito_product_order.post_mobile
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setPostMobile(String postMobile) {
		this.postMobile = postMobile;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.post_email
	 * @return  the value of ito_product_order.post_email
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public String getPostEmail() {
		return postEmail;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.post_email
	 * @param postEmail  the value for ito_product_order.post_email
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setPostEmail(String postEmail) {
		this.postEmail = postEmail;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.post_status
	 * @return  the value of ito_product_order.post_status
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public Short getPostStatus() {
		return postStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.post_status
	 * @param postStatus  the value for ito_product_order.post_status
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setPostStatus(Short postStatus) {
		this.postStatus = postStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.status
	 * @return  the value of ito_product_order.status
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public Short getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.status
	 * @param status  the value for ito_product_order.status
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.create_time
	 * @return  the value of ito_product_order.create_time
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.create_time
	 * @param createTime  the value for ito_product_order.create_time
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column ito_product_order.update_time
	 * @return  the value of ito_product_order.update_time
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column ito_product_order.update_time
	 * @param updateTime  the value for ito_product_order.update_time
	 * @mbggenerated  Sun Jun 08 18:28:21 CST 2014
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}