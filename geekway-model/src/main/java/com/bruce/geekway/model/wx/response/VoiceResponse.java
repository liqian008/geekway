package com.bruce.geekway.model.wx.response;


/**
 * 语音请求的响应消息<Br>
 * MsgType 是 voice
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
public class VoiceResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    public VoiceResponse() {
    	MsgType = "voice";
    }
    
    public VoiceResponse(String toUserName, String fromUserName) {
    	super(toUserName, fromUserName);
    	MsgType = "voice";
    }

    /** 通过上传多媒体文件，得到的id。 */
    public Integer MediaId;

    @Override
    public String toString() {
        return "VoiceResponse [ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime=" + CreateTime + ", MsgType=" + MsgType
                + ", MediaId=" + MediaId + "]";
    }

}
