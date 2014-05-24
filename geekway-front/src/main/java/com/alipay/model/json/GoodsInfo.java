package com.alipay.model.json;

public class GoodsInfo {

	/* id */
	private String id;
	/* 名称 */
	private String name;
	/* 价格 */
	private String price;
	/* 过期 */
	private String expiry_date;
	/* 描述 */
	private String desc;
	/* sku标题 */
	private String sku_title;
	/* sku */
	private String sku;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSku_title() {
		return sku_title;
	}

	public void setSku_title(String sku_title) {
		this.sku_title = sku_title;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	
	

}
