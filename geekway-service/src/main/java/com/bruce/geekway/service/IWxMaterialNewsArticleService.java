package com.bruce.geekway.service;

import com.bruce.geekway.model.WxMaterialNewsArticle;

public interface IWxMaterialNewsArticleService extends IBaseService<WxMaterialNewsArticle, Integer> {
	
	public int delete(int newsId, int articleId);
	
	public int deleteByNewsId(int newsId);
	
	
	/**
	 * 置顶
	 * @param newsId
	 * @param articleId
	 * @return
	 */
	public int topNewsArticle(int newsId, int articleId);
	
}
