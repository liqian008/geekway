package com.bruce.geekway.model.klh;

import java.util.List;

/**
 * 易店宝的订单对象
 * 
 * @author liqian
 * 
 */
public class KlhEdbOrder {

	/* 平台类型 */
	private String platType;
	/* 订单渠道 */
	private String orderChannel;
	/* 收件人手机号 */
	private String mobile;
	/* 收件人电话 */
	private String phone;
	/* 店铺名称 */
	private String shopName;
	/* 订单号 */
	private String expressNo;
	/* 收件人姓名 */
	private String receiverName;
	/* 实收金额 */
	private String referencePricePaid;
	/* 订货时间 */
	private String tidTime;
	/* 付款状态 */
	private String payStatus;
	/* 物流状态 */
	private String deliveryStatus;
	/* 订单类型 */
	private String type;
	/* 订单状态 */
	private String status;
	
	private List<KlhEdbOrderItem> edbOrderItemList;

	public KlhEdbOrder() {
		super();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getOrderChannel() {
		return orderChannel;
	}

	public void setOrderChannel(String orderChannel) {
		this.orderChannel = orderChannel;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReferencePricePaid() {
		return referencePricePaid;
	}

	public void setReferencePricePaid(String referencePricePaid) {
		this.referencePricePaid = referencePricePaid;
	}

	public String getTidTime() {
		return tidTime;
	}

	public void setTidTime(String tidTime) {
		this.tidTime = tidTime;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getPlatType() {
		return platType;
	}

	public void setPlatType(String platType) {
		this.platType = platType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<KlhEdbOrderItem> getEdbOrderItemList() {
		return edbOrderItemList;
	}

	public void setEdbOrderItemList(List<KlhEdbOrderItem> edbOrderItemList) {
		this.edbOrderItemList = edbOrderItemList;
	}
	
}
