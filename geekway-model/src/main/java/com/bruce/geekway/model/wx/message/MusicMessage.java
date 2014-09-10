package com.bruce.geekway.model.wx.message;

/**
 * 主动音乐消息<br>
 * msgtype = "music"
 */
public class MusicMessage extends CustomMessage {

	private static final long serialVersionUID = 1L;

	public MusicMessage() {
		msgtype = "music";
	}

	/** 发送的视频的媒体ID */
	public String media_id;

	/** 音乐标题 [可选] */
	public String title;

	/** 音乐描述 [可选] */
	public String description;

	/** 音乐链接 */
	public String musicurl;

	/** 高品质音乐链接，wifi环境优先使用该链接播放音乐 */
	public String hqmusicurl;

	/** 缩略图的媒体ID */
	public String thumb_media_id;

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

	public String getMusicurl() {
		return musicurl;
	}

	public void setMusicurl(String musicurl) {
		this.musicurl = musicurl;
	}

	public String getHqmusicurl() {
		return hqmusicurl;
	}

	public void setHqmusicurl(String hqmusicurl) {
		this.hqmusicurl = hqmusicurl;
	}

	public String getThumb_media_id() {
		return thumb_media_id;
	}

	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}

	@Override
	public String toString() {
		return "MusicMessage [touser=" + touser + ", msgtype=" + msgtype
				+ ", media_id=" + media_id + ", title=" + title
				+ ", description=" + description + ", musicurl=" + musicurl
				+ ", hqmusicurl=" + hqmusicurl + ", thumb_media_id="
				+ thumb_media_id + "]";
	}

}
