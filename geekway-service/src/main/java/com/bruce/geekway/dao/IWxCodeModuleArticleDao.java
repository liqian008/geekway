package com.bruce.geekway.dao;

import com.bruce.geekway.model.WxCodeModuleArticle;

public interface IWxCodeModuleArticleDao extends IBaseDao<WxCodeModuleArticle, Integer> {

	public int delete(int moduleId, int articleId);
	
}
