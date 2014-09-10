package com.bruce.geekway.model.wx.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 语音消息请求<br>
 * MsgType 语音为voice
 * 
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
public class VoiceRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;

	/** 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。 */
	public String mediaId;

	/** 语音格式，如amr，speex等 */
	public String format;

	/** 语音识别结果，UTF8编码 */
	public String recognition;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getRecognition() {
		return recognition;
	}

	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}

	@Override
	public String toString() {
		return "VoiceRequest [ToUserName=" + toUserName + ", FromUserName="
				+ fromUserName + ", CreateTime=" + createTime + ", MsgType="
				+ msgType + ", MsgId=" + msgId + ", MediaId=" + mediaId
				+ ", Format=" + format + ", Recognition=" + recognition + "]";
	}

}
