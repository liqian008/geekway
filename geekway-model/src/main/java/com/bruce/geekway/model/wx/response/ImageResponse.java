package com.bruce.geekway.model.wx.response;


/**
 * 图片消息的响应 <Br>
 * MsgType 是 image
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
public class ImageResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    public ImageResponse() {
        MsgType = "image";
    }
    
    public ImageResponse(String toUserName, String fromUserName) {
    	super(toUserName, fromUserName);
    	MsgType = "image";
    }

    /** 通过上传多媒体文件，得到的id。 */
    public String MediaId;

    @Override
    public String toString() {
        return "ImageResponse [ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime=" + CreateTime + ", MsgType=" + MsgType
                + ", MediaId=" + MediaId + "]";
    }

}
