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
    public String ToUserName;

    /** 开发者微信号，自动赋值 */
    public String FromUserName;

    /** 消息创建时间，自动赋值 */
    public String CreateTime;

    /** 消息类型，自动赋值 */
    public String MsgType;

    public String getToUserName() {
        return ToUserName;
    }
    
    public BaseResponse() {
    }
    
    protected BaseResponse(String toUserName, String fromUserName) {
        this.ToUserName = toUserName;
        this.FromUserName = fromUserName;
        this.CreateTime = String.valueOf(System.currentTimeMillis()/1000);
    }

    public void setToUserName(String toUserName) {
        this.ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        this.CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        this.MsgType = msgType;
    }

    @Override
    public String toString() {
        return "AbstractResponse [ToUserName=" + ToUserName + ", FromUserName="
                + FromUserName + ", CreateTime=" + CreateTime + ", MsgType="
                + MsgType + "]";
    }

}
