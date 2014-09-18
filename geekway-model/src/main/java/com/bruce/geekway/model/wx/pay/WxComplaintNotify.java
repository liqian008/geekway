package com.bruce.geekway.model.wx.pay;

import java.util.List;

/**
 * 发自微信的投诉xml对象
 * 
 * @author liqian
 * 
 */
public class WxComplaintNotify {

	public String openId;
	public String appId;
	public String timestamp;
	public String msgType;//通知类型 request用户提交投诉,confirm 用户确认消除,  reject用户拒绝消除投诉
	public String feedbackId;
	public String transId;
	public String reason;

	public String solution;
	public String extInfo;
	public String appSignature;
	public String signMethod;
	public List<String> picItemList;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getExtInfo() {
		return extInfo;
	}

	public void setExtInfo(String extInfo) {
		this.extInfo = extInfo;
	}

	public String getAppSignature() {
		return appSignature;
	}

	public void setAppSignature(String appSignature) {
		this.appSignature = appSignature;
	}

	public String getSignMethod() {
		return signMethod;
	}

	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}

	public List<String> getPicItemList() {
		return picItemList;
	}

	public void setPicItemList(List<String> picItemList) {
		this.picItemList = picItemList;
	}

}
