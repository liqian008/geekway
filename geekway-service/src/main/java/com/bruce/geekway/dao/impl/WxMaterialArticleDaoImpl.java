package com.bruce.geekway.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.IWxMaterialArticleDao;
import com.bruce.geekway.dao.mapper.WxMaterialArticleMapper;
import com.bruce.geekway.model.WxMaterialArticle;
import com.bruce.geekway.model.WxMaterialArticleCriteria;


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
	
	/*查询commandId对应的素材列表*/
	public List<WxMaterialArticle> queryMaterialArticlesByCommandId(int commandId){
		return wxMaterialArticleMapper.queryMaterialArticlesByCommandId(commandId);
	}
	
	/*查询关注时素材列表*/
	public List<WxMaterialArticle> querySubscribedMaterials(){
		WxMaterialArticleCriteria criteria = new WxMaterialArticleCriteria();
		criteria.createCriteria().andSubscribeStatusNotEqualTo((short) 0);
		criteria.setOrderByClause("id desc");
		return wxMaterialArticleMapper.selectByExample(criteria);
	}
	
	/*查询普通的素材列表*/
	public List<WxMaterialArticle> queryGeneralMaterials(){
		WxMaterialArticleCriteria criteria = new WxMaterialArticleCriteria();
		criteria.createCriteria().andSubscribeStatusEqualTo((short) 0);
		criteria.setOrderByClause("id desc");
		return wxMaterialArticleMapper.selectByExample(criteria);
	}
	
    
	@Override
	public void afterPropertiesSet() throws Exception {
		
	}
    
	public WxMaterialArticleMapper getWxMaterialArticleMapper() {
		return wxMaterialArticleMapper;
	}

	public void setWxMaterialArticleMapper(WxMaterialArticleMapper wxMaterialArticleMapper) {
		this.wxMaterialArticleMapper = wxMaterialArticleMapper;
	}

 
}
