package com.bruce.geekway.model.wx.request;



/**
 * 关注/取消关注事件的请求 <br>
 * MsgId 字段无效
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
public class SubscribeEventRequest extends EventRequest {

    private static final long serialVersionUID = 1L;

    /** 事件KEY值，qrscene_为前缀，后面为二维码的参数值[扫描了带参数的二维码时使用] */
    public String eventKey;

    /** 二维码的ticket，可用来换取二维码图片[扫描了带参数的二维码时使用] */
    public String ticket;

    @Override
    public String toString() {
        return "SubscribeEventRequest [Event=" + event + ", ToUserName=" + toUserName + ", FromUserName=" + fromUserName + ", CreateTime=" + createTime
                + ", MsgType=" + msgType + ", MsgId=" + msgId + "]";
    }

}
