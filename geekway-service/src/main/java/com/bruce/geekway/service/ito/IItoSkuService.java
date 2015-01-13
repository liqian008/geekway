package com.bruce.geekway.service.ito;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.ItoSku;
import com.bruce.geekway.model.ItoSkuCriteria;

public interface IItoSkuService extends IFoundationService<ItoSku, Integer, ItoSkuCriteria>{
	
	public int deleteByProductId(int productId);
	
	public List<ItoSku> queryAllByProductId(int productId);
	
	public ItoSku loadProductSku(int productId, int skuId);

	/*根据sku的属性id删除sku*/
	public void deleteBySkuPropValueIds(int productId, List<Integer> productSkuValueIdList);
	
//	public int queryCountBySkuPropValueId(int skuPropValueId);
	
}