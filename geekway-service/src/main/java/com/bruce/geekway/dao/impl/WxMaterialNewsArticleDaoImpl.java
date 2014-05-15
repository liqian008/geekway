package com.bruce.geekway.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.IWxMaterialNewsArticleDao;
import com.bruce.geekway.dao.mapper.WxMaterialNewsArticleMapper;
import com.bruce.geekway.model.WxMaterialNewsArticle;
import com.bruce.geekway.model.WxMaterialNewsArticleCriteria;
import com.bruce.geekway.model.WxMaterialNewsArticleCriteria.Criteria;


@Repository
public class WxMaterialNewsArticleDaoImpl implements IWxMaterialNewsArticleDao, InitializingBean {

     @Autowired
    private WxMaterialNewsArticleMapper wxMaterialNewsArticleMapper;

    @Override
    public int save(WxMaterialNewsArticle t) {
        return wxMaterialNewsArticleMapper.insert(t);
    }

    @Override
    public int updateById(WxMaterialNewsArticle t) {
        return wxMaterialNewsArticleMapper.updateByPrimaryKey(t);
    }

    @Override
    public int deleteById(Integer id) {
        return wxMaterialNewsArticleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WxMaterialNewsArticle loadById(Integer id) {
        return wxMaterialNewsArticleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<WxMaterialNewsArticle> queryAll() {
        return wxMaterialNewsArticleMapper.selectByExample(null);
    }

    @Override
    public List<WxMaterialNewsArticle> fallLoadList(Integer tailId, int limit) {
        return null;
    }

    @Override
    public int delete(int newsId, int articleId){
		WxMaterialNewsArticleCriteria criteria = new WxMaterialNewsArticleCriteria();
        criteria.createCriteria().andNewsIdEqualTo(newsId).andArticleIdEqualTo(articleId);
		return wxMaterialNewsArticleMapper.deleteByExample(criteria);
	}
    
    @Override
    public int deleteByNewsId(int newsId){
		WxMaterialNewsArticleCriteria criteria = new WxMaterialNewsArticleCriteria();
        criteria.createCriteria().andNewsIdEqualTo(newsId);
		return wxMaterialNewsArticleMapper.deleteByExample(criteria);
	}
    
    @Override
    public int deleteByArticleId(int articleId){
		WxMaterialNewsArticleCriteria criteria = new WxMaterialNewsArticleCriteria();
        criteria.createCriteria().andArticleIdEqualTo(articleId);
		return wxMaterialNewsArticleMapper.deleteByExample(criteria);
	}


    public int topNewsArticle(int newsId, int articleId){
    	WxMaterialNewsArticleCriteria criteria = new WxMaterialNewsArticleCriteria();
        criteria.createCriteria().andNewsIdEqualTo(newsId).andArticleIdEqualTo(articleId);
        
        //修改top
        WxMaterialNewsArticle newsArticle = new WxMaterialNewsArticle();
        newsArticle.setTopTime(new Date());
		return wxMaterialNewsArticleMapper.updateByExampleSelective(newsArticle, criteria);
    } 
    
    
    @Override
    public void afterPropertiesSet() throws Exception {

    }

	public WxMaterialNewsArticleMapper getWxMaterialNewsArticleMapper() {
		return wxMaterialNewsArticleMapper;
	}

	public void setWxMaterialNewsArticleMapper(WxMaterialNewsArticleMapper wxMaterialNewsArticleMapper) {
		this.wxMaterialNewsArticleMapper = wxMaterialNewsArticleMapper;
	}

	
	
}
