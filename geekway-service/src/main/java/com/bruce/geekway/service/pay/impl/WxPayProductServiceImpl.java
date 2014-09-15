package com.bruce.geekway.service.pay.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.geekway.dao.mapper.WxPayProductMapper;
import com.bruce.geekway.model.WxPayProduct;
import com.bruce.geekway.model.WxPayProductCriteria;
import com.bruce.geekway.service.pay.IWxPayProductService;

public class WxPayProductServiceImpl implements IWxPayProductService {

	@Autowired
	private WxPayProductMapper wxPayProductMapper;

	@Override
	public int save(WxPayProduct t) {
		return wxPayProductMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxPayProduct t) {
		return wxPayProductMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxPayProduct t, WxPayProductCriteria criteria) {
		return wxPayProductMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxPayProductMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxPayProductCriteria criteria) {
		return wxPayProductMapper.deleteByExample(criteria);
	}

	@Override
	public WxPayProduct loadById(Integer id) {
		return wxPayProductMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxPayProduct> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxPayProduct> queryAll(String orderByClause) {
		WxPayProductCriteria criteria = new WxPayProductCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxPayProduct> queryByCriteria(WxPayProductCriteria criteria) {
		return wxPayProductMapper.selectByExample(criteria);
	}
	
	
}