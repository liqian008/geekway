package com.bruce.geekway.service.product;

import java.util.HashMap;
import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.WxSkuPropValue;
import com.bruce.geekway.model.WxSkuPropValueCriteria;

public interface IWxSkuPropValueService extends IFoundationService<WxSkuPropValue, Integer, WxSkuPropValueCriteria>{

	
	public List<WxSkuPropValue> querySortedSkuPropValues();

	public List<Integer> querySkuValueIdListByProductId(int productId);
	
	public List<WxSkuPropValue> querySkuValueListByProductId(int productId);

	public int deleteSkuValuesByProductId(int productId);
	
	public int saveProductSkuValues(int productId, List<Integer> productSkuValueIdList);

	public HashMap<Integer, WxSkuPropValue> queryMap();
	
	

}