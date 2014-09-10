package com.bruce.geekway.model.wx.message;

import java.io.Serializable;

/**
 * 主动图片消息<br>
 * msgtype = "image"
 * 
 */
public class ImageMessage extends CustomMessage {

	private static final long serialVersionUID = 1L;

	/** 图片 */
	public Media image = new Media();

	public ImageMessage() {
		msgtype = "image";
	}

	public Media getImage() {
		return image;
	}

	public void setImage(Media image) {
		this.image = image;
	}
	
	public void addImage(String media_id) {
		this.image = new Media();
		image.setMedia_id(media_id);
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

	@Override
	public String toString() {
		return "ImageMessage [touser=" + touser + ", msgtype=" + msgtype
				+ ", image=" + image + "]";
	}

}
