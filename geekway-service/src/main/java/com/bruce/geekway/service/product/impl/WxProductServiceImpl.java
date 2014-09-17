package com.bruce.geekway.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.mapper.WxProductMapper;
import com.bruce.geekway.model.WxProduct;
import com.bruce.geekway.model.WxProductCriteria;
import com.bruce.geekway.service.product.IWxProductService;

@Service
public class WxProductServiceImpl implements IWxProductService {

	@Autowired
	private WxProductMapper wxProductMapper;

	@Override
	public int save(WxProduct t) {
		return wxProductMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxProduct t) {
		return wxProductMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxProduct t, WxProductCriteria criteria) {
		return wxProductMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxProductMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxProductCriteria criteria) {
		return wxProductMapper.deleteByExample(criteria);
	}

	@Override
	public WxProduct loadById(Integer id) {
		return wxProductMapper.selectByPrimaryKey(id);
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
		return wxProductMapper.selectByExample(criteria);
	}

	@Override
	public List<WxProduct> queryAvailableList() {
		WxProductCriteria criteria = new WxProductCriteria();
		criteria.createCriteria().andStatusEqualTo((short) 1);
		return wxProductMapper.selectByExample(criteria);
	}

	public WxProductMapper getWxProductMapper() {
		return wxProductMapper;
	}

	public void setWxProductMapper(WxProductMapper wxPayProductMapper) {
		this.wxProductMapper = wxPayProductMapper;
	}


	
	
}