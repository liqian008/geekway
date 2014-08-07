package com.bruce.geekway.model.wx.message;

/**
 * 主动语音消息<br>
 * msgtype = "voice"
 * 
 */
public class VoiceMessage extends CustomMessage {

	private static final long serialVersionUID = 1L;

	public VoiceMessage() {
		msgtype = "voice";
	}

	/** 发送的语音的媒体ID */
	public String media_id;

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	@Override
	public String toString() {
		return "VoiceMessage [media_id=" + media_id + ", touser=" + touser
				+ ", msgtype=" + msgtype + "]";
	}

}
