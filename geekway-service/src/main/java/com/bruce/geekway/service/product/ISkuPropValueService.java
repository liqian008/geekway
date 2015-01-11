package com.bruce.geekway.service.product;

import java.util.HashMap;
import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.SkuPropValue;
import com.bruce.geekway.model.SkuPropValueCriteria;

public interface ISkuPropValueService extends IFoundationService<SkuPropValue, Integer, SkuPropValueCriteria>{
	
	/*查询sku属性值列表*/
	public List<SkuPropValue> querySkuPropValueListByIdList(List<Integer> idList);
	
	/*根据sku属性列表查询其对应的sku属性值列表*/
	public List<SkuPropValue> querySkuPropValueListByPropIdList(List<Integer> skuPropIdList);
	
	/*根据categoryId查询其包含的sku属性值列表*/
	public List<SkuPropValue> querySkuPropValueListByCategoryId(int categoryId);
	
	
	/*根据propValue的id列表，查询其对应的数据项列表*/
	public List<SkuPropValue> querySkuValueListByIdList(List<Integer> skuPropValueIdList);
	

	public List<Integer> querySkuPropValueIdListByProductId(int productId);
	
	public List<SkuPropValue> querySkuPropValueListByProductId(int productId);

	public HashMap<Integer, SkuPropValue> queryMap();
	
	public List<SkuPropValue> querySortedSkuPropValues();

}