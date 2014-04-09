package com.bruce.geekway.model.wx.response;


/**
 * 音乐消息的响应
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
public class MusicResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    public MusicResponse() {
        msgType = "music";
    }

    /** 音乐标题 */
    public String Title;

    /** 音乐描述 */
    public String Description;

    /** 音乐链接 */
    public String MusicURL;

    /** 高质量音乐链接，WIFI环境优先使用该链接播放音乐 */
    public String HQMusicUrl;

    /** 缩略图的媒体id，通过上传多媒体文件，得到的id */
    public Integer ThumbMediaId;

    @Override
    public String toString() {
        return "MusicResponse [ToUserName=" + toUserName + ", FromUserName=" + fromUserName + ", CreateTime=" + createTime + ", MsgType=" + msgType
                + ", Title=" + Title + ", Description=" + Description + ", MusicURL=" + MusicURL + ", HQMusicUrl=" + HQMusicUrl + ", ThumbMediaId="
                + ThumbMediaId + "]";
    }

}
