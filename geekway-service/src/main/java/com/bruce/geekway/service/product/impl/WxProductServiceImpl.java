package com.bruce.geekway.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.geekway.dao.mapper.WxProductMapper;
import com.bruce.geekway.model.WxProduct;
import com.bruce.geekway.model.WxProductCriteria;
import com.bruce.geekway.service.product.IWxProductService;

public class WxProductServiceImpl implements IWxProductService {

	@Autowired
	private WxProductMapper wxPayProductMapper;

	@Override
	public int save(WxProduct t) {
		return wxPayProductMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxProduct t) {
		return wxPayProductMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxProduct t, WxProductCriteria criteria) {
		return wxPayProductMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxPayProductMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxProductCriteria criteria) {
		return wxPayProductMapper.deleteByExample(criteria);
	}

	@Override
	public WxProduct loadById(Integer id) {
		return wxPayProductMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxProduct> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxProduct> queryAll(String orderByClause) {
		WxProductCriteria criteria = new WxProductCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxProduct> queryByCriteria(WxProductCriteria criteria) {
		return wxPayProductMapper.selectByExample(criteria);
	}

	@Override
	public List<WxProduct> queryAvailableList() {
		WxProductCriteria criteria = new WxProductCriteria();
		criteria.createCriteria().andStatusEqualTo((short) 1);
		return wxPayProductMapper.selectByExample(criteria);
	}

	public WxProductMapper getWxProductMapper() {
		return wxPayProductMapper;
	}

	public void setWxProductMapper(WxProductMapper wxPayProductMapper) {
		this.wxPayProductMapper = wxPayProductMapper;
	}

}