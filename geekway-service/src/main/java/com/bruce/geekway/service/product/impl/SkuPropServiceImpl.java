package com.bruce.geekway.service.product.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.geekway.dao.mapper.SkuPropMapper;
import com.bruce.geekway.model.SkuProp;
import com.bruce.geekway.model.SkuPropCriteria;
import com.bruce.geekway.service.product.ISkuPropService;

public class SkuPropServiceImpl implements ISkuPropService{
	
	@Autowired
	private SkuPropMapper skuPropMapper;
	
	@Override
	public int save(SkuProp t) {
		return skuPropMapper.insertSelective(t);
	}

	@Override
	public int updateById(SkuProp t) {
		return skuPropMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(SkuProp t, SkuPropCriteria criteria) {
		return skuPropMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return skuPropMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(SkuPropCriteria criteria) {
		return skuPropMapper.deleteByExample(criteria);
	}

	@Override
	public SkuProp loadById(Integer id) {
		return skuPropMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SkuProp> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<SkuProp> queryAll(String orderByClause) {
		SkuPropCriteria criteria = new SkuPropCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<SkuProp> queryByCriteria(SkuPropCriteria criteria) {
		return skuPropMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(SkuPropCriteria criteria) {
		return skuPropMapper.countByExample(criteria);
	}

	@Override
	public HashMap<Integer, SkuProp> queryMap() {
		HashMap<Integer, SkuProp> skuPropHm = new HashMap<Integer, SkuProp>();
		List<SkuProp> skuPropList = queryAll();
		if(skuPropList!=null){
			for(SkuProp skuProp: skuPropList){
				skuPropHm.put(skuProp.getId(), skuProp);
			}
			return skuPropHm;
		}
		return skuPropHm;
	}

	public SkuPropMapper getSkuPropMapper() {
		return skuPropMapper;
	}

	public void setSkuPropMapper(SkuPropMapper skuPropMapper) {
		this.skuPropMapper = skuPropMapper;
	}
	
	
}