package com.bruce.geekway.model.wx.json;

import java.io.Serializable;
import java.util.Set;

/**
 * 群发广播对象
 * @author liqian
 *
 */
public class WxBroadcastInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String msgtype;
    /*根据分组进行群发*/
    private WxGroupInfo.Group filter;
    /*根据openid进行群发*/
    private Set<String> touser;
    
    /*文本*/
	private WxMedia text;
	/*图文*/
	private WxMedia mpnews;
	/*图片*/
	private WxMedia image;
	private WxMedia voice;
	/*视频（暂时不支持）*/
	private WxMedia mpvideo;
	
	
	
    
	public WxGroupInfo.Group getFilter() {
		return filter;
	}
	public void setFilter(WxGroupInfo.Group filter) {
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
	
}
