package com.bruce.geekway.service;

import java.util.List;

import com.bruce.geekway.model.WxMaterialArticle;

public interface IWxMaterialArticleService extends IBaseService<WxMaterialArticle, Integer>{

	public List<WxMaterialArticle> queryMaterialArticlesByNewsId(int newsId);
	
	public List<WxMaterialArticle> queryMaterialArticlesByNewsId(int newsId, int limit);

	public List<WxMaterialArticle> queryMaterialArticlesOutNewsId(int newsId);
	
	
}