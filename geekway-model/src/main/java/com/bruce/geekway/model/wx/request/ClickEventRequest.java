package com.bruce.geekway.model.wx.request;


/**
 * 自定义菜单点击事件的请求，包含click与view<br>
 * MsgId 字段无效
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
public class ClickEventRequest extends EventRequest {

    private static final long serialVersionUID = 1L;
    
    public ClickEventRequest(String eventKey){
    	this.eventKey = eventKey;
    }
    
    /** 事件KEY值，与自定义菜单接口中KEY值对应 */
    public String eventKey;

    @Override
    public String toString() {
        return "ClickEventRequest [Event=" + event + ", ToUserName=" + toUserName + ", FromUserName=" + fromUserName + ", CreateTime=" + createTime
                + ", MsgType=" + msgType + ", MsgId=" + msgId + ", EventKey=" + eventKey + "]";
    }

}
