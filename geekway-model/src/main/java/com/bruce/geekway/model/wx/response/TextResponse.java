package com.bruce.geekway.model.wx.response;


/**
 * 文本消息的响应
 * 
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
public class TextResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    /** 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示） （长度不超过2048字节） */
    public String Content;
    
    public TextResponse() {
        MsgType = "text";
    }
    
    public TextResponse(String toUserName, String fromUserName, String content) {
        super(toUserName, fromUserName);
        this.MsgType = "text";
        this.Content = content;
    }
    

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }

    @Override
    public String toString() {
        return "TextResponse [ToUserName=" + ToUserName + ", FromUserName="
                + FromUserName + ", CreateTime=" + CreateTime + ", MsgType="
                + MsgType + ", Content=" + Content + "]";
    }

}
