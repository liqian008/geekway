package com.bruce.geekway.model.wx.request;

/**
 * 链接消息的请求
 * 
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
public class LinkRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;

	/** 消息标题 */
	public String title;

	/** 消息描述 */
	public String description;

	/** 消息链接 */
	public String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "LinkRequest [ToUserName=" + toUserName + ", FromUserName=" + fromUserName + ", CreateTime=" + createTime + ", MsgType=" + msgType + ", MsgId=" + msgId + ", Title="
				+ title + ", Description=" + description + ", Url=" + url + "]";
	}

}
