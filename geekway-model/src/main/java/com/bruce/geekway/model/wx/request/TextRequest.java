package com.bruce.geekway.model.wx.request;

/**
 * 文本消息的请求
 * 
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
public class TextRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;

	/** 文本消息内容 */
	public String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "TextRequest [ToUserName=" + toUserName + ", FromUserName=" + fromUserName + ", CreateTime=" + createTime + ", MsgType=" + msgType + ", MsgId=" + msgId
				+ ", Content=" + content + "]";
	}

}
