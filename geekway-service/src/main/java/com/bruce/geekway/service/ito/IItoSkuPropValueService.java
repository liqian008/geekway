package com.bruce.geekway.service.ito;

import java.util.HashMap;
import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.ItoSkuPropValue;
import com.bruce.geekway.model.ItoSkuPropValueCriteria;

public interface IItoSkuPropValueService extends IFoundationService<ItoSkuPropValue, Integer, ItoSkuPropValueCriteria>{

	
	public List<ItoSkuPropValue> querySortedSkuPropValues();

	public List<Integer> querySkuValueIdListByProductId(int productId);
	
	public List<ItoSkuPropValue> querySkuValueListByProductId(int productId);

	public int deleteSkuValuesByProductId(int productId);
	
	public int saveProductSkuValues(int productId, List<Integer> productSkuValueIdList);

	public HashMap<Integer, ItoSkuPropValue> queryMap();
	
//	public List<ItoSkuPropValue> queryCombiledSkuPropValueListByProductId(int productId);
    
	
	public int deleteBySkuPropValueIds(int productId, List<Integer> skuPropValueIdList);
	

}