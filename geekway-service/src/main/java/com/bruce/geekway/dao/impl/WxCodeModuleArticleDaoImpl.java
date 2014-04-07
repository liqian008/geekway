package com.bruce.geekway.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.IWxCodeModuleArticleDao;
import com.bruce.geekway.dao.mapper.WxCodeModuleArticleMapper;
import com.bruce.geekway.model.WxCodeModuleArticle;
import com.bruce.geekway.model.WxCodeModuleArticleCriteria;
import com.bruce.geekway.model.WxCodeModuleArticleCriteria.Criteria;


@Repository
public class WxCodeModuleArticleDaoImpl implements IWxCodeModuleArticleDao, InitializingBean {

     @Autowired
    private WxCodeModuleArticleMapper wxCodeModuleArticleMapper;

    @Override
    public int save(WxCodeModuleArticle t) {
        return wxCodeModuleArticleMapper.insert(t);
    }

    @Override
    public int updateById(WxCodeModuleArticle t) {
        return wxCodeModuleArticleMapper.updateByPrimaryKey(t);
    }

    @Override
    public int deleteById(Integer id) {
        return wxCodeModuleArticleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WxCodeModuleArticle loadById(Integer id) {
        return wxCodeModuleArticleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<WxCodeModuleArticle> queryAll() {
        return wxCodeModuleArticleMapper.selectByExample(null);
    }

    @Override
    public List<WxCodeModuleArticle> fallLoadList(Integer tailId, int limit) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

	public WxCodeModuleArticleMapper getWxCodeModuleArticleMapper() {
		return wxCodeModuleArticleMapper;
	}

	public void setWxCodeModuleArticleMapper(WxCodeModuleArticleMapper wxCodeModuleArticleMapper) {
		this.wxCodeModuleArticleMapper = wxCodeModuleArticleMapper;
	}

	@Override
	public int delete(int moduleId, int articleId) {
		WxCodeModuleArticleCriteria criteria = new WxCodeModuleArticleCriteria();
        criteria.createCriteria().andModuleIdEqualTo(moduleId).andArticleIdEqualTo(articleId);
		return wxCodeModuleArticleMapper.deleteByExample(criteria);
	}

	
}
