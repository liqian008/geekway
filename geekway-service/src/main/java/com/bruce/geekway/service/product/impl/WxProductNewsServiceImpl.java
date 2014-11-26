package com.bruce.geekway.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.dao.mapper.WxProductNewsMapper;
import com.bruce.geekway.model.WxProductNews;
import com.bruce.geekway.model.WxProductNewsCriteria;
import com.bruce.geekway.service.product.IWxProductNewsService;

@Service
public class WxProductNewsServiceImpl implements IWxProductNewsService, InitializingBean {

	@Autowired
	private WxProductNewsMapper wxProductNewsMapper;

	@Override
	public int save(WxProductNews t) {
		return wxProductNewsMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxProductNews t) {
		return wxProductNewsMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxProductNews t, WxProductNewsCriteria criteria) {
		return wxProductNewsMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxProductNewsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxProductNewsCriteria criteria) {
		return wxProductNewsMapper.deleteByExample(criteria);
	}

	@Override
	public WxProductNews loadById(Integer id) {
		return wxProductNewsMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxProductNews> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxProductNews> queryAll(String orderByClause) {
		WxProductNewsCriteria criteria = new WxProductNewsCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxProductNews> queryByCriteria(WxProductNewsCriteria criteria) {
		return wxProductNewsMapper.selectByExample(criteria);
	}
	

	@Override
	public int countByCriteria(WxProductNewsCriteria criteria) {
		return wxProductNewsMapper.countByExample(criteria);
	}
	

	@Override
	public List<WxProductNews> fallloadByCriteria(int pageSize, WxProductNewsCriteria criteria) {
		return null;
	}

	@Override
	public PagingResult<WxProductNews> pagingByCriteria(int pageNo, int pageSize, WxProductNewsCriteria criteria) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?ConstConfig.PAGE_SIZE_DEFAULT:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		//构造查询条件
		if(criteria==null){
			criteria = new WxProductNewsCriteria();
		}
		
		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);
		
		int count = wxProductNewsMapper.countByExample(criteria);
		List<WxProductNews> dataList = wxProductNewsMapper.selectByExample(criteria);
		//返回分页数据
		return new PagingResult<WxProductNews>(pageNo, pageSize, count, dataList);
	}
	
	

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	public WxProductNewsMapper getWxProductNewsMapper() {
		return wxProductNewsMapper;
	}

	public void setWxProductNewsMapper(WxProductNewsMapper wxProductNewsMapper) {
		this.wxProductNewsMapper = wxProductNewsMapper;
	}

}