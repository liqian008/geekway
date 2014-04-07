package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.IWxCodeModuleArticleDao;
import com.bruce.geekway.model.WxCodeModuleArticle;
import com.bruce.geekway.service.IWxCodeModuleArticleService;

@Service
public class WxCodeModuleArticleServiceImpl implements IWxCodeModuleArticleService{
	
	@Autowired
	private IWxCodeModuleArticleDao wxCodeModuleArticleDao;
	
	@Override
	public int save(WxCodeModuleArticle t) {
		return wxCodeModuleArticleDao.save(t);
	}

	@Override
	public int updateById(WxCodeModuleArticle t) {
		return wxCodeModuleArticleDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return wxCodeModuleArticleDao.deleteById(id);
	}

	@Override
	public WxCodeModuleArticle loadById(Integer id) {
		return wxCodeModuleArticleDao.loadById(id);
	}

	@Override
	public List<WxCodeModuleArticle> queryAll() {
		return wxCodeModuleArticleDao.queryAll();
	}
	
	@Override
	public int delete(int moduleId, int articleId) {
		return wxCodeModuleArticleDao.delete(moduleId, articleId);
	}
	
}