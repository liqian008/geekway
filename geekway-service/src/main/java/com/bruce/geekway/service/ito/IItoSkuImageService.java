package com.bruce.geekway.service.ito;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.ItoSkuImage;
import com.bruce.geekway.model.ItoSkuImageCriteria;

public interface IItoSkuImageService extends IFoundationService<ItoSkuImage, Integer, ItoSkuImageCriteria>{
	
	public List<ItoSkuImage> queryAllBySkuId(int skuId);
	
	public List<ItoSkuImage> queryAllByProductId(int productId);
	
	public int delete(int skuImageId, int productId, int skuId);
	
}
