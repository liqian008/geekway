package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.IWxMaterialNewsArticleDao;
import com.bruce.geekway.model.WxMaterialNewsArticle;
import com.bruce.geekway.service.IWxMaterialNewsArticleService;

@Service
public class WxMaterialNewsArticleServiceImpl implements IWxMaterialNewsArticleService{
	
	@Autowired
	private IWxMaterialNewsArticleDao wxMaterialNewsArticleDao;
	
	@Override
	public int save(WxMaterialNewsArticle t) {
		return wxMaterialNewsArticleDao.save(t);
	}

	@Override
	public int updateById(WxMaterialNewsArticle t) {
		return wxMaterialNewsArticleDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return wxMaterialNewsArticleDao.deleteById(id);
	}

	@Override
	public WxMaterialNewsArticle loadById(Integer id) {
		return wxMaterialNewsArticleDao.loadById(id);
	}

	@Override
	public List<WxMaterialNewsArticle> queryAll() {
		return wxMaterialNewsArticleDao.queryAll();
	}
	
	@Override
	public int delete(int newsId, int articleId) {
		return wxMaterialNewsArticleDao.delete(newsId, articleId);
	}
	
	@Override
	public int deleteByNewsId(int newsId) {
		return wxMaterialNewsArticleDao.deleteByNewsId(newsId);
	}
	
	/**
	 * 置顶
	 * @param newsId
	 * @param articleId
	 * @return
	 */
	public int topNewsArticle(int newsId, int articleId){
		return wxMaterialNewsArticleDao.topNewsArticle(newsId, articleId); 
	}
	
}