package com.bruce.geekway.model.wx.response;

import java.util.ArrayList;
import java.util.List;

/**
 * 图文消息的响应
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
public class NewsResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    public NewsResponse() {
        msgType = "news";
    }

    /** 图文消息个数，限制为10条以内 */
    public int ArticleCount;

    /** 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应 */
    public List<Item> Articles = new ArrayList<Item>();

    static class Item {

        /** 图文消息标题 */
        public String title;

        /** 图文消息描述 */
        public String description;

        /** 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200 */
        public String picUrl;

        /** 点击图文消息跳转链接 */
        public String url;

        @Override
        public String toString() {
            return "Item [Title=" + title + ", Description=" + description + ", PicUrl=" + picUrl + ", Url=" + url + "]";
        }

    }

    /**
     * 增加一条图文消息
     * @param Title
     *            图文消息标题
     * @param Description
     *            图文消息描述
     * @param PicUrl
     *            图片链接，支持JPG、PNG格式，较好的效果为大图720*400，小图100*100
     */
    public void addArticle(String Title, String Description, String PicUrl) {
        Item item = new Item();
        item.title = Title;
        item.description = Description;
        item.picUrl = PicUrl;
        Articles.add(item);
        ArticleCount = Articles.size();
    }

    @Override
    public String toString() {
        return "NewsResponse [ToUserName=" + toUserName + ", FromUserName=" + fromUserName + ", CreateTime=" + createTime + ", MsgType=" + msgType
                + ", ArticleCount=" + ArticleCount + ", Articles=" + Articles + "]";
    }

}
