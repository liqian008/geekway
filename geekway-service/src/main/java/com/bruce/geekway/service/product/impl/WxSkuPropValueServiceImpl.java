package com.bruce.geekway.service.product.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.mapper.WxSkuPropValueMapper;
import com.bruce.geekway.model.WxProductSkuRelation;
import com.bruce.geekway.model.WxSkuPropValue;
import com.bruce.geekway.model.WxSkuPropValueCriteria;
import com.bruce.geekway.service.product.IWxProductSkuRelationService;
import com.bruce.geekway.service.product.IWxSkuPropValueService;

@Service
public class WxSkuPropValueServiceImpl implements IWxSkuPropValueService{
	
	@Autowired
	private WxSkuPropValueMapper wxSkuPropValueMapper;
	@Autowired
	private IWxProductSkuRelationService wxProductSkuRelationService;
	
	
	@Override
	public int save(WxSkuPropValue t) {
		return wxSkuPropValueMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxSkuPropValue t) {
		return wxSkuPropValueMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxSkuPropValue t, WxSkuPropValueCriteria criteria) {
		return wxSkuPropValueMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxSkuPropValueMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxSkuPropValueCriteria criteria) {
		return wxSkuPropValueMapper.deleteByExample(criteria);
	}

	@Override
	public WxSkuPropValue loadById(Integer id) {
		return wxSkuPropValueMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxSkuPropValue> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxSkuPropValue> queryAll(String orderByClause) {
		WxSkuPropValueCriteria criteria = new WxSkuPropValueCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxSkuPropValue> queryByCriteria(WxSkuPropValueCriteria criteria) {
		return wxSkuPropValueMapper.selectByExample(criteria);
	}
	
	
	@Override
	public List<WxSkuPropValue> querySkuPropValueListByPropIdList(List<Integer> skuPropIdList) {
		WxSkuPropValueCriteria criteria = new WxSkuPropValueCriteria();
    	criteria.createCriteria().andSkuPropIdIn(skuPropIdList);
		return queryByCriteria(criteria);
	}
	
	@Override
	public List<WxSkuPropValue> querySkuPropValueListByCategoryId(int categoryId) {
		return null;
	}

	
	@Override
	public List<WxSkuPropValue> querySortedSkuPropValues() {
		WxSkuPropValueCriteria criteria = new WxSkuPropValueCriteria();
    	criteria.createCriteria();
    	criteria.setOrderByClause(" sku_prop_id, sort ");
    	return wxSkuPropValueMapper.selectByExample(criteria);
		
	}
	
	@Override
	public List<WxSkuPropValue> querySkuValueListByIdList(List<Integer> idList) {
		WxSkuPropValueCriteria criteria = new WxSkuPropValueCriteria();
    	criteria.createCriteria().andIdIn(idList);
    	return wxSkuPropValueMapper.selectByExample(criteria);
	}


	@Override
	public List<Integer> querySkuPropValueIdListByProductId(int productId) {
		List<WxProductSkuRelation> relationList = wxProductSkuRelationService.queryByProductId(productId);
		if(relationList!=null&&relationList.size()>0){
			List<Integer> valueIdList = new ArrayList<Integer>();
			for(WxProductSkuRelation relation: relationList){
				valueIdList.add(relation.getSkuPropValueId());
			}
			return valueIdList;
		}
		return null;
	}

	
	@Override
	public List<WxSkuPropValue> querySkuPropValueListByProductId(int productId) {
		List<Integer> valueIdList = querySkuPropValueIdListByProductId(productId);
		if(valueIdList!=null&&valueIdList.size()>0){
			WxSkuPropValueCriteria criteria = new WxSkuPropValueCriteria();
			criteria.createCriteria().andIdIn(valueIdList);
			criteria.setOrderByClause(" sort ");
			return queryByCriteria(criteria);
		}
		return null;
	}

	

	@Override
	public HashMap<Integer, WxSkuPropValue> queryMap() {
		HashMap<Integer, WxSkuPropValue> skuPropValueHm = new HashMap<Integer, WxSkuPropValue>();
		List<WxSkuPropValue> skuPropValueList = queryAll();
		if(skuPropValueList!=null){
			for(WxSkuPropValue skuPropValue: skuPropValueList){
				skuPropValueHm.put(skuPropValue.getId(), skuPropValue);
			}
			return skuPropValueHm;
		}
		return skuPropValueHm;
	}
	
	
	
	
	
	

	public WxSkuPropValueMapper getWxSkuPropValueMapper() {
		return wxSkuPropValueMapper;
	}

	public void setWxSkuPropValueMapper(WxSkuPropValueMapper wxSkuPropValueMapper) {
		this.wxSkuPropValueMapper = wxSkuPropValueMapper;
	}

	public IWxProductSkuRelationService getWxProductSkuRelationService() {
		return wxProductSkuRelationService;
	}

	public void setWxProductSkuRelationService(
			IWxProductSkuRelationService wxProductSkuRelationService) {
		this.wxProductSkuRelationService = wxProductSkuRelationService;
	}

}