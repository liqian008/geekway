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
        MsgType = "news";
    }

    public NewsResponse(String toUserName, String fromUserName) {
    	super(toUserName, fromUserName);
    	MsgType = "news";
    }
    
    
    /** 图文消息个数，限制为10条以内 */
    private int ArticleCount;

    /** 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应 */
    private List<Item> Articles = new ArrayList<Item>();

    public static class Item {

        /** 图文消息标题 */
        private String title;

        /** 图文消息描述 */
        private String description;

        /** 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200 */
        private String picUrl;

        /** 点击图文消息跳转链接 */
        private String url;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getPicUrl() {
			return picUrl;
		}

		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
		
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
        return "NewsResponse [ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime=" + CreateTime + ", MsgType=" + MsgType
                + ", ArticleCount=" + ArticleCount + ", Articles=" + Articles + "]";
    }

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Item> getArticles() {
		return Articles;
	}

	public void setArticles(List<Item> articles) {
		Articles = articles;
	}
    
}
