package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.dao.mapper.WxMaterialNewsArticleMapper;
import com.bruce.geekway.dao.mapper.WxMaterialNewsMapper;
import com.bruce.geekway.model.WxMaterialNews;
import com.bruce.geekway.model.WxMaterialNewsCriteria;
import com.bruce.geekway.service.IWxMaterialNewsService;

@Service
public class WxMaterialNewsServiceImpl implements IWxMaterialNewsService, InitializingBean {

	@Autowired
	private WxMaterialNewsMapper wxMaterialNewsMapper;

	@Override
	public int save(WxMaterialNews t) {
		return wxMaterialNewsMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxMaterialNews t) {
		return wxMaterialNewsMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxMaterialNews t,
			WxMaterialNewsCriteria criteria) {
		return wxMaterialNewsMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxMaterialNewsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxMaterialNewsCriteria criteria) {
		return wxMaterialNewsMapper.deleteByExample(criteria);
	}

	@Override
	public WxMaterialNews loadById(Integer id) {
		return wxMaterialNewsMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxMaterialNews> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxMaterialNews> queryAll(String orderByClause) {
		WxMaterialNewsCriteria criteria = new WxMaterialNewsCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxMaterialNews> queryByCriteria(WxMaterialNewsCriteria criteria) {
		return wxMaterialNewsMapper.selectByExample(criteria);
	}
	
	
	
	@Override
	public List<WxMaterialNews> fallloadByCriteria(int pageSize,
			WxMaterialNewsCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResult<WxMaterialNews> pagingByCriteria(int pageNo,
			int pageSize, WxMaterialNewsCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	public WxMaterialNewsMapper getWxMaterialNewsMapper() {
		return wxMaterialNewsMapper;
	}

	public void setWxMaterialNewsMapper(
			WxMaterialNewsMapper wxMaterialNewsMapper) {
		this.wxMaterialNewsMapper = wxMaterialNewsMapper;
	}

}