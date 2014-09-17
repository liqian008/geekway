package com.bruce.geekway.service.product.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.mapper.WxSkuPropMapper;
import com.bruce.geekway.model.WxSkuProp;
import com.bruce.geekway.model.WxSkuPropCriteria;
import com.bruce.geekway.service.product.IWxSkuPropService;

@Service
public class WxSkuPropServiceImpl implements IWxSkuPropService{
	
	@Autowired
	private WxSkuPropMapper wxSkuPropMapper;
	
	@Override
	public int save(WxSkuProp t) {
		return wxSkuPropMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxSkuProp t) {
		return wxSkuPropMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxSkuProp t, WxSkuPropCriteria criteria) {
		return wxSkuPropMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxSkuPropMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxSkuPropCriteria criteria) {
		return wxSkuPropMapper.deleteByExample(criteria);
	}

	@Override
	public WxSkuProp loadById(Integer id) {
		return wxSkuPropMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxSkuProp> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxSkuProp> queryAll(String orderByClause) {
		WxSkuPropCriteria criteria = new WxSkuPropCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxSkuProp> queryByCriteria(WxSkuPropCriteria criteria) {
		return wxSkuPropMapper.selectByExample(criteria);
	}

	@Override
	public HashMap<Integer, WxSkuProp> queryMap() {
		HashMap<Integer, WxSkuProp> skuPropHm = new HashMap<Integer, WxSkuProp>();
		List<WxSkuProp> skuPropList = queryAll();
		if(skuPropList!=null){
			for(WxSkuProp skuProp: skuPropList){
				skuPropHm.put(skuProp.getId(), skuProp);
			}
			return skuPropHm;
		}
		return skuPropHm;
	}

	
	
	
	
	
	
	public WxSkuPropMapper getWxSkuPropMapper() {
		return wxSkuPropMapper;
	}

	public void setWxSkuPropMapper(WxSkuPropMapper wxSkuPropMapper) {
		this.wxSkuPropMapper = wxSkuPropMapper;
	}

	
	
}