package com.bruce.geekway.model.wx.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 视频消息的响应 <Br>
 * MsgType 是 video
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class VideoResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    public VideoResponse() {
        msgType = "video";
    }

    /** 通过上传多媒体文件，得到的id。 */
    public Integer mediaId;

    /** 视频消息的标题 */
    public Integer title;

    /** 视频消息的描述 */
    public Integer description;

    
    
    public Integer getMediaId() {
		return mediaId;
	}



	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}



	public Integer getTitle() {
		return title;
	}



	public void setTitle(Integer title) {
		this.title = title;
	}



	public Integer getDescription() {
		return description;
	}



	public void setDescription(Integer description) {
		this.description = description;
	}



	@Override
    public String toString() {
        return "VideoResponse [ToUserName=" + toUserName + ", FromUserName=" + fromUserName + ", CreateTime=" + createTime + ", MsgType=" + msgType
                + ", MediaId=" + mediaId + ", Title=" + title + ", Description=" + description + "]";
    }

}
