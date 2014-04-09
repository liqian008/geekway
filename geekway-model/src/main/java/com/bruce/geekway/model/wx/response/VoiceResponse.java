package com.bruce.geekway.model.wx.response;


/**
 * 语音请求的响应消息<Br>
 * MsgType 是 voice
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
public class VoiceResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    public VoiceResponse() {
        msgType = "voice";
    }

    /** 通过上传多媒体文件，得到的id。 */
    public Integer mediaId;

    @Override
    public String toString() {
        return "VoiceResponse [ToUserName=" + toUserName + ", FromUserName=" + fromUserName + ", CreateTime=" + createTime + ", MsgType=" + msgType
                + ", MediaId=" + mediaId + "]";
    }

}
