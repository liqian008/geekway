package com.bruce.geekway.model.wx.response;


/**
 * 视频消息的响应 <Br>
 * MsgType 是 video
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
public class VideoResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    public VideoResponse(String toUserName, String fromUserName) {
    	super(toUserName, fromUserName);
    	MsgType = "video";
    }

    /** 通过上传多媒体文件，得到的id。 */
    public Integer MediaId;

    /** 视频消息的标题 */
    public Integer Title;

    /** 视频消息的描述 */
    public Integer Description;

    
    
    public Integer getMediaId() {
		return MediaId;
	}



	public void setMediaId(Integer mediaId) {
		this.MediaId = mediaId;
	}



	public Integer getTitle() {
		return Title;
	}



	public void setTitle(Integer title) {
		this.Title = title;
	}



	public Integer getDescription() {
		return Description;
	}



	public void setDescription(Integer description) {
		this.Description = description;
	}



	@Override
    public String toString() {
        return "VideoResponse [ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime=" + CreateTime + ", MsgType=" + MsgType
                + ", MediaId=" + MediaId + ", Title=" + Title + ", Description=" + Description + "]";
    }

}
