package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.IWxMaterialArticlesDao;
import com.bruce.geekway.model.WxMaterialArticles;
import com.bruce.geekway.service.IWxMaterialArticlesService;

@Service
public class WxMaterialArticlesServiceImpl implements IWxMaterialArticlesService{
	
	@Autowired
	private IWxMaterialArticlesDao wxMaterialArticlesDao;
	
	@Override
	public int save(WxMaterialArticles t) {
		return wxMaterialArticlesDao.save(t);
	}

	@Override
	public int updateById(WxMaterialArticles t) {
		return wxMaterialArticlesDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return wxMaterialArticlesDao.deleteById(id);
	}

	@Override
	public WxMaterialArticles loadById(Integer id) {
		return wxMaterialArticlesDao.loadById(id);
	}

	@Override
	public List<WxMaterialArticles> queryAll() {
		return wxMaterialArticlesDao.queryAll();
	}
	
	
//	@Override
//    public List<WxMaterialArticles> queryMaterialArticlessByCommandId(int commandId) { 
//        return wxMaterialArticlesDao.queryMaterialArticlessByCommandId(commandId);
//    }
//	
//	@Override
//    public List<WxMaterialArticles> queryMaterialArticlessByCommandId(int commandId, int limit) {
//        return wxMaterialArticlesDao.queryMaterialArticlessByCommandId(commandId, limit);
//    }
//	
//	@Override
//    public List<WxMaterialArticles> queryMaterialArticlessOutCommandId(int commandId) { 
//        return wxMaterialArticlesDao.queryMaterialArticlessOutCommandId(commandId);
//    }

	public IWxMaterialArticlesDao getWxMaterialArticlesDao() {
		return wxMaterialArticlesDao;
	}

	public void setWxMaterialArticlesDao(IWxMaterialArticlesDao wxMaterialArticlesDao) {
		this.wxMaterialArticlesDao = wxMaterialArticlesDao;
	}
	
}