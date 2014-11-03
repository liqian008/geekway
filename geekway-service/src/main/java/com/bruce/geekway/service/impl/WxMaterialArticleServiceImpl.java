package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.dao.mapper.WxMaterialArticleMapper;
import com.bruce.geekway.model.WxMaterialArticle;
import com.bruce.geekway.model.WxMaterialArticleCriteria;
import com.bruce.geekway.service.IWxMaterialArticleService;

//import com.bruce.geekway.dao.IWxMaterialNewsArticleDao;

@Service
public class WxMaterialArticleServiceImpl implements IWxMaterialArticleService, InitializingBean {

	@Autowired
	private WxMaterialArticleMapper wxMaterialArticleMapper;

	@Override
	public int save(WxMaterialArticle t) {
		return wxMaterialArticleMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxMaterialArticle t) {
		return wxMaterialArticleMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxMaterialArticle t, WxMaterialArticleCriteria criteria) {
		return wxMaterialArticleMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxMaterialArticleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxMaterialArticleCriteria criteria) {
		return wxMaterialArticleMapper.deleteByExample(criteria);
	}

	@Override
	public WxMaterialArticle loadById(Integer id) {
		return wxMaterialArticleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxMaterialArticle> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxMaterialArticle> queryAll(String orderByClause) {
		WxMaterialArticleCriteria criteria = new WxMaterialArticleCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxMaterialArticle> queryByCriteria(WxMaterialArticleCriteria criteria) {
		return wxMaterialArticleMapper.selectByExample(criteria);
	}


	
	public List<WxMaterialArticle> queryMaterialArticlesByNewsId(int newsId) {
		return wxMaterialArticleMapper.queryMaterialArticlesByNewsId(newsId);
	}

	public List<WxMaterialArticle> queryMaterialArticlesByNewsId(int newsId, int limit) {
		return wxMaterialArticleMapper.queryMaterialArticlesByNewsId(newsId, limit);
	}

	public List<WxMaterialArticle> queryMaterialArticlesOutNewsId(int newsId) {
		return wxMaterialArticleMapper.queryMaterialArticlesOutNewsId(newsId);
	}
	
//	/* 查询commandId对应的素材列表 */
//	public List<WxMaterialArticle> queryMaterialArticlesByCommandId(int commandId) {
//		return wxMaterialArticleMapper.queryMaterialArticlesByCommandId(commandId);
//	}
//	
//	/* 查询普通的素材列表 */
//	public List<WxMaterialArticle> queryGeneralMaterials() {
//		WxMaterialArticleCriteria criteria = new WxMaterialArticleCriteria();
//		criteria.createCriteria().andSubscribeStatusEqualTo((short) 0);
//		criteria.setOrderByClause("id desc");
//		return wxMaterialArticleMapper.selectByExample(criteria);
//	}
//
//	/* 查询关注时素材列表 */
//	public List<WxMaterialArticle> querySubscribedMaterials() {
//		WxMaterialArticleCriteria criteria = new WxMaterialArticleCriteria();
//		criteria.createCriteria().andSubscribeStatusNotEqualTo((short) 0);
//		criteria.setOrderByClause("id desc");
//		return wxMaterialArticleMapper.selectByExample(criteria);
//	}
//
//	/* 查询关注状态对应的素材列表 */
//	public List<WxMaterialArticle> querySubscribedMaterials(short subscribeStatus) {
//		WxMaterialArticleCriteria criteria = new WxMaterialArticleCriteria();
//		criteria.createCriteria().andSubscribeStatusEqualTo(subscribeStatus);
//		criteria.setOrderByClause("id desc");
//		return wxMaterialArticleMapper.selectByExample(criteria);
//	}

	@Override
	public List<WxMaterialArticle> fallloadByCriteria(int pageSize, WxMaterialArticleCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResult<WxMaterialArticle> pagingByCriteria(int pageNo, int pageSize, WxMaterialArticleCriteria criteria) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?ConstConfig.PAGE_SIZE_DEFAULT:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		//构造查询条件
		if(criteria==null){
			criteria = new WxMaterialArticleCriteria();
		}
		
		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);
		
		int count = wxMaterialArticleMapper.countByExample(criteria);
		List<WxMaterialArticle> dataList = wxMaterialArticleMapper.selectByExample(criteria);
		//返回分页数据
		return new PagingResult<WxMaterialArticle>(pageNo, pageSize, count, dataList);
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