package com.bruce.geekway.model;

import java.util.Date;

public class WxProductSkuRelation {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wx_product_sku_relation.id
	 * @mbggenerated  Tue Sep 16 22:14:29 CST 2014
	 */
	private Long id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wx_product_sku_relation.product_id
	 * @mbggenerated  Tue Sep 16 22:14:29 CST 2014
	 */
	private Integer productId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wx_product_sku_relation.sku_prop_value_id
	 * @mbggenerated  Tue Sep 16 22:14:29 CST 2014
	 */
	private Integer skuPropValueId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wx_product_sku_relation.create_time
	 * @mbggenerated  Tue Sep 16 22:14:29 CST 2014
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wx_product_sku_relation.update_time
	 * @mbggenerated  Tue Sep 16 22:14:29 CST 2014
	 */
	private Date updateTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wx_product_sku_relation.id
	 * @return  the value of wx_product_sku_relation.id
	 * @mbggenerated  Tue Sep 16 22:14:29 CST 2014
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wx_product_sku_relation.id
	 * @param id  the value for wx_product_sku_relation.id
	 * @mbggenerated  Tue Sep 16 22:14:29 CST 2014
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wx_product_sku_relation.product_id
	 * @return  the value of wx_product_sku_relation.product_id
	 * @mbggenerated  Tue Sep 16 22:14:29 CST 2014
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wx_product_sku_relation.product_id
	 * @param productId  the value for wx_product_sku_relation.product_id
	 * @mbggenerated  Tue Sep 16 22:14:29 CST 2014
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wx_product_sku_relation.sku_prop_value_id
	 * @return  the value of wx_product_sku_relation.sku_prop_value_id
	 * @mbggenerated  Tue Sep 16 22:14:29 CST 2014
	 */
	public Integer getSkuPropValueId() {
		return skuPropValueId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wx_product_sku_relation.sku_prop_value_id
	 * @param skuPropValueId  the value for wx_product_sku_relation.sku_prop_value_id
	 * @mbggenerated  Tue Sep 16 22:14:29 CST 2014
	 */
	public void setSkuPropValueId(Integer skuPropValueId) {
		this.skuPropValueId = skuPropValueId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wx_product_sku_relation.create_time
	 * @return  the value of wx_product_sku_relation.create_time
	 * @mbggenerated  Tue Sep 16 22:14:29 CST 2014
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wx_product_sku_relation.create_time
	 * @param createTime  the value for wx_product_sku_relation.create_time
	 * @mbggenerated  Tue Sep 16 22:14:29 CST 2014
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wx_product_sku_relation.update_time
	 * @return  the value of wx_product_sku_relation.update_time
	 * @mbggenerated  Tue Sep 16 22:14:29 CST 2014
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wx_product_sku_relation.update_time
	 * @param updateTime  the value for wx_product_sku_relation.update_time
	 * @mbggenerated  Tue Sep 16 22:14:29 CST 2014
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}