package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.IWxMaterialArticleDao;
//import com.bruce.geekway.dao.IWxMaterialNewsArticleDao;
import com.bruce.geekway.model.WxMaterialArticle;
import com.bruce.geekway.service.IWxMaterialArticleService;

@Service
public class WxMaterialArticleServiceImpl implements IWxMaterialArticleService{
	
	@Autowired
	private IWxMaterialArticleDao wxMaterialArticleDao;
//	@Autowired
//	private IWxMaterialNewsArticleDao wxMaterialNewsArticleDao;
	
	@Override
	public int save(WxMaterialArticle t) {
		return wxMaterialArticleDao.save(t);
	}

	@Override
	public int updateById(WxMaterialArticle t) {
		return wxMaterialArticleDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
//		// 删除资源的关联
//		wxMaterialNewsArticleDao.deleteByArticleId(id);
		//删除实体
		return wxMaterialArticleDao.deleteById(id);
	}

	@Override
	public WxMaterialArticle loadById(Integer id) {
		return wxMaterialArticleDao.loadById(id);
	}

	@Override
	public List<WxMaterialArticle> queryAll() {
		return wxMaterialArticleDao.queryAll();
	}
	
	
	@Override
	public List<WxMaterialArticle> queryMaterialArticlesByNewsId(int articlesId){ 
        return wxMaterialArticleDao.queryMaterialArticlesByNewsId(articlesId);
    }
	
	@Override
    public List<WxMaterialArticle> queryMaterialArticlesByNewsId(int articlesId, int limit) {
        return wxMaterialArticleDao.queryMaterialArticlesByNewsId(articlesId, limit);
    }
	
	@Override
    public List<WxMaterialArticle> queryMaterialArticlesOutNewsId(int articlesId) { 
        return wxMaterialArticleDao.queryMaterialArticlesOutNewsId(articlesId);
    }
	
	/*查询commandId对应的素材列表*/
	public List<WxMaterialArticle> queryMaterialArticlesByCommandId(int commandId){
		return wxMaterialArticleDao.queryMaterialArticlesByCommandId(commandId);
	}
	
	/*查询关注时素材列表*/
	public List<WxMaterialArticle> querySubscribedMaterials(){
		return wxMaterialArticleDao.querySubscribedMaterials();
	}
	
	/*查询普通的素材列表*/
	public List<WxMaterialArticle> queryGeneralMaterials(){
		return wxMaterialArticleDao.queryGeneralMaterials();
	}

	public IWxMaterialArticleDao getWxMaterialArticleDao() {
		return wxMaterialArticleDao;
	}

	public void setWxMaterialArticleDao(IWxMaterialArticleDao wxMaterialArticleDao) {
		this.wxMaterialArticleDao = wxMaterialArticleDao;
	}
	
}