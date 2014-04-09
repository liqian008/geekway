package com.bruce.geekway.model.wx.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * 被动响应消息的公用部分
 * 
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
public abstract class BaseResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 接收方帐号（收到的OpenID），自动赋值 */
    public String toUserName;

    /** 开发者微信号，自动赋值 */
    public String fromUserName;

    /** 消息创建时间，自动赋值 */
    public String createTime;

    /** 消息类型，自动赋值 */
    public String msgType;

    public String getToUserName() {
        return toUserName;
    }
    
    public BaseResponse() {
    }

    public BaseResponse(String toUserName, String fromUserName) {
        this.toUserName = toUserName;
        this.fromUserName = fromUserName;
        this.createTime = String.valueOf(System.currentTimeMillis());
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @Override
    public String toString() {
        return "AbstractResponse [ToUserName=" + toUserName + ", FromUserName="
                + fromUserName + ", CreateTime=" + createTime + ", MsgType="
                + msgType + "]";
    }

}
