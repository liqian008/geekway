package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.IWxMaterialArticleDao;
import com.bruce.geekway.model.WxMaterialArticle;
import com.bruce.geekway.service.IWxMaterialArticleService;

@Service
public class WxMaterialArticleServiceImpl implements IWxMaterialArticleService{
	
	@Autowired
	private IWxMaterialArticleDao wxMaterialArticleDao;
	
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
	
	
//	@Override
//    public List<WxMaterialArticle> queryMaterialArticlesByCommandId(int commandId) { 
//        return wxMaterialArticleDao.queryMaterialArticlesByCommandId(commandId);
//    }
//	
//	@Override
//    public List<WxMaterialArticle> queryMaterialArticlesByCommandId(int commandId, int limit) {
//        return wxMaterialArticleDao.queryMaterialArticlesByCommandId(commandId, limit);
//    }
//	
//	@Override
//    public List<WxMaterialArticle> queryMaterialArticlesOutCommandId(int commandId) { 
//        return wxMaterialArticleDao.queryMaterialArticlesOutCommandId(commandId);
//    }

	public IWxMaterialArticleDao getWxMaterialArticleDao() {
		return wxMaterialArticleDao;
	}

	public void setWxMaterialArticleDao(IWxMaterialArticleDao wxMaterialArticleDao) {
		this.wxMaterialArticleDao = wxMaterialArticleDao;
	}
	
}