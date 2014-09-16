package com.bruce.geekway.service.product;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.WxProductSku;
import com.bruce.geekway.model.WxProductSkuCriteria;

public interface IWxProductSkuService extends IFoundationService<WxProductSku, Integer, WxProductSkuCriteria>{
	
	public List<WxProductSku> queryAllByProductId(int productId);
	
	public WxProductSku loadProductSku(int productId, int skuId);
	
//	public int queryCountBySkuPropValueId(int skuPropValueId);
	
}