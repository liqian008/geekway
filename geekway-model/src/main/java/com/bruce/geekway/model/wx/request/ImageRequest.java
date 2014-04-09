package com.bruce.geekway.model.wx.request;


/**
 * 图片消息的请求
 * 
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
public class ImageRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;

	/** 图片链接 */
	public String picUrl;

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	/** 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。 */
	public String MediaId;

	@Override
	public String toString() {
		return "ImageRequest [ToUserName=" + toUserName + ", FromUserName=" + fromUserName + ", CreateTime=" + createTime + ", MsgType=" + msgType + ", MsgId=" + msgId
				+ ", PicUrl=" + picUrl + ", MediaId=" + MediaId + "]";
	}
}
