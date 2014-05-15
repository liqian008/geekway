package com.bruce.geekway.dao;

import com.bruce.geekway.model.WxMaterialNewsArticle;

public interface IWxMaterialNewsArticleDao extends IBaseDao<WxMaterialNewsArticle, Integer> {
	
	public int delete(int newsId, int articleId);
	
	public int deleteByNewsId(int newsId); 

	public int deleteByArticleId(int articleId);
	
	public int topNewsArticle(int newsId, int articleId);

	
}
