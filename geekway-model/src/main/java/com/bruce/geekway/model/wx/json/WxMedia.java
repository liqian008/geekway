package com.bruce.geekway.model.wx.json;

import java.io.Serializable;
import java.util.Set;

/**
 * 群发广播对象
 * @author liqian
 *
 */
public class WxMedia implements Serializable {

    private static final long serialVersionUID = 1L;
    /*wx生成的mediaId*/
    private String media_id;
    /*文本内容，仅用在群发文本上*/
    private String content;

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
}
