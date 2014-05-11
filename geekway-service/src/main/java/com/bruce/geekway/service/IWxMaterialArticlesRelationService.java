package com.bruce.geekway.service;

import com.bruce.geekway.model.WxMaterialArticlesRelation;

public interface IWxMaterialArticlesRelationService extends IBaseService<WxMaterialArticlesRelation, Integer> {
	
	public int delete(int articlesId, int articleId);
	
}
