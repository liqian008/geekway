package com.bruce.geekway.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.IWxMaterialArticlesRelationDao;
import com.bruce.geekway.dao.mapper.WxMaterialArticlesRelationMapper;
import com.bruce.geekway.model.WxMaterialArticlesRelation;
import com.bruce.geekway.model.WxMaterialArticlesRelationCriteria;
import com.bruce.geekway.model.WxMaterialArticlesRelationCriteria.Criteria;


@Repository
public class WxMaterialArticlesRelationDaoImpl implements IWxMaterialArticlesRelationDao, InitializingBean {

     @Autowired
    private WxMaterialArticlesRelationMapper wxMaterialArticlesRelationMapper;

    @Override
    public int save(WxMaterialArticlesRelation t) {
        return wxMaterialArticlesRelationMapper.insert(t);
    }

    @Override
    public int updateById(WxMaterialArticlesRelation t) {
        return wxMaterialArticlesRelationMapper.updateByPrimaryKey(t);
    }

    @Override
    public int deleteById(Integer id) {
        return wxMaterialArticlesRelationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WxMaterialArticlesRelation loadById(Integer id) {
        return wxMaterialArticlesRelationMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<WxMaterialArticlesRelation> queryAll() {
        return wxMaterialArticlesRelationMapper.selectByExample(null);
    }

    @Override
    public List<WxMaterialArticlesRelation> fallLoadList(Integer tailId, int limit) {
        return null;
    }

    @Override
    public int delete(int articlesId, int articleId){
		WxMaterialArticlesRelationCriteria criteria = new WxMaterialArticlesRelationCriteria();
        criteria.createCriteria().andArticlesIdEqualTo(articlesId).andArticleIdEqualTo(articleId);
		return wxMaterialArticlesRelationMapper.deleteByExample(criteria);
	}

    
    @Override
    public void afterPropertiesSet() throws Exception {

    }

	public WxMaterialArticlesRelationMapper getWxMaterialArticlesRelationMapper() {
		return wxMaterialArticlesRelationMapper;
	}

	public void setWxMaterialArticlesRelationMapper(WxMaterialArticlesRelationMapper wxMaterialArticlesRelationMapper) {
		this.wxMaterialArticlesRelationMapper = wxMaterialArticlesRelationMapper;
	}

	
	
}
