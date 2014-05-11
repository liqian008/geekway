package com.bruce.geekway.dao;

import com.bruce.geekway.model.WxMaterialArticlesRelation;

public interface IWxMaterialArticlesRelationDao extends IBaseDao<WxMaterialArticlesRelation, Integer> {
	
	public int delete(int materialsId, int materialId);
	
}
