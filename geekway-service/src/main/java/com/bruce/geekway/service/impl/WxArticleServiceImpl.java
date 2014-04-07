package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.IWxArticleDao;
import com.bruce.geekway.model.WxArticle;
import com.bruce.geekway.service.IWxArticleService;

@Service
public class WxArticleServiceImpl implements IWxArticleService{
	
	@Autowired
	private IWxArticleDao wxArticleDao;
	
	@Override
	public int save(WxArticle t) {
		return wxArticleDao.save(t);
	}

	@Override
	public int updateById(WxArticle t) {
		return wxArticleDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return wxArticleDao.deleteById(id);
	}

	@Override
	public WxArticle loadById(Integer id) {
		return wxArticleDao.loadById(id);
	}

	@Override
	public List<WxArticle> queryAll() {
		return wxArticleDao.queryAll();
	}
	
	
	@Override
    public List<WxArticle> queryArticlesByModuleId(int moduleId) { 
        return wxArticleDao.queryArticlesByModuleId(moduleId);
    }
	
	@Override
    public List<WxArticle> queryArticlesOutModuleId(int moduleId) { 
        return wxArticleDao.queryArticlesOutModuleId(moduleId);
    }
	
}