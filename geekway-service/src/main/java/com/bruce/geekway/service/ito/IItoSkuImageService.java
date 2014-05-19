package com.bruce.geekway.service.ito;

import java.util.List;

import com.bruce.geekway.model.ItoSkuImage;
import com.bruce.geekway.service.IBaseService;

public interface IItoSkuImageService extends IBaseService<ItoSkuImage, Integer>{
	
	public List<ItoSkuImage> queryAllBySkuId(int skuId);
	
	public List<ItoSkuImage> queryAllByProductId(int productId);
	
	public int delete(int skuImageId, int productId, int skuId);
	
}
