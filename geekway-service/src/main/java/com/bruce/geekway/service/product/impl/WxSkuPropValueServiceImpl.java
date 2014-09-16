package com.bruce.geekway.service.product.impl;

import java.util.Date;
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
	public List<WxSkuPropValue> querySortedSkuPropValues() {
		WxSkuPropValueCriteria criteria = new WxSkuPropValueCriteria();
    	criteria.createCriteria();
    	criteria.setOrderByClause(" sku_prop_id, sort ");
    	return wxSkuPropValueMapper.selectByExample(criteria);
		
	}

	@Override
	public List<Integer> querySkuValueIdListByProductId(int productId) {
		return null;
	}

	@Override
	public List<WxSkuPropValue> querySkuValueListByProductId(int productId) {
		return null;
	}

	@Override
	public int deleteSkuValuesByProductId(int productId) {
		return 0;
	}

	@Override
	public int saveProductSkuValues(int productId, List<Integer> productSkuValueIdList) {
		int result = 0;
		if(productId>0&&productSkuValueIdList!=null&&productSkuValueIdList.size()>0){
			Date currentTime = new Date();
			for(Integer skuPropValueId: productSkuValueIdList){
				if(skuPropValueId!=null){
					WxProductSkuRelation relation = new WxProductSkuRelation();
					relation.setProductId(productId);
					relation.setSkuPropValueId(skuPropValueId);
					relation.setCreateTime(currentTime);
					int rows = wxProductSkuRelationService.save(relation);
					result +=rows;
				}
			}
		}
		return result;
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

}