package com.bruce.geekway.service.product.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.geekway.dao.mapper.SkuPropValueMapper;
import com.bruce.geekway.model.ProductSku;
import com.bruce.geekway.model.SkuPropValue;
import com.bruce.geekway.model.SkuPropValueCriteria;
import com.bruce.geekway.service.product.IProductSkuService;
import com.bruce.geekway.service.product.ISkuPropValueService;

public class SkuPropValueServiceImpl implements ISkuPropValueService{
	
	@Autowired
	private SkuPropValueMapper skuPropValueMapper;
	@Autowired
	private IProductSkuService productSkuService;
	
	
	@Override
	public int save(SkuPropValue t) {
		return skuPropValueMapper.insertSelective(t);
	}

	@Override
	public int updateById(SkuPropValue t) {
		return skuPropValueMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(SkuPropValue t, SkuPropValueCriteria criteria) {
		return skuPropValueMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return skuPropValueMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(SkuPropValueCriteria criteria) {
		return skuPropValueMapper.deleteByExample(criteria);
	}

	@Override
	public SkuPropValue loadById(Integer id) {
		return skuPropValueMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SkuPropValue> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<SkuPropValue> queryAll(String orderByClause) {
		SkuPropValueCriteria criteria = new SkuPropValueCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<SkuPropValue> queryByCriteria(SkuPropValueCriteria criteria) {
		return skuPropValueMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(SkuPropValueCriteria criteria) {
		return skuPropValueMapper.countByExample(criteria);
	}
	
	@Override
	public List<SkuPropValue> querySkuPropValueListByIdList(List<Integer> idList) {
		SkuPropValueCriteria criteria = new SkuPropValueCriteria();
    	criteria.createCriteria().andIdIn(idList); 
		return queryByCriteria(criteria);
	}
	
	
	@Override
	public List<SkuPropValue> querySkuPropValueListByPropIdList(List<Integer> skuPropIdList) {
		SkuPropValueCriteria criteria = new SkuPropValueCriteria();
    	criteria.createCriteria().andSkuPropIdIn(skuPropIdList);
		return queryByCriteria(criteria);
	}
	
	
	
	@Override
	public List<SkuPropValue> querySkuPropValueListByCategoryId(int categoryId) {
		return null;
	}

	
	@Override
	public List<SkuPropValue> querySortedSkuPropValues() {
		SkuPropValueCriteria criteria = new SkuPropValueCriteria();
    	criteria.createCriteria();
    	criteria.setOrderByClause(" sku_prop_id, sort ");
    	return skuPropValueMapper.selectByExample(criteria);
		
	}
	
	@Override
	public List<SkuPropValue> querySkuValueListByIdList(List<Integer> idList) {
		SkuPropValueCriteria criteria = new SkuPropValueCriteria();
    	criteria.createCriteria().andIdIn(idList);
    	return skuPropValueMapper.selectByExample(criteria);
	}


	@Override
	public List<Integer> querySkuPropValueIdListByProductId(int productId) {
		List<ProductSku> productSkuList = productSkuService.querySkuListByProductId(productId);
		if(productSkuList!=null&&productSkuList.size()>0){
			Set<Integer> valueIdSet = new TreeSet<Integer>();
			for(ProductSku productSku: productSkuList){
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
	public List<SkuPropValue> querySkuPropValueListByProductId(int productId) {
		List<Integer> valueIdList = querySkuPropValueIdListByProductId(productId);
		if(valueIdList!=null&&valueIdList.size()>0){
			SkuPropValueCriteria criteria = new SkuPropValueCriteria();
			criteria.createCriteria().andIdIn(valueIdList);
			criteria.setOrderByClause(" sort ");
			return queryByCriteria(criteria);
		}
		return null;
	}

	

	@Override
	public HashMap<Integer, SkuPropValue> queryMap() {
		HashMap<Integer, SkuPropValue> skuPropValueHm = new HashMap<Integer, SkuPropValue>();
		List<SkuPropValue> skuPropValueList = queryAll();
		if(skuPropValueList!=null){
			for(SkuPropValue skuPropValue: skuPropValueList){
				skuPropValueHm.put(skuPropValue.getId(), skuPropValue);
			}
			return skuPropValueHm;
		}
		return skuPropValueHm;
	}

	public SkuPropValueMapper getSkuPropValueMapper() {
		return skuPropValueMapper;
	}

	public void setSkuPropValueMapper(SkuPropValueMapper skuPropValueMapper) {
		this.skuPropValueMapper = skuPropValueMapper;
	}

	public IProductSkuService getProductSkuService() {
		return productSkuService;
	}

	public void setProductSkuService(IProductSkuService productSkuService) {
		this.productSkuService = productSkuService;
	}
	
}