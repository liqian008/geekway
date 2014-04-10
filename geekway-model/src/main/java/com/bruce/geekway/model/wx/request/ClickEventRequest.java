package com.bruce.geekway.model.wx.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 自定义菜单点击事件的请求<br>
 * MsgId 字段无效
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
public class ClickEventRequest extends EventRequest {

    private static final long serialVersionUID = 1L;

    /** 事件KEY值，与自定义菜单接口中KEY值对应 */
    public String EventKey;

    @Override
    public String toString() {
        return "ClickEventRequest [Event=" + Event + ", ToUserName=" + toUserName + ", FromUserName=" + fromUserName + ", CreateTime=" + createTime
                + ", MsgType=" + msgType + ", MsgId=" + msgId + ", EventKey=" + EventKey + "]";
    }

}
