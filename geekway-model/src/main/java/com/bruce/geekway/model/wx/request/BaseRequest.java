package com.bruce.geekway.model.wx.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * 被动请求消息的公用部分
 * 
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
public class BaseRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 开发者微信号 */
	public String toUserName;

	/** 发送方帐号（一个OpenID） */
	public String fromUserName;

	/** 消息创建时间 （整型） */
	public String createTime;

	/** 消息类型 */
	public String msgType;

	/** 消息id，64位整型（事件消息{@link SubscribeEventRequest}没有该属性） */
	public String msgId;

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	@Override
	public String toString() {
		return "AbstractRequest [ToUserName=" + toUserName + ", FromUserName=" + fromUserName + ", CreateTime=" + createTime + ", MsgType=" + msgType + ", MsgId=" + msgId + "]";
	}

}
