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
    private WxArticleMapper articleMapper;

    @Override
    public int save(WxArticle t) {
        return articleMapper.insert(t);
    }

    @Override
    public int updateById(WxArticle t) {
        return articleMapper.updateByPrimaryKey(t);
    }

    @Override
    public int deleteById(Integer id) {
        return articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WxArticle loadById(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<WxArticle> queryAll() {
        return articleMapper.selectByExample(null);
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
        return articleMapper.queryArticlesByModuleId(moduleId);
    }

	public WxArticleMapper getArticleMapper() {
		return articleMapper;
	}

	public void setArticleMapper(WxArticleMapper articleMapper) {
		this.articleMapper = articleMapper;
	}

}
