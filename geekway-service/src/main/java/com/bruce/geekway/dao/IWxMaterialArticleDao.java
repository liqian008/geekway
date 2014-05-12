package com.bruce.geekway.dao;

import java.util.List;

import com.bruce.geekway.model.WxMaterialArticle;

public interface IWxMaterialArticleDao extends IBaseDao<WxMaterialArticle, Integer> {
	
	public List<WxMaterialArticle> queryMaterialArticlesByNewsId(int newsId);
	
	public List<WxMaterialArticle> queryMaterialArticlesByNewsId(int newsId, int limit);

	public List<WxMaterialArticle> queryMaterialArticlesOutNewsId(int newsId);
    
}
