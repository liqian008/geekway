package com.bruce.geekway.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.IWxMaterialArticleDao;
import com.bruce.geekway.dao.mapper.WxMaterialArticleMapper;
import com.bruce.geekway.model.WxMaterialArticle;


@Repository
public class WxMaterialArticleDaoImpl implements IWxMaterialArticleDao, InitializingBean {

     @Autowired
    private WxMaterialArticleMapper wxMaterialArticleMapper;

    @Override
    public int save(WxMaterialArticle t) {
        return wxMaterialArticleMapper.insert(t);
    }

    @Override
    public int updateById(WxMaterialArticle t) {
        return wxMaterialArticleMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return wxMaterialArticleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WxMaterialArticle loadById(Integer id) {
        return wxMaterialArticleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<WxMaterialArticle> queryAll() {
        return wxMaterialArticleMapper.selectByExample(null);
    }

    @Override
    public List<WxMaterialArticle> fallLoadList(Integer tailId, int limit) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public List<WxMaterialArticle> queryMaterialArticlesByNewsId(int newsId){
    	return wxMaterialArticleMapper.queryMaterialArticlesByNewsId(newsId); 
//    	return null;
    }
	
	public List<WxMaterialArticle> queryMaterialArticlesByNewsId(int newsId, int limit){
		return wxMaterialArticleMapper.queryMaterialArticlesByNewsId(newsId, limit);
//		return null;
	}

	public List<WxMaterialArticle> queryMaterialArticlesOutNewsId(int newsId){
		return wxMaterialArticleMapper.queryMaterialArticlesOutNewsId(newsId);
//		return null;
	}
    
    
	public WxMaterialArticleMapper getWxMaterialArticleMapper() {
		return wxMaterialArticleMapper;
	}

	public void setWxMaterialArticleMapper(WxMaterialArticleMapper wxMaterialArticleMapper) {
		this.wxMaterialArticleMapper = wxMaterialArticleMapper;
	}

	

}
