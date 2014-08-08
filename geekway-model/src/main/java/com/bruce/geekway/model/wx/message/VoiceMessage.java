package com.bruce.geekway.model.wx.message;

import java.io.Serializable;


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

	/** 图片 */
	public Media voice = new Media();

	public Media getVoice() {
		return voice;
	}

	public void setVoice(Media vioce) {
		this.voice = vioce;
	}
	
	public void addVoice(String media_id) {
		this.voice = new Media();
		voice.setMedia_id(media_id);
	}
	
	
	@Override
	public String toString() {
		return "VoiceMessage [touser=" + touser + ", msgtype=" + msgtype
				+ ", voice=" + voice + "]";
	}

	
	/** 媒体 */
	public static class Media implements Serializable {

		private static final long serialVersionUID = 1L;

		/** 发送的图片的媒体ID */
		private String media_id;

		public String getMedia_id() {
			return media_id;
		}

		public void setMedia_id(String media_id) {
			this.media_id = media_id;
		}

		@Override
		public String toString() {
			return "Media [media_id=" + media_id + "]";
		}

	}
}
