package com.bruce.geekway.service.ito;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.ItoSku;
import com.bruce.geekway.model.ItoSkuCriteria;

public interface IItoSkuService extends IFoundationService<ItoSku, Integer, ItoSkuCriteria>{
	
	public List<ItoSku> queryAllByProductId(int productId);
	
	public ItoSku loadProductSku(int productId, int skuId);
	
//	public int queryCountBySkuPropValueId(int skuPropValueId);
	
}