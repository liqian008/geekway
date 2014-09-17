package com.bruce.geekway.service.product;

import java.util.HashMap;
import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.WxSkuPropValue;
import com.bruce.geekway.model.WxSkuPropValueCriteria;

public interface IWxSkuPropValueService extends IFoundationService<WxSkuPropValue, Integer, WxSkuPropValueCriteria>{

	/*根据sku属性列表查询其对应的sku属性值列表*/
	public List<WxSkuPropValue> querySkuPropValueListByPropIdList(List<Integer> skuPropIdList);
	
	/*根据categoryId查询其包含的sku属性值列表*/
	public List<WxSkuPropValue> querySkuPropValueListByCategoryId(int categoryId);
	
	
	/*根据propValue的id列表，查询其对应的数据项列表*/
	public List<WxSkuPropValue> querySkuValueListByIdList(List<Integer> skuPropValueIdList);
	

	public List<Integer> querySkuPropValueIdListByProductId(int productId);
	
	public List<WxSkuPropValue> querySkuPropValueListByProductId(int productId);

	public HashMap<Integer, WxSkuPropValue> queryMap();
	
	public List<WxSkuPropValue> querySortedSkuPropValues();

}