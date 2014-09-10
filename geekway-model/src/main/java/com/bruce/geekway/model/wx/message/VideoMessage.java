package com.bruce.geekway.model.wx.message;

/**
 * 主动视频消息<br>
 * msgtype = "video"
 */
public class VideoMessage extends CustomMessage {

	private static final long serialVersionUID = 1L;

	public VideoMessage() {
		msgtype = "voice";
	}

	/** 发送的视频的媒体ID */
	private String media_id;
	/** 发送的视频的缩略图媒体ID */
	private String thumb_media_id;

	/** 视频消息的标题 [可选] */
	private String title;

	/** 视频消息的描述 [可选] */
	private String description;

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

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

	public String getThumb_media_id() {
		return thumb_media_id;
	}

	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}

	@Override
	public String toString() {
		return "VideoMessage [touser=" + touser + ", msgtype=" + msgtype
				+ ", media_id=" + media_id + ", title=" + title
				+ ", description=" + description + "]";
	}

}
