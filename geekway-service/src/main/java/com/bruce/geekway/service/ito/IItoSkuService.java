package com.bruce.geekway.service.ito;

import java.util.List;

import com.bruce.geekway.model.ItoSku;
import com.bruce.geekway.service.IBaseService;

public interface IItoSkuService extends IBaseService<ItoSku, Integer>{
	
	public List<ItoSku> queryAllByProductId(int productId);
	
	public ItoSku loadProductSku(int productId, int skuId);
	
}