package com.bruce.geekway.model.wx.message;

import java.io.Serializable;

/**
 * 主动客服消息的公用部分
 */
public abstract class CustomMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 普通用户openid */
    public String touser;

    /** 消息类型 */
    public String msgtype;

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

}
