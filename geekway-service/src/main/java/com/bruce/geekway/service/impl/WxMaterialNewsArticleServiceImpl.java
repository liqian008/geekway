package com.bruce.geekway.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.dao.mapper.WxMaterialNewsArticleMapper;
import com.bruce.geekway.model.WxMaterialNewsArticle;
import com.bruce.geekway.model.WxMaterialNewsArticleCriteria;
import com.bruce.geekway.service.IWxMaterialNewsArticleService;

@Service
public class WxMaterialNewsArticleServiceImpl implements IWxMaterialNewsArticleService, InitializingBean {

	@Autowired
	private WxMaterialNewsArticleMapper wxMaterialNewsArticleMapper;

	@Override
	public int save(WxMaterialNewsArticle t) {
		return wxMaterialNewsArticleMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxMaterialNewsArticle t) {
		return wxMaterialNewsArticleMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxMaterialNewsArticle t,
			WxMaterialNewsArticleCriteria criteria) {
		return wxMaterialNewsArticleMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxMaterialNewsArticleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxMaterialNewsArticleCriteria criteria) {
		return wxMaterialNewsArticleMapper.deleteByExample(criteria);
	}

	@Override
	public WxMaterialNewsArticle loadById(Integer id) {
		return wxMaterialNewsArticleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxMaterialNewsArticle> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxMaterialNewsArticle> queryAll(String orderByClause) {
		WxMaterialNewsArticleCriteria criteria = new WxMaterialNewsArticleCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxMaterialNewsArticle> queryByCriteria(WxMaterialNewsArticleCriteria criteria) {
		return wxMaterialNewsArticleMapper.selectByExample(criteria);
	}
	
	
	@Override
	public int topArticle(int newsId, int articleId) {
		WxMaterialNewsArticleCriteria criteria = new WxMaterialNewsArticleCriteria();
		criteria.createCriteria().andNewsIdEqualTo(newsId).andArticleIdEqualTo(articleId);
		
		WxMaterialNewsArticle newsArticle = new WxMaterialNewsArticle();
		newsArticle.setTopTime(new Date());
		
		return wxMaterialNewsArticleMapper.updateByExampleSelective(newsArticle, criteria);
	}

	
	@Override
	public int delete(int newsId, int articleId) {
		WxMaterialNewsArticleCriteria criteria = new WxMaterialNewsArticleCriteria();
		criteria.createCriteria().andNewsIdEqualTo(newsId).andArticleIdEqualTo(articleId);
		return deleteByCriteria(criteria);
	}
	

	@Override
	public int deleteByNewsId(int newsId) {
		WxMaterialNewsArticleCriteria criteria = new WxMaterialNewsArticleCriteria();
		criteria.createCriteria().andNewsIdEqualTo(newsId);
		return deleteByCriteria(criteria);
	}
	
	@Override
	public int deleteByArticleId(int articleId) {
		WxMaterialNewsArticleCriteria criteria = new WxMaterialNewsArticleCriteria();
		criteria.createCriteria().andArticleIdEqualTo(articleId);
		return deleteByCriteria(criteria);
	}
	
	@Override
	public List<WxMaterialNewsArticle> fallloadByCriteria(int pageSize,
			WxMaterialNewsArticleCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResult<WxMaterialNewsArticle> pagingByCriteria(int pageNo,
			int pageSize, WxMaterialNewsArticleCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	public WxMaterialNewsArticleMapper getWxMaterialNewsArticleMapper() {
		return wxMaterialNewsArticleMapper;
	}

	public void setWxMaterialNewsArticleMapper(
			WxMaterialNewsArticleMapper wxMaterialNewsArticleMapper) {
		this.wxMaterialNewsArticleMapper = wxMaterialNewsArticleMapper;
	}

	
	

}