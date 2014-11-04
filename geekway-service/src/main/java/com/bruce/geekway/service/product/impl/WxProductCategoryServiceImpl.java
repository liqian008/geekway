package com.bruce.geekway.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.mapper.WxProductCategoryMapper;
import com.bruce.geekway.model.WxProductCategory;
import com.bruce.geekway.model.WxProductCategoryCriteria;
import com.bruce.geekway.service.product.IWxProductCategoryService;

@Service
public class WxProductCategoryServiceImpl implements IWxProductCategoryService {

	@Autowired
	private WxProductCategoryMapper wxProductCategoryMapper;

	@Override
	public int save(WxProductCategory t) {
		return wxProductCategoryMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxProductCategory t) {
		return wxProductCategoryMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxProductCategory t, WxProductCategoryCriteria criteria) {
		return wxProductCategoryMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxProductCategoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxProductCategoryCriteria criteria) {
		return wxProductCategoryMapper.deleteByExample(criteria);
	}

	@Override
	public WxProductCategory loadById(Integer id) {
		return wxProductCategoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxProductCategory> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxProductCategory> queryAll(String orderByClause) {
		WxProductCategoryCriteria criteria = new WxProductCategoryCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxProductCategory> queryByCriteria(WxProductCategoryCriteria criteria) {
		return wxProductCategoryMapper.selectByExample(criteria);
	}
	

	public WxProductCategoryMapper getWxProductCategoryMapper() {
		return wxProductCategoryMapper;
	}

	public void setWxProductCategoryMapper(WxProductCategoryMapper wxPayProductCategoryMapper) {
		this.wxProductCategoryMapper = wxPayProductCategoryMapper;
	}

}