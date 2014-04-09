package com.bruce.geekway.model.wx.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 位置消息的请求
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocationRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

    /** 地理位置纬度 */
    public float Location_X;

    /** 地理位置经度 */
    public float Location_Y;

    /** 地图缩放大小 */
    public float Scale;

    /** 地理位置信息 */
    public String Label;

    @Override
    public String toString() {
        return "LocationRequest [ToUserName=" + toUserName + ", FromUserName=" + fromUserName + ", CreateTime=" + createTime + ", MsgType=" + msgType
                + ", MsgId=" + msgId + ", Location_X=" + Location_X + ", Location_Y=" + Location_Y + ", Scale=" + Scale + ", Label=" + Label + "]";
    }

}
