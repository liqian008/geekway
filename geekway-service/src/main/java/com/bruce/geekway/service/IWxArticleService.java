package com.bruce.geekway.service;

import java.util.List;

import com.bruce.geekway.model.WxArticle;

public interface IWxArticleService extends IBaseService<WxArticle, Integer>{

	public List<WxArticle> queryArticlesByModuleId(int moduleId);
	
	public List<WxArticle> queryArticlesByModuleId(int moduleId, int limit);

	public List<WxArticle> queryArticlesOutModuleId(int moduleId);
	
	
}