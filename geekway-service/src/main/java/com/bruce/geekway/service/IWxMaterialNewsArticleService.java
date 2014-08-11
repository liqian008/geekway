package com.bruce.geekway.service;

import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.WxMaterialNewsArticle;
import com.bruce.geekway.model.WxMaterialNewsArticleCriteria;

/**
 * 多图文管理
 * @author liqian
 *
 */
public interface IWxMaterialNewsArticleService extends IFoundationPagingService<WxMaterialNewsArticle, Integer, WxMaterialNewsArticleCriteria>{

	public int deleteByNewsId(int newsId);
	
	public int deleteByArticleId(int articleId);
	
	public int delete(int newsId, int articleId);
	
	
}