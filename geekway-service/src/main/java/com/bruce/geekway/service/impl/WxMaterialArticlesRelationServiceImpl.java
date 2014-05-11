package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.IWxMaterialArticlesRelationDao;
import com.bruce.geekway.model.WxMaterialArticlesRelation;
import com.bruce.geekway.service.IWxMaterialArticlesRelationService;

@Service
public class WxMaterialArticlesRelationServiceImpl implements IWxMaterialArticlesRelationService{
	
	@Autowired
	private IWxMaterialArticlesRelationDao wxMaterialArticlesRelationDao;
	
	@Override
	public int save(WxMaterialArticlesRelation t) {
		return wxMaterialArticlesRelationDao.save(t);
	}

	@Override
	public int updateById(WxMaterialArticlesRelation t) {
		return wxMaterialArticlesRelationDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return wxMaterialArticlesRelationDao.deleteById(id);
	}

	@Override
	public WxMaterialArticlesRelation loadById(Integer id) {
		return wxMaterialArticlesRelationDao.loadById(id);
	}

	@Override
	public List<WxMaterialArticlesRelation> queryAll() {
		return wxMaterialArticlesRelationDao.queryAll();
	}
	
	@Override
	public int delete(int materialsId, int materialId) {
		return wxMaterialArticlesRelationDao.delete(materialsId, materialId);
	}
	
}