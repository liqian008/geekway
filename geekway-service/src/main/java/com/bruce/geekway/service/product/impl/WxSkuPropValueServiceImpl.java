package com.bruce.geekway.service.product.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.mapper.WxSkuPropValueMapper;
import com.bruce.geekway.model.WxProductSku;
import com.bruce.geekway.model.WxSkuPropValue;
import com.bruce.geekway.model.WxSkuPropValueCriteria;
import com.bruce.geekway.service.product.IWxProductSkuService;
import com.bruce.geekway.service.product.IWxSkuPropValueService;

@Service
public class WxSkuPropValueServiceImpl implements IWxSkuPropValueService{
	
	@Autowired
	private WxSkuPropValueMapper wxSkuPropValueMapper;
	@Autowired
	private IWxProductSkuService wxProductSkuService;
	
	
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
	public int countByCriteria(WxSkuPropValueCriteria criteria) {
		return wxSkuPropValueMapper.countByExample(criteria);
	}
	
	@Override
	public List<WxSkuPropValue> querySkuPropValueListByIdList(List<Integer> idList) {
		WxSkuPropValueCriteria criteria = new WxSkuPropValueCriteria();
    	criteria.createCriteria().andIdIn(idList); 
		return queryByCriteria(criteria);
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
		List<WxProductSku> productSkuList = wxProductSkuService.queryAllByProductId(productId);
		if(productSkuList!=null&&productSkuList.size()>0){
			Set<Integer> valueIdSet = new TreeSet<Integer>();
			for(WxProductSku productSku: productSkuList){
				Integer skuColorValueId = productSku.getSkuColorValueId();
				if(skuColorValueId!=null&&skuColorValueId>0){
					valueIdSet.add(skuColorValueId);
				}
				
				Integer skuSizeValueId = productSku.getSkuSizeValueId();
				if(skuSizeValueId!=null&&skuSizeValueId>0){
					valueIdSet.add(skuSizeValueId);
				}
				
			}
			return new ArrayList<Integer>(valueIdSet);
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

	public IWxProductSkuService getWxProductSkuService() {
		return wxProductSkuService;
	}

	public void setWxProductSkuService(IWxProductSkuService wxProductSkuService) {
		this.wxProductSkuService = wxProductSkuService;
	}

	
	
}