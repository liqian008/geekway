package com.bruce.geekway.model.wx.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 视频消息请求<br>
 * MsgType 语音为video
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
public class VideoRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

    /** 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。 */
    public String mediaId;

    /** 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。 */
    public String thumbMediaId;

    @Override
    public String toString() {
        return "VideoRequest [ToUserName=" + toUserName + ", FromUserName=" + fromUserName + ", CreateTime=" + createTime + ", MsgType=" + msgType + ", MsgId="
                + msgId + ", MediaId=" + mediaId + ", ThumbMediaId=" + thumbMediaId + "]";
    }

}
