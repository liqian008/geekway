package com.bruce.geekway.model.wx.json;

import java.io.Serializable;
import java.util.Set;

import com.bruce.geekway.model.wx.WxBroadcastTypeEnum;

/**
 * 群发广播对象
 * 
 * @author liqian
 * 
 */
public class WxBroadcastInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String msgtype;
	/* 根据分组进行群发 */
	private WxBroadcastInfo.FilterGroup filter;
	/* 根据openid进行群发 */
	private Set<String> touser;

	/* 文本 */
	private WxMedia text;
	/* 图文 */
	private WxMedia mpnews;
	/* 图片 */
	private WxMedia image;
	/* 语音 */
	private WxMedia voice;
	/* 视频（暂时不支持） */
	private WxMedia mpvideo;

	/**
	 * 构造群发对象
	 * 
	 * @param broadcastTypeEnum
	 * @param content
	 * @param mediaId
	 */
	public WxBroadcastInfo(WxBroadcastTypeEnum broadcastTypeEnum, String content, String mediaId) {
		this.msgtype = broadcastTypeEnum.toString();
		if (WxBroadcastTypeEnum.TEXT.equals(broadcastTypeEnum)) {// 群发文本
			text = new WxMedia(null, content);
		} else if (WxBroadcastTypeEnum.MPNEWS.equals(broadcastTypeEnum)) {// 群发图文消息
			mpnews = new WxMedia(null, content);
		} else if (WxBroadcastTypeEnum.IMAGE.equals(broadcastTypeEnum)) {// 群发图片
			image = new WxMedia(null, content);
		} else if (WxBroadcastTypeEnum.VOICE.equals(broadcastTypeEnum)) {// 群发语音
			voice = new WxMedia(null, content);
		}
	}

	public WxBroadcastInfo.FilterGroup getFilter() {
		return filter;
	}

	public void setFilter(WxBroadcastInfo.FilterGroup filter) {
		this.filter = filter;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public Set<String> getTouser() {
		return touser;
	}

	public void setTouser(Set<String> touser) {
		this.touser = touser;
	}

	public WxMedia getText() {
		return text;
	}

	public void setText(WxMedia text) {
		this.text = text;
	}

	public WxMedia getMpnews() {
		return mpnews;
	}

	public void setMpnews(WxMedia mpnews) {
		this.mpnews = mpnews;
	}

	public WxMedia getImage() {
		return image;
	}

	public void setImage(WxMedia image) {
		this.image = image;
	}

	public WxMedia getVoice() {
		return voice;
	}

	public void setVoice(WxMedia voice) {
		this.voice = voice;
	}

	public WxMedia getMpvideo() {
		return mpvideo;
	}

	public void setMpvideo(WxMedia mpvideo) {
		this.mpvideo = mpvideo;
	}

	/**
	 * 分组信息
	 * 
	 */
	public static class FilterGroup implements Serializable {

		private static final long serialVersionUID = 1L;

		/** 分组id，由微信分配 */
		public Integer group_id;

		public Integer getGroup_id() {
			return group_id;
		}

		public void setGroup_id(Integer group_id) {
			this.group_id = group_id;
		}

		@Override
		public String toString() {
			return "Group [group_id=" + group_id + "]";
		}

	}
}
