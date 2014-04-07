package com.bruce.geekway.service;

import com.bruce.geekway.model.WxCodeModuleArticle;

public interface IWxCodeModuleArticleService extends IBaseService<WxCodeModuleArticle, Integer>{

	public int delete(int moduleId, int articleId); 
	
	
}