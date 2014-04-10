package com.bruce.geekway.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.IWxArticleDao;
import com.bruce.geekway.dao.mapper.WxArticleMapper;
import com.bruce.geekway.model.WxArticle;


@Repository
public class WxArticleDaoImpl implements IWxArticleDao, InitializingBean {

     @Autowired
    private WxArticleMapper wxArticleMapper;

    @Override
    public int save(WxArticle t) {
        return wxArticleMapper.insert(t);
    }

    @Override
    public int updateById(WxArticle t) {
        return wxArticleMapper.updateByPrimaryKey(t);
    }

    @Override
    public int deleteById(Integer id) {
        return wxArticleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WxArticle loadById(Integer id) {
        return wxArticleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<WxArticle> queryAll() {
        return wxArticleMapper.selectByExample(null);
    }

    @Override
    public List<WxArticle> fallLoadList(Integer tailId, int limit) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public List<WxArticle> queryArticlesByModuleId(int moduleId) {
        return wxArticleMapper.queryArticlesByModuleId(moduleId);
    }
    
    @Override
    public List<WxArticle> queryArticlesOutModuleId(int moduleId) {
        return wxArticleMapper.queryArticlesOutModuleId(moduleId);
    }
    
	public WxArticleMapper getWxArticleMapper() {
		return wxArticleMapper;
	}

	public void setWxArticleMapper(WxArticleMapper wxArticleMapper) {
		this.wxArticleMapper = wxArticleMapper;
	}

}
